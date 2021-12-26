## [460. LFU Cache](https://leetcode.com/problems/lfu-cache/)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: `get` and `put`.

`get(key)` - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.

`put(key, value)` - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least **recently** used key would be evicted.

Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.

 

Follow up:
- Could you do both operations in O(1) time complexity?

 

Example:

```
LFUCache cache = new LFUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
```

## Answer
### Method 1 - Own LinkedList - :rabbit: 31ms (70.66%)

- Idea:
  - Create two maps. One saves `<key, node>` pair for o(1) access to the node saving the value. One saves `<freq, LRULinkedList>` pair for quick access to each frequent list that is designed with `head` and `tail` LRU cache.
  - `MyLinkedList` is pretty much like **LRU Cache** without `get()` method.
  - `put()` method is like **LRU** with extra step to keep `minFreq` as `1` and save the `node` to corresponding **LRU LinkedList**.
  - `get()`
    - Get its freq linkedlist.
    - remove the node from that linkedlist
    - add it to the `freq+1` linkedlist
    - update `min freq` if that linkedlist is empty **AND** that freq is the minFreq.

#### Approach 2 OOD style - 🚀 49ms (91.56%)
```java
class LFUCache {
    Map<Integer, Node> keyToNode;
    Map<Integer, DoubleList> freqToCache;
    int minFreq;
    int cap;

    public LFUCache(int capacity) {
        keyToNode = new HashMap<>();
        freqToCache = new HashMap<>();
        cap = capacity;
        minFreq = Integer.MAX_VALUE;
    }
    
    public int get(int key) {
        if (!keyToNode.containsKey(key)) return -1;

        Node node = makeRecent(key);

        return node.val;
    }
    
    public void put(int key, int value) {
        if (!keyToNode.containsKey(key)) {
            // LRU cache is full
            if (this.cap <= 0) {
                // unable to remove least frequent used
                // usually it is because initial cap is 0
                if (!removeLeastFreqUsed()) {
                    return;
                }
                this.cap = 0;
            } else {
                this.cap -= 1;
            }

            addNew(key, value);
        } else {
            updateRecentAndFreq(key, value);
        }
    }

    /* Encapsulate operations */
    private Node makeRecent(int key) {
        Node node = keyToNode.get(key);
        int freq = node.getFreq();
        // System.out.println(node.key + ", " + node.val+":"+freq);
        DoubleList oldCache = freqToCache.get(freq);

        // remove from current freqToCache
        oldCache.removeNode(node);
        if (oldCache.getSize() == 0) {
            freqToCache.remove(freq);

            // update minFreq
            if (minFreq >= freq) {
                minFreq = freq + 1; // freq + 1
            }
        }

        // add to a higher freq cache
        DoubleList newCache = 
            freqToCache.computeIfAbsent(freq + 1, o -> new DoubleList());
        newCache.addLast(node);

        // update freq for the node
        node.inc();

        return node;
    }

    private void addNew(int key, int val) {
        Node node = new Node(key, val);
        node.inc();

        // update minFreq since this is a new node
        minFreq = 1;

        // create Cache if it not exist for minFreq
        freqToCache.computeIfAbsent(minFreq, o -> new DoubleList()).addLast(node);

        // add node to keyToNode
        keyToNode.put(key, node);
    }

    private void updateRecentAndFreq(int key, int val) {
        Node node = keyToNode.get(key);
        node.val = val;

        this.get(key);
    }

    private boolean removeLeastFreqUsed() {
        // get cache which stores nodes with min freq
        DoubleList cache = freqToCache.get(minFreq);

        if (cache == null) return false;

        removeLeastRecentUsed(cache);

        return true;
    }

    private void removeLeastRecentUsed(DoubleList cache) {
        Node first = cache.removeFirst();

        if (first == null) return;

        keyToNode.remove(first.key);
    }
}

class DoubleList {
    Node head, tail;
    int size;

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // node must exists
    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.next = null;
        node.prev = null;
        size--;
    }

    public void addLast(Node node) {
        node.next = tail;
        node.prev = tail.prev;

        tail.prev.next = node;
        tail.prev = node;

        size++;
    }

    public Node removeFirst() {
        if (head.next == tail) return null;

        Node first = head.next;
        this.removeNode(first);

        return first;
    }

    public int getSize() {
        return this.size;
    }
}

class Node {
    int key, val, freq;
    Node prev, next;
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public void inc() {
        this.freq++;
    }

    public int getFreq() {
        return this.freq;
    }
}
```

