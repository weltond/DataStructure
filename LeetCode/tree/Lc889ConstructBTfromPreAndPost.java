// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/


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
    // =========== Method 1: DFS ============
    // 1ms(100%)
    int[] pre, post;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        
        return dfs(0, 0, pre.length);
    }
    // If we knew how many nodes the left branch had, we could partition these arrays as such, and use recursion to generate each branch of the tree.
    private TreeNode dfs(int preStart, int postStart, int len) {
        if (len == 0) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        if (len == 1) return root;
        
        int L = 1;
        // find left length
        for (; L < len; L++) {
            // preStart + 1 => left, postStart => left
            if (pre[preStart + 1] == post[postStart + L - 1]) {
                break;
            }
        }
        
        root.left = dfs(preStart + 1, postStart, L);
        root.right = dfs(preStart + 1 + L, postStart + L, len - L - 1); 
        
        return root;
    }
}
