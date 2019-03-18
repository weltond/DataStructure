// https://leetcode.com/problems/copy-list-with-random-pointer/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    // ========= Method 2: Interweaving without HashMap ========
    // 0ms. O(1) space
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        Node cur = head;
        
        // 1. A->B->C ===> A->A'->B->B'->C->C'
        while (cur != null) {
            Node tmp = cur.next;
            Node n = new Node(cur.val, null, null);
            cur.next = n;
            n.next = tmp;
            cur = tmp;
        }
        
        
        Node root = head.next;
        cur = head;
        // 2. connect random
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;  
            Node tmp = cur.next.next;
            //cur.next.next = tmp == null ? null : tmp.next;
            cur = tmp;
        }
        
        cur = head;
        Node dummy = new Node(0);
        Node cpy = dummy;
        // 3. extract the copied nodes
        while (cur != null) {
            cpy.next = cur.next;
            cur.next = cur.next.next;
            cpy = cpy.next;
            cur = cur.next;
            // Node tmp = cur.next.next;
            // cur.next.next = tmp == null ? null : tmp.next;
            // cur = tmp;
        }
        
        return dummy.next;
    }
    
    // ========= Method 1: Recursion with HashMap ==========
    // 1ms
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        Map<Node, Node> map = new HashMap();
        
        return helper(head, map);
    }
    
    private Node helper(Node head, Map<Node, Node> map) {
        if (head == null || map.containsKey(head)) return map.get(head);
        
        Node root = new Node(head.val, null, null);
        
        map.put(head, root);
        
        root.next = helper(head.next, map);
        root.random = helper(head.random, map);
        
        return root;
    }
}
