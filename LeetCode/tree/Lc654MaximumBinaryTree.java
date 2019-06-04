// https://leetcode.com/problems/maximum-binary-tree/

/**
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
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
    // =========== Method 1: DFS ============
    // 2ms (100%)
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }
    
    private TreeNode dfs(int[] nums, int left, int right) {
        if (left > right) return null;
        if (left == right) return new TreeNode(nums[left]);
        
        int rootPos = findMaxPos(nums, left, right);
        TreeNode root = new TreeNode(nums[rootPos]);
        
        root.left = dfs(nums, left, rootPos - 1);
        root.right = dfs(nums, rootPos + 1, right);
        
        return root;
    }
    
    private int findMaxPos(int[] nums, int left, int right) {
        int max = nums[left], pos = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > max) {
                max = nums[i];
                pos = i;
            }
        }
        
        return pos;
    }
}
