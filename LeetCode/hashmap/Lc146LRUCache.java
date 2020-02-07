// https://leetcode.com/problems/lru-cache/
class LRUCache {
    Node head, tail;
    int k;
    Map<Integer, Node> map;
    public LRUCache(int capacity) {
        k = capacity;
        head = new Node(0,0);
        tail = new Node(0,0);
        map = new HashMap();
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        
        Node n = map.get(key);
        
        putHead(n);

        return n.val;
    }
    
    public void put(int key, int value) {
        Node n = null;
        if (map.containsKey(key)) {
            n = map.get(key);
            n.val = value;
            get(key);
            return;
        } 
        
        n = new Node(key, value);
        map.put(key, n);
        putHead(n);
        k--;
        
        if (k < 0) {
            map.remove(tail.pre.key);   // remove key from map first
            remove(tail.pre); 
        }

    }
    
    private void putHead(Node n) {
        if (n.pre != null) {
            remove(n);
        }
        
        Node next = head.next;
        head.next = n;
        n.pre = head;
        
        n.next = next;
        next.pre = n;
    }
    
    private void remove(Node n) {
        if (n == head) return;  // cannot delete head when k is initial 0.
        
        n.pre.next = n.next;
        n.next.pre = n.pre;
        n.next = null;
        n.pre = null;
        
    }
}

class Node {
    Node pre;
    Node next;
    int val, key;
    public Node(int key, int val) {
        this.val = val;
        this.key = key;
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class LRUCache {

    Node head = new Node(-1, -1), tail = new Node(-1, -1);
    Map<Integer, Node> map;
    int n;
    public LRUCache(int capacity) {
        map = new HashMap();
        n = capacity;
        head.next = tail;
        tail.pre = head;
        if (n == 0) return;
    }
    
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        
        Node n = map.get(key);
        
        pushToHead(n);
        
        return n.val;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.val = value;  
        } else {
            node = new Node(key, value);
            map.put(key, node);
            n -= 1;
        }
        
        pushToHead(node);
        
        
        if (n < 0) {
            removeTail();
            n = 0;
        }
    }
    
    public void pushToHead(Node n) {
        Node pre = n.pre;
        if (pre != null) {
            pre.next = n.next;
            n.next.pre = pre;
        }
        
        Node next = head.next;
        n.pre = head;
        n.next = next;
        next.pre = n;
        head.next = n;
    }
    
