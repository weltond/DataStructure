// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

/**
Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
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
    // =========== Method 1 ============
    // 0ms (100%)
    public TreeNode bstFromPreorder(int[] preorder) {
        return dfs(preorder, 0, preorder.length - 1);
    }
    
    private TreeNode dfs(int[] preorder, int start, int end) {
        if (start > end) return null;
        
        int rootVal = preorder[start];
        TreeNode root = new TreeNode(rootVal);
        
        if (start == end) return root;
        
        int stop = end + 1; // avoid only left child.
        for (int i = start + 1; i <= end; i++) {
            if (preorder[i] > rootVal) {
                stop = i;
                break;
            }
        }
        
        root.left = dfs(preorder, start + 1, stop - 1);
        root.right = dfs(preorder, stop, end);
        
        return root;
    }
}
