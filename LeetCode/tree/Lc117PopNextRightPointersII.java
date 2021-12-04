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
// ======== BFS (1ms 67.6%) ============
class Solution {
    public Node connect(Node root) {
        Deque<Node> q = new LinkedList<>();
        if (root == null) return null;

        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node node = q.poll();

                if (prev != null) prev.next = node;
                prev = node;

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }

        return root;
    }
}

class Solution {
    // ========== Method 1: DFS ============
    // 0ms (100%)
    public Node connect(Node root) {
        if (root == null) return null;
        
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                root.left.next = findNextToConnect(root.next);
            }
        }
        if (root.right != null) {
            root.right.next = findNextToConnect(root.next);
        }
        
        /**
               1  ->   2
             /  \     / \
            3 -> 4 ->5   6
           /              \
         7                 8
         Consider above. If recurse left first, 7 can not find next node.
        */
        connect(root.right);    
        connect(root.left);
        
        return root;
    }
    private Node findNextToConnect(Node root) {
        if (root == null) return null;
        
        if (root.left != null) return root.left;
        if (root.right != null) return root.right;
        
        return findNextToConnect(root.next);
        
    }
}

class Solution {
    // ======== Method 1: BFS =============
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
    
    // ======== Method 2: DFS =============
    // Unlike perfect tree
    // do dfs for root.right first this way because right sub-tree's next pointer comes later than left subtree !
    public Node connect(Node root) {
        if (root == null) return root;
        
        if (root.left == null && root.right == null) return root;
        
        if (root.left != null) {
            root.left.next = root.right;
            System.out.println("CO: " + root.left.val + ", " + (root.left.next == null ? null : root.left.next.val) );
        }
        
        if (root.next != null) {
            Node cur = root.next;
            while (cur != null) {
                if (cur.left != null || cur.right != null) {
                    break;
                }
                cur = cur.next;
            }
            Node tmp = root.right == null ? root.left : root.right;
            
            Node nextTmp = cur == null ? null : cur.left == null ? cur.right : cur.left;
            tmp.next = nextTmp;
            System.out.println(tmp.val + ", " + (nextTmp == null ? null : nextTmp.val));
        }
        
        connect(root.right);
        connect(root.left);
        
        return root;
    }
}
