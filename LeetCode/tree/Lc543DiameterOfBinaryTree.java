// https://leetcode.com/problems/diameter-of-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // 4ms
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] ans = new int[1];
        
        dfs(root, ans);
        
        return ans[0];
    }
    
    private int dfs(TreeNode root, int[] ans) {
        if (root == null) return 0;
        
        int left = dfs(root.left, ans);
        int right = dfs(root.right, ans);
        
        ans[0] = Math.max(ans[0], left + right);
        
        return Math.max(left, right) + 1;
    }
}
