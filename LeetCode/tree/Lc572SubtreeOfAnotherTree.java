// https://leetcode.com/problems/subtree-of-another-tree/

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
    // 0ms
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return helper(s, t, true);
    }
    
    private boolean helper(TreeNode s, TreeNode t, boolean isRoot) {
        if (s == null || t == null) return s == t;
        
        if (s.val != t.val) {
            if (!isRoot) return false;  // Important to check if it is not from root of t
            
            return helper(s.left, t, true) || helper(s.right, t, true);
        } else {
            return helper(s.left, t.left, false) && helper(s.right, t.right, false) ||
                helper(s.left, t, true) || helper(s.right, t, true);
        }
    }
}

class Solution {
    // 6ms (95.54%)
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) return s == t;
 
        return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null || t == null) return s == t;
        
        return s.val == t.val && isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}
