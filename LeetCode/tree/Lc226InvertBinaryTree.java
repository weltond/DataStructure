// https://leetcode.com/problems/invert-binary-tree/


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

    // ========= Method 2: BFS ===========
    // 0ms
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        
        while(!q.isEmpty()) {
            TreeNode n = q.poll();
            
            TreeNode tmp = n.left;
            n.left = n.right;
            n.right = tmp;
            
            // order doesn't matter
            if (n.right != null) {
                q.offer(n.right);
            }
            if (n.left != null) {
                q.offer(n.left);
            }
        }
        
        return root;  
    }
    
    // ============ Method 1: DFS ==============
    // 0ms
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
    }
}
