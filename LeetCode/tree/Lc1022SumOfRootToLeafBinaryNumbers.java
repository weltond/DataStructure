// https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/submissions/

/**
Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers.
Input: [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22

Note:

The number of nodes in the tree is between 1 and 1000.
node.val is 0 or 1.
The answer will not exceed 2^31 - 1.
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
    // ========== Method 2: Elegant ==========
    // 0ms (100%)
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode root, int val) {
        if (root == null) return 0;
        
        val = val * 2 + root.val;
        
//      if (root.left == null && root.right == null) return val;      
//      return dfs(root.left, val) + dfs(root.right, val);
        
        return root.left == root.right ? val : dfs(root.left, val) + dfs(root.right, val);
    }
    // ========== Method 1: Naive ============
    // 1ms (32.67%)
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return dfs(root, "");
    }
    
    private int dfs(TreeNode root, String parent) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            String s = Integer.toString(root.val) + parent;
            return convert(s);
        }
        
        int left = dfs(root.left, Integer.toString(root.val) + parent);
        int right = dfs(root.right, Integer.toString(root.val) + parent);
        
        return left + right;
    }
    
    private int convert(String s) {
        int base = 1, ret = 0, len = s.length();
        
        for (int i = 0; i < len; i++) {
            int tmp = s.charAt(i) - 48;
            ret += tmp * base;
            base *= 2;
        }
//         while (val > 0) {
//             int tmp = val % 10;
//             ret += tmp * base;
            
//             val = val / 10;
//             base *= 2;
//         }
        
        return ret;
    }
}
