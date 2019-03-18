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
