// https://leetcode.com/problems/smallest-string-starting-from-leaf/


/**
Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.

Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

(As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)

Input: [0,1,2,3,4,3,4]
Output: "dba"
Input: [2,2,1,null,1,0,null,0]
Output: "abc"
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
    
    // ========== Approach 3: Most Elegant ========
    // 1ms (99.18%)
    private String ans = "~"; // dummy value '~' > 'z'
    
    public String smallestFromLeaf(TreeNode root) {
        return dfs(root, "");
    }
    
    private String dfs(TreeNode n, String str) {
        if (n == null) return ans;
        
        str = (char)('a' + n.val) + str;
        
        if (n.left == null && n.right == null && str.compareTo(ans) < 0){
            ans = str;
        }
        dfs(n.left, str);
        dfs(n.right, str);
        
        return ans;
    }
    
    String res = null;
    // ========== Approach 2: Elegant ===========
    // 2ms (79.44%)
    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        
        return res;
    }
    
    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append((char)('a' + root.val));
        
        if (root.left == null && root.right == null) {
            sb.reverse();
            String s = sb.toString();
            sb.reverse();
            if (res == null) {
                res = s;
            }else if (s.compareTo(res) < 0) {
                res = s;
            }
            // return;  // CANNOT return here, because we need to delete last char.
        }
        
        dfs(root.left, sb);
        dfs(root.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
    
    // ========== Approach 1 ===========
    // 2ms (79.44%)
    
    public String smallestFromLeaf(TreeNode root) {
        if (root == null) return res.toString();
        
        dfs(root, new StringBuilder());

        return new StringBuilder(res).reverse().toString();
    }
    
    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        
        if (root.left == null && root.right == null) {
            sb.append((char)(root.val + 'a'));
            
            res = compare(res, sb.toString());
            
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        
        sb.append((char)(root.val + 'a'));
        dfs(root.left, sb);
        dfs(root.right, sb);
        sb.deleteCharAt(sb.length() - 1);
        
    }
    
    private String compare(String s1, String s2) {
        if (s1 == null) return s2;
        
        StringBuilder sb1 = new StringBuilder(s1);
        StringBuilder sb2 = new StringBuilder(s2);
        
        int ret = sb1.reverse().toString().compareTo(sb2.reverse().toString());
        
        return ret <= 0 ? s1 : s2;
    }
}