#### Approach 1
```java
class LFUCache {

    Map<Integer, Node> map = new HashMap(); // key, node
    Map<Integer, MyLinkedList> freqs = new HashMap(); // freq, head & tail
    int cap, minFreq;
    public LFUCache(int capacity) {
        cap = capacity;
        minFreq = Integer.MAX_VALUE;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        
        Node n = map.get(key);
        
        int freq = n.freq;
        
        // get its freq linkedlist.
        MyLinkedList linkedlist = freqs.get(freq);
        //  1. remove it from current linkedlist
        linkedlist.remove(n);
        if (linkedlist.isEmpty()) {
            freqs.remove(freq);
        }
        //  2. put it to higher freq linkedlist
        freqs.computeIfAbsent(freq + 1, o -> new MyLinkedList()).push(n);
        
        if (linkedlist.isEmpty() && minFreq >= freq) {
            minFreq = freq + 1;
        }
        
        n.freq++;
        return n.val;
    }
    
    public void put(int key, int value) {
        Node n = map.get(key);
        if (n != null) {
            n.val = value;
            get(key);
            return;
        }
        
        cap -= 1;
        n = new Node(key, value);
        n.freq = 1;

        // remove tail of min freq if full
        if (cap < 0) {
            MyLinkedList smallest = freqs.get(minFreq);
            if (smallest == null) return;
            
            Node rem = smallest.removeTail();
            map.remove(rem.key);
        }

        minFreq = 1;
        freqs.computeIfAbsent(1, o -> new MyLinkedList()).push(n);
        map.put(key, n);
    }
}

// LRU
class MyLinkedList {
    Node head, tail;
    public MyLinkedList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
    }
    
    public boolean isEmpty() {
        return head.next == tail && tail.pre == head;
    }
    
    public void push(Node n) {
        Node tmp = head.next;
        head.next = n;
        tmp.pre = n;
        n.next = tmp;
        n.pre = head;
    }
    
    public Node remove(Node n) {
        if (isEmpty()) return null;
        
        Node pre = n.pre;
        pre.next = n.next;
        n.next.pre = pre;
        
        n.next = null;
        n.pre = null;
        
        return n;
    }
    
    public Node removeTail() {
        return remove(tail.pre);
    }
}

class Node {
    int key, val, freq;
    Node pre, next;
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

### Method 2 - :turtle: 39ms (32.98%)

```java
class LFUCache {
    Map<Integer, Integer> vals; // key, val
    Map<Integer, Integer> counts;   // key, cnt
    Map<Integer, LinkedHashSet<Integer>> lists; // cnt, keys
    int cap;
    int min = -1;
    public LFUCache(int capacity) {
        vals = new HashMap();
        counts = new HashMap();
        lists = new HashMap();
        cap = capacity;
        lists.put(1, new LinkedHashSet());
    }
    
    public int get(int key) {
        if (!vals.containsKey(key)) return -1;
        
        // update key's freq and remove from prev freq map.
        int cnt = counts.get(key);
        counts.put(key, cnt + 1);
        lists.get(cnt).remove(key);
        
        // update min freq if possible
        if (cnt == min && lists.get(cnt).size() == 0) min++;
        
        // add key to new freq (counts)
        if (!lists.containsKey(cnt + 1)) lists.put(cnt + 1, new LinkedHashSet<>());
        
        lists.get(cnt + 1).add(key);
        
        return vals.get(key);
    }
    
    public void put(int key, int value) {
        if (cap <= 0) return;
        
        // if key exists, use get() function to update maps
        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key);
            return;
        }
        
        if (vals.size() >= cap) {
            int evict = lists.get(min).iterator().next();
            lists.get(min).remove(evict);
            vals.remove(evict);
        }
        
        vals.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```
