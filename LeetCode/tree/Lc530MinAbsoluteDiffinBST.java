// https://leetcode.com/problems/minimum-absolute-difference-in-bst/

/**
Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
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
    // ========== Method 2: In order =========
    // 1ms (98.48%)
    TreeNode prev = null;
    int diff = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return diff;
    }
    private void inorder(TreeNode root) {
        if (root == null) return;
        
        inorder(root.left);
        if (prev != null) {
            diff = Math.min(diff, Math.abs(root.val - prev.val));
        }
        prev = root;
        
        inorder(root.right);
    } 
    
    // =========== Method 1: Pre order -> Use min, max =========
    // 1ms (98.48%)
    int diff = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return diff;
    }
    
    private void helper(TreeNode root, int min, int max) {
        if (root == null) return;
        
        int d1 = min == Integer.MIN_VALUE ? Integer.MAX_VALUE : root.val - min;
        int d2 = max == Integer.MAX_VALUE ? Integer.MAX_VALUE : max - root.val;
        
        if (d1 <= d2 && d1 < diff) {
            diff = d1;
        } else if (d2 < d1 && d2 < diff) {
            diff = d2;
        }
        
        helper(root.left, min, root.val);
        helper(root.right, root.val, max);
    }
}
