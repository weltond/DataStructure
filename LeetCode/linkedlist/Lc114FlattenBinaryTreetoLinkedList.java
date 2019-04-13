// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

/**
For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
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
    // ============ Method 2: Post-order =========
    // 0ms
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        // right first
        flatten(root.right);
        flatten(root.left);
        
        root.right = prev;
        root.left = null;
        prev = root;
    }
    // ============ Method 1: Pre-order ===========
    // 0ms
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        helper(root);
    }
    
    private TreeNode helper(TreeNode root) {
        if (root == null) return null;
        
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        
        // Get left subtree's right most node
        TreeNode tmp = left, prev = null;
        while (tmp != null) {
            prev = tmp;
            tmp = tmp.right;
        }
        
        root.left = null;
        
        // if exist, make the right most node as root for root's right subtree
        if (prev != null) {
            root.right = left;
            prev.right = right;
        }
        // else {
        //     root.right = right;
        // }
            
        
        return root;
    }
}
