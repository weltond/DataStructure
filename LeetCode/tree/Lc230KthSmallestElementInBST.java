// https://leetcode.com/problems/kth-smallest-element-in-a-bst/

/**
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
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
    int c = 0;
    int value = 0;
    // Approach 2: 0ms
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return -1;
        
        this.c = k;
        dfs(root);
        
        return value;
    }
    
    private void dfs(TreeNode root) {
        if (root.left != null) dfs(root.left);
        c--;
        if (c == 0) {
            value = root.val;
            return;
        }
        if (root.right != null) dfs(root.right);
    }
    
    // Approach 1:
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> ans = new ArrayList();
        get(root, k, ans);
        //return value;
        return ans.get(k - 1);
    }
    
    public void get(TreeNode root, int k, List<Integer> count) {
        if (root == null) return;
        
        get(root.left, k, count);
        //c += 1;
        //if (c == k) value = root.val;
        count.add(root.val);
        get(root.right, k, count);
        
    }
}