    public void removeTail() {
        Node n = tail.pre;
        tail.pre = n.pre;
        n.pre.next = tail;

        //System.out.println(n.key);
        n.next = null;
        n.pre = null;
        map.remove(n.key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class Node {
    int val, key;
    Node pre, next;
    public Node(int key, int val) {
        this.val = val;
        this.key = key;
    }
}

// 20ms (90.74%)
class LRUCache {

    Node head, tail;
    int cap;
    Map<Integer, Node> map; // key, node
    public LRUCache(int capacity) {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        cap = capacity;
        map = new HashMap();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        
        Node n = map.get(key);
        
        putToHead(n, true);
        
        return n.val;
    }
    
    public void put(int key, int value) {
        if (cap <= 0) return;
        
        Node n = null;
        if (map.containsKey(key)) {
            // update value in map
            n = map.get(key);
            n.val = value;
            // use get() to update its position (latest to head)
            get(key);
            return;
        }
        
        if (map.size() >= cap) {
            // remove last element (tail's prev) out of the list
            Node evict = tail.pre;
            evict.pre.next = tail;
            tail.pre = evict.pre;
            
            map.remove(evict.key);
        }
        
        n = new Node(key, value);
        map.put(key, n);
        
        putToHead(n, false);
    }
    
    private void putToHead(Node n, boolean isExist) {
        if (isExist) {
            Node prev = n.pre;
            prev.next = n.next;
            n.next.pre = prev;
        }
        
        Node move = head.next;
        head.next = n;
        n.pre = head;
        n.next = move;
        move.pre = n;
    }
}

class Node {
    Node pre, next;
    int key, val;
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
        pre = null;
        next = null;
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class LRUCache {
    ListNode head, tail;
    int size;
    Map<Integer, ListNode> map;
    public LRUCache(int capacity) {
        size = capacity;
        head = new ListNode(0, 0);
        tail = new ListNode(0, 0);
        
        head.next = tail;
        tail.prev = head;
        map = new HashMap();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        
        ListNode n = map.get(key);
        
        pushToHead(n);
        
        return n.val;
    }
    
    // 1. not exist
    //      1) full: put to head and then remove tail
    //      2) not full: put to head
    // 2. exist: update and then put to head.
    public void put(int key, int value) {
        ListNode n = null;
        boolean isFull = false;
        if (map.containsKey(key)) {
            n = map.get(key);
            n.val = value;
        } else {
            n = new ListNode(key, value);
            size -= 1;
            if (size < 0) isFull = true;
            map.put(key, n);
        }
        pushToHead(n);
        
        if (isFull) {
            removeTail();
        }
        
    }
    
    private void pushToHead(ListNode node) {
        if (node.next != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        } 
        ListNode tmp = head.next;
        head.next = node;
        node.next = tmp;
        node.prev = head;
        tmp.prev = node;
    }
    
    private void removeTail() {
        ListNode n = tail.prev;
        map.remove(n.key);
        n.prev.next = tail;
        tail.prev = n.prev;
    }
}

class ListNode {
    int key;
    int val;
    ListNode prev;
    ListNode next;
    public ListNode(int k, int v) {
        key = k;
        val = v;
        prev = null;
        next = null;
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//LRUCache cache = new LRUCache( 2 /* capacity */ );
/**
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/
class LRUCache {
    Map<Integer, ListNode> map;
    int cap;
    ListNode tail, head;
    public LRUCache(int capacity) {
        map = new HashMap();
        cap = capacity;
        init();
    }
    private void init() {
        head = new ListNode(0, 0);
        tail = new ListNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        
        ListNode node = map.get(key);
        
        putToHead(node);
        
        return node.val;
    }
    
    public void put(int key, int value) {
        ListNode node = map.get(key);
        
        // if exist, just update its value and put to head.
        if (node != null) {
            node.val = value;
            putToHead(node);
            return;
        }
        
        // if not exist, put to head
        node = new ListNode(key, value);
        node.val = value;
        node.key = key;
        putToHead(node);
        
        // if cap is full, evict from tail
        if (cap > 0) {
            cap--;
        }else {
            ListNode delete = tail.prev;
            evictNodeFrom(tail);
            map.remove(delete.key);
        }

    }
    
    // private void print() {
    //     ListNode tmp = head;
    //     int i = 0;
    //     while (tmp != null) {
    //         i++;
    //         System.out.print(tmp.val + " ");
    //         tmp = tmp.next;
    //     }
    //     System.out.println("total: " + (i - 2));
    // }
    
    private void putToHead(ListNode node) {
        if (node.prev != null) {
            node = evictNodeFrom(node.next);
        }
        
        ListNode tmp = head.next;
        head.next = node;
        tmp.prev = node;
        
        node.next = tmp;
        node.prev = head;
        
        map.put(node.key, node);
    }
    
    private ListNode evictNodeFrom(ListNode node) {
        ListNode tmp = node.prev;
        if (tmp == head) return null;    // so if cap is full, we need to insert to head first and then delete from tail
        
        tmp.prev.next = node;
        tmp.next.prev = tmp.prev;
        
        tmp.next = null;
        tmp.prev = null;

        return tmp;
    }
}

class ListNode {
    ListNode prev;
    ListNode next;
    int val;
    int key;
    public ListNode(int key, int val) {
        this.val = val;
        this.key = key;
        prev = null;
        next = null;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


class LRUCache {
    // Push to Tail, Remove from Head
    // 63ms (83.14%)
    Map<Integer, Node> map;
    Node head;
    Node tail;
    int size;
    public LRUCache(int capacity) {
        map = new HashMap();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
        size = capacity;
    }
    
    // Get if exists and then put it to tail
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node n = map.get(key);
        
        if (n.next != tail) {
            putToTail(n);    
        }
        
        return n.val;
    }
    
    // If not exists: Put to tail if not full, otherwise remove from head
    // If exists, put to tail and then update
    public void put(int key, int value) {
        Node n = null;
        
        if (map.containsKey(key)) {
            n = map.get(key);
            if (n.next != tail) // IMPORTANT!
                putToTail(n);
            n.val = value;
            return;
        }
        
        n = new Node(key, value);
        map.put(key, n);
        putToTail(n);
        
        // if full, remove from head first
        if (size == 0) {
            removeHead();
        } else {
            size--;
        }

    }
    
    private void putToTail(Node n) {
        Node tmp = tail.pre;
        Node preN = n.pre;
        Node nextN = n.next;
        
        n.next = tail;
        tmp.next = n;
        n.pre = tmp;
        tail.pre = n;
        
        if (preN != null && nextN != null) {
            preN.next = nextN;
            nextN.pre = preN;
        } 
    }
    
    private void removeHead() {
        Node tmp = head.next;
        if (tmp.next == null) {
            System.out.println(tmp.val + ", " + tmp.key);
        }
        tmp.next.pre = head;
        head.next = tmp.next;
        
        tmp.next = null;
        tmp.pre = null;
        map.remove(tmp.key);
    }
}

class Node {
    int key;
    int val;
    Node pre;
    Node next;
    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
/**
* Your LRUCache object will be instantiated and called as such:
* LRUCache obj = new LRUCache(capacity);
* int param_1 = obj.get(key);
* obj.put(key,value);
*/
 
 
 
// 65ms
class LRUCache {
public Map<Integer, Node> map;   // key - Node pair
private int cnt;
private int cap;
private Node dummyHead; // least recent used
private Node dummyTail; 

public LRUCache(int capacity) {
    map = new HashMap<>();
    this.cnt = 0;
    this.cap = capacity;
    dummyHead = new Node(0,0);
    dummyTail = new Node(0,0);
    dummyHead.next = dummyTail;
    dummyTail.prev = dummyHead;
}

public int get(int key) {
    if (!map.containsKey(key)) return -1;
    Node cur = map.get(key);

    remove(cur);

    insert(cur);

    return cur.val;
}

private void remove(Node n) {
    Node tmp = n.prev;
    Node tmp2 = n.next;

    tmp.next = tmp2;
    tmp2.prev = tmp;
}

private void insert(Node n) {
    Node oldHead = dummyHead.next;
    n.next = oldHead;
    n.prev = dummyHead;
    oldHead.prev = n;
    dummyHead.next = n;
}

public void put(int key, int value) {
    Node n = null;

    if (!map.containsKey(key)) {
        n = new Node(key, value);
        map.put(key, n);
        cnt++;
    } else {
        n = map.get(key);
        n.val = value;

        remove(n);
    }

    // put this key to head
    insert(n);


    // if full, remove the last
    if (cnt > this.cap) {
        Node oldLast = dummyTail.prev;
        remove(oldLast);

        map.remove(oldLast.key);
        cnt--;
    }
}
}

class Node {
int key;
int val;
Node next;
Node prev;

public Node(int key, int val) {
    this.key = key;
    this.val = val;
    next = null;
    prev = null;
}
}

/**
* Your LRUCache object will be instantiated and called as such:
* LRUCache obj = new LRUCache(capacity);
* int param_1 = obj.get(key);
* obj.put(key,value);
*/
