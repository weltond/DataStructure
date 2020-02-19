// https://leetcode.com/problems/range-sum-of-bst/

/**
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

 

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23
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
public class Solution {
    /**
     * @param root: the root node
     * @param L: an integer
     * @param R: an integer
     * @return: the sum
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        // write your code here.
    
        if (root == null) return 0;
        

        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        } else if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        } else {
            return rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R) + root.val;
        }
    }
}

class Solution {
    // ========== Method 1: DFS =============
    // Approach 1: 0ms
    public int rangeSumBST(TreeNode root, int l, int r) {
        if (root == null) return 0;
        
        int res = 0;
        
        if (l <= root.val) {
            res += root.val + rangeSumBST(root.left, l, r);
        }
        if (r >= root.val) {
            res += root.val + rangeSumBST(root.right, l, r);
        }
        
        return res - root.val;
    }
    
    // Approach 2: 1ms(61.11%)
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val <= R && root.val >= L) {
            return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);
        }
        return rangeSumBST(root.right, L, R)+rangeSumBST(root.left, L, R);
    }
    
    // Approach 3
    int ans;
    public int rangeSumBST(TreeNode root, int L, int R) {
        ans = 0;
        dfs(root, L, R);
        return ans;
    }

    public void dfs(TreeNode node, int L, int R) {
        if (node != null) {
            if (L <= node.val && node.val <= R)
                ans += node.val;
            if (L < node.val)
                dfs(node.left, L, R);
            if (node.val < R)
                dfs(node.right, L, R);
        }
    }
}
