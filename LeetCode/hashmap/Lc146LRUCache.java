// https://leetcode.com/problems/lru-cache/


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
