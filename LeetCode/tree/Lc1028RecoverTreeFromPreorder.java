// https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/

/**
Input: "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]

Input: "1-401--349---90--88"
Output: [1,401,null,349,88,90]

Input: "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]
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
    private int idx = 0;
    public TreeNode recoverFromPreorder(String S) {
        if (S == null || S.length() == 0) return null;
        
        TreeNode dummy = new TreeNode(0);
        
        helper(dummy, S, 0, true);
        
        return dummy.left;
    }
    
    private void helper(TreeNode p, String s, int level, boolean isLeft) {
        if (idx > s.length()) return;
        
        int lvl = findLvl(s);
        if (lvl != level) {
            idx -= lvl;
            return;
        }
        
        int val = getVal(s);
        TreeNode n = new TreeNode(val);
        
        if (isLeft) p.left = n;
        else p.right = n;
        
        helper(n, s, level + 1, true);
        helper(n, s, level + 1, false);
    }
    
    private int findLvl(String s) {
        int lvl = 0;
        while (idx < s.length() && s.charAt(idx) == '-') {
            lvl++;
            idx++;
        }
        return lvl;
    }
    
    private int getVal(String s) {
        int val = 0, len = s.length();
        while (idx < len && s.charAt(idx) != '-') {
            val = val * 10 + s.charAt(idx++) - '0';
        }
        
        return val;
    }
}
