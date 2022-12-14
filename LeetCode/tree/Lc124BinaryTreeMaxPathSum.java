/**
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);

        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        res = Math.max(res, root.val + left + right);

        return Math.max(0, root.val + Math.max(left, right));
    }
}

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
    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);

        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));

        res = Math.max(res, left + right + root.val);

        return root.val + Math.max(left, right);
    }
}

class Solution {
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);

        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 1. path can be a tree 
        int includeCur = left + right + root.val;
        // 2. path can be single line
        int notIncludeCur =  Math.max(left, right) + root.val;
           
        res = Math.max(res, Math.max(includeCur, notIncludeCur));
           
        // We should only return single line path to 
        //    its parent. If single path is negative,
        //    return 0 meaning we don't chose the path.
        return Math.max(0, notIncludeCur);
    }
}

class Solution {
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        
        int[] res = new int[]{Integer.MIN_VALUE};
        
        dfs(root, res);
        
        return res[0];
    }
    
    private int dfs(TreeNode root, int[] res) {
        // base case
        
        int left = root.left == null ? 0 : Math.max(dfs(root.left, res), 0);
        int right = root.right == null ? 0 : Math.max(dfs(root.right, res), 0);
        res[0] = Math.max(res[0], root.val + left + right);
        return root.val + Math.max(left, right);
    }
}

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
    int res;
    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        dfs(root);
        
        return res;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        
        int l = dfs(root.left);
        int r = dfs(root.right);
        
        res = Math.max(res, l + r + root.val);
        
        return Math.max(0, Math.max(l, r) + root.val);
    }
}
