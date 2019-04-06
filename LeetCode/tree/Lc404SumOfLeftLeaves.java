// https://leetcode.com/problems/sum-of-left-leaves/

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
    // ============ Recursion ============
    // 0ms
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;  // if only root, should return 0.
        
        return dfs(root);
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        
        int left = dfs(root.left);
        // return 0 if 1) root.right is null or 2) root.right is leaf node.
        int right = root.right == null ? 0 : root.right.left == null && root.right.right == null ? 0 : dfs(root.right);
        
        return left + right;
    }
}
