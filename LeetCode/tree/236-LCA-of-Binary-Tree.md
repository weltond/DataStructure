//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    // =========== Method 2: Iterative ==============
    // Time = O(n), Space = O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        // Hashmap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap();
        
        parent.put(root, null);
        stack.push(root);
        
        // Iterate until we find both the nodes p and q 
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            
            // while traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        System.out.println(parent);
        // ancestors set() for node p
        Set<TreeNode> ancestors = new HashSet();
        
        // process all ancestors for node p using parent pointers
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        
        // the first ancestor of q which appears in p's ancestor set() is res
        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        
        return q;
    }
    
    // =========== Method 1: Recursion ==============
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (root == q || root == p) return root;
        
        // here, root != p && q
        if (left == null && right == null) return null;
        else if (left != null && right != null) {
            //System.out.println(left.val + ", " + right.val);
            return root;
        }
        else {
            //System.out.println(root.val + " -> its left is null? : " + (left == null));
            return left == null ? right : left;
            
        }
    }
}
