// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

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
    // ============== Method 1: DFS ================
    // 3ms (82.04%)
    private int idx;
    private Map<Integer, Integer> inPosMap; // <val, pos>
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length || inorder.length == 0) return null;
        
        inPosMap = new HashMap();
        
        idx = inorder.length - 1;
        
        for (int i = 0; i < inorder.length; i++) {
            inPosMap.put(inorder[i], i);
        }
        
        return dfs(inorder, postorder, 0, inorder.length - 1);
    }
    
    private TreeNode dfs(int[] inorder, int[] postorder, int left, int right) {
        if (left > right) return null;
        if (left == right) {
            return new TreeNode(postorder[idx--]);
        }
        
        int rootVal = postorder[idx--];
        TreeNode root = new TreeNode(rootVal);
        
        int rootPos = inPosMap.get(rootVal);
        
        // right first because idx is decreased from right to left
        root.right = dfs(inorder, postorder, rootPos + 1, right);
        root.left = dfs(inorder, postorder, left, rootPos - 1);
        
        return root;
    }
}
