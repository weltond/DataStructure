// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        Node head = root;
        
        while (head != null) {
            head = bfs(head);
        }
        
        return root;
    }
    
    private Node bfs(Node root) {
        Node dummy = new Node(0);
        //dummy.next = root.left != null ? root.left : root.right;
        Node cur = dummy;
        
        while (root != null) {
            if (root.left != null) {
                cur.next = root.left;
                cur = cur.next;
            }
            if (root.right != null) {
                cur.next = root.right;
                cur = cur.next;
            }
            root = root.next;
        }
        
        
        return dummy.next;
    }
    
    // Unlike perfect tree
    // CANNOT do DFS this way because right sub-tree's next pointer comes later than left subtree
//     public Node connect(Node root) {
//         if (root == null) return root;
        
//         if (root.left == null && root.right == null) return root;
        
//         if (root.left != null) {
//             root.left.next = root.right;
//             System.out.println("CO: " + root.left.val + ", " + (root.left.next == null ? null : root.left.next.val) );
//         }
        
//         if (root.next != null) {
//             Node cur = root.next;
//             while (cur != null) {
//                 if (cur.left != null || cur.right != null) {
//                     break;
//                 }
//                 cur = cur.next;
//             }
//             Node tmp = root.right == null ? root.left : root.right;
            
//             Node nextTmp = cur == null ? null : cur.left == null ? cur.right : cur.left;
//             tmp.next = nextTmp;
//             System.out.println(tmp.val + ", " + (nextTmp == null ? null : nextTmp.val));
//         }
        
//         connect(root.left);
//         connect(root.right);
        
//         return root;
//     }
}
