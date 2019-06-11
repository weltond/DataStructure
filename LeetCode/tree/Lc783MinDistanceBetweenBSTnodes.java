// https://leetcode.com/problems/minimum-distance-between-bst-nodes/

/**
Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.

Example :

Input: root = [4,2,6,1,3,null,null]
Output: 1
Explanation:
Note that root is a TreeNode object, not an array.

The given tree [4,2,6,1,3,null,null] is represented by the following diagram:

          4
        /   \
      2      6
     / \    
    1   3  

while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
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
    // ================== Method 2: DFS in-order ===================
    // 0ms
    int ans = Integer.MAX_VALUE;
    long prev = Long.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {
        dfs(root);
        
        return ans;
    }
    
    private void dfs(TreeNode root) {
        if (root == null) return;
        
        dfs(root.left);
        
        if (ans > Math.abs(prev - root.val)) {
            ans = (int) Math.abs(prev - root.val); // If use ans = min(ans, ..) it will cause negative results.
        }
        prev = root.val;
        
        dfs(root.right);
    }
    
    // ================== Method 1: DFS post order ===================
    // 0ms
    int ans;
    public int minDiffInBST(TreeNode root) {
        ans = Integer.MAX_VALUE;
        
        dfs(root);
        
        return ans;
    }
    
    private int[] dfs(TreeNode root) {
        if (root == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        
        if (root.left == null && root.right == null) return new int[]{root.val, root.val};
        
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        
        int lmin = left[0], lmax = left[1], rmin = right[0], rmax = right[1];
        
        ans = Math.min(ans, Math.min(Math.abs(root.val - lmax), Math.abs(root.val - rmin)));
        
        // don't forget to compare with current root
        return new int[]{Math.min(root.val, lmin), Math.max(root.val, rmax)};
    }
}
