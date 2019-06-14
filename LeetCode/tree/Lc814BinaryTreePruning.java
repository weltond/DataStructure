// https://leetcode.com/problems/binary-tree-pruning/

/**
We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.

Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

(Recall that the subtree of a node X is X, plus every node that is a descendant of X.)

Example 1:
Input: [1,null,0,0,1]
Output: [1,null,0,null,1]
 
Explanation: 
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.

Example 2:
Input: [1,0,1,0,0,0,1]
Output: [1,null,1,null,1]

Example 3:
Input: [1,1,0,1,1,0,1,0]
Output: [1,1,0,1,1,null,1]
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
    // ========= Method 1: DFS ===========
    // 0ms
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        
        // if (root.left == null && root.right == null) {
        //     return root.val == 0 ? null : root;
        // }
        
        TreeNode l = pruneTree(root.left);
        TreeNode r = pruneTree(root.right);
        
        root.left = l;
        root.right = r;
        
        return (l == null && r == null && root.val == 0) ? null : root;
    }
}
