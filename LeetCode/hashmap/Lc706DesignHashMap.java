/**
Two method: Open addressing & LinkedList
*/
// Method 2: JDK1.8 Style
/**
Data Structure
lookup table. An array of bucket, each bucket is associated with a linked list

How to index bucket
Step 1. Key could be arbitrary type, we have to translate the key to an interger. Fortunately, each java object has a hashCode() function, need only call Key's hashCode().

Step 2. The interger obtained in the previous step may have bad quality. Amelirate with HashMap's internal hash().

Step 3. mod by table length.

hash(): XORs with higher 16 bits. Rationale: spread the impact of higher bits downward. Otherwise higher bits would never be used in index calculations if the table length is small;

When to resize
number of entries > loadFactor * capacity, where capacity is consistent to the table length.

How to resize
Prerequisite: power of two table length, power of tow expansion.

Observation: For each bucket in the old table, associated elements must either stay at same index or move forward with a fixed offset in the new table. The offset equals to old table length. In short, one old bucket is splitted into two new bucket, the index difference is exactly old table length.

Advantage: each bucket is independent to each other. Parallelization is feasible.
*/
class MyHashMap {

    /** Initialize your data structure here. */
    float loadFactor = 0.75f;
    int size = 0;
    int cap = 16;
    Node[] table;
    public MyHashMap() {
        table = new Node[cap];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = hash(key);
        int idx = hash & (cap-1);
        Node cur = table[idx];
        while(cur!=null && cur.key != key){
            cur = cur.next;
        }
        if(cur == null){
            table[idx] = new Node(key, value, hash, table[idx]);
            size++;
        }
        else{
            cur.val = value;
        }
        if(size > cap * loadFactor){
            resize();
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int idx = hash(key) & (cap-1);
        Node cur = table[idx];
        while(cur != null && cur.key!=key){
            cur = cur.next;
        }
        return cur == null ? -1 : cur.val;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int idx = hash(key) & (cap-1);
        Node cur = table[idx];
        Node pre = null;
        while( cur != null && cur.key!=key){
            pre = cur;
            cur = cur.next;
        }
        if(cur!=null){
            size--;
            if(pre == null){
                table[idx] = cur.next;
            }
            else{
                pre.next = cur.next;
            }
        }
    }

    private static int hash(int key){
        int h;
        return (h=Integer.valueOf(key).hashCode()) ^ (h >>> 16);
    }

    private void resize(){
        int oldCap = table.length;
        cap = oldCap << 1;
        Node[] newTable = new Node[cap];
        for(int i=0; i<oldCap; i++){
            Node lohead = new Node(-1, -1, -1, null);
            Node lotail = lohead;
            Node hihead = new Node(-1, -1, -1, null);
            Node hitail = hihead;
            Node cur = table[i];
            while(cur != null){
                int idx = hash(cur.key);
                Node next = cur.next;
                if((cur.hash & oldCap) == 0){
                    lotail.next = cur;
                    lotail = lotail.next;
                    lotail.next = null;
                }
                else{
                    hitail.next = cur;
                    hitail = hitail.next;
                    hitail.next = null;
                }
                cur = next;
            }
            newTable[i] = lohead.next;
            newTable[i+oldCap] = hihead.next;
        }
        table = newTable;
    }

    private class Node{
        int key;
        int val;
        int hash;
        Node next;
        Node(int key, int val, int hash, Node next){
            this.key= key;
            this.val = val;
            this.hash = hash;
            this.next = next;
        }
    }
}

// Method 1: linked list
class MyHashMap {
    final Node[] arr;
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        arr = new Node[10000];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = getIndex(key);
        
        // if arr is null, create a dummy node
        if (arr[hash] == null) arr[hash] = new Node(-1, -1);
        
        // otherwise, search for the same key
        Node node = getNode(arr[hash], key);
        
        // node is garunteed not null because we have a dummy head here
        // if key not exists
        if (node.next == null) node.next = new Node(key, value);
        // if exist, update value
        else node.next.val = value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = getIndex(key);
        
        Node head = arr[hash];
        if (head == null) return -1;
        
        Node node = getNode(head, key);
        
        // if key not exist
        if (node.next == null) return -1;
        else return node.next.val;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = getIndex(key);
        
        Node head = arr[hash];
        if (head == null) return;
        
        Node node = getNode(head, key);
        
        // if key exists
        if (node.next != null) node.next = node.next.next;
        // otherwise do nothing
    }
    
    // =========== HELPER FUNCTION =============
    public int getIndex(int key) { return Integer.hashCode(key) % arr.length;}
    
    // Find the available slot for incoming key value pair
    public Node getNode(Node head, int key) {
        Node node = head;
        Node prev = null;
        
        while (node != null && node.key != key) {
            prev = node;
            node = node.next;
        }
        
        return prev;
    }
    
    class Node {
        int val;
        int key;
        Node next;
        Node(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */