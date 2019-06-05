// https://leetcode.com/problems/distribute-coins-in-binary-tree/submissions/

/**
Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.

Input: [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.

Input: [0,3,0]
Output: 3
Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.
*/

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
    // =========== Method: DFS ==============
    public int distributeCoins(TreeNode root) {
        int[] ans = {0};
        dfs(root, ans);
        
        return ans[0];
    }
    // Approach 2: 1ms(41.09%)
    private int dfs(TreeNode root, int[] ans) {
        if (root == null) return 0;
        
        int left = dfs(root.left, ans);
        int right = dfs(root.right, ans);
        
        
        int ret = root.val + left + right - 1;
        // ans[0] += Math.abs(ret);
        ans[0] += Math.abs(left) + Math.abs(right);
        
        return ret;
    }
    // Approach 1: 1ms (41.09%)
    private int dfs(TreeNode root, int[] ans) {
        if (root == null) return 0;
        
        if (root.left == null && root.right == null) {
            int ret = root.val - 1;
            ans[0] += Math.abs(ret);
            return ret;
        }
        
        int left = dfs(root.left, ans);
        int right = dfs(root.right, ans);
        
        
        int ret = root.val + left + right - 1;
        ans[0] += Math.abs(ret);
        
        return ret;
    }
}
