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
