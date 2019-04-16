// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

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
    // ============= Method 1: DFS ==============
    // 8ms (43.32%)
    private int idx = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length || preorder.length == 0) return null;
        return dfs(preorder, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode dfs(int[] preorder, int[] inorder, int left, int right) {
        if (left > right) return null;  // DONOT forget! [1,2] [2,1]
        
        if (left == right) {
            return new TreeNode(preorder[idx++]);
        }
        
        int rootVal = preorder[idx++];
        TreeNode root = new TreeNode(rootVal);
        
        int rootPos = findPos(inorder, rootVal);
        
        root.left = dfs(preorder, inorder, left, rootPos - 1);
        root.right = dfs(preorder, inorder, rootPos + 1, right);
        
        return root;
    }
    
    private int findPos(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        
        return -1;
    }
}
