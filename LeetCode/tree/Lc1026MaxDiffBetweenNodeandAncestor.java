// https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/

/**
Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: 
We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
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
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }
    
    private int dfs(TreeNode root, int min, int max) {
        // if encounter leaves, return the max-min along the path
        if (root == null) {
            return max - min;
        }
        
        // else, update max and min
        // and return the max of left and right subtrees
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);
        
        int left = dfs(root.left, min, max);
        int right = dfs(root.right, min, max);
        
        return Math.max(left, right);
    }
}

class Solution {
    // ============== Method 1: Post order ==============
    // 1ms (34.48%)
    int res;
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;
        
        res = Integer.MIN_VALUE;
        
        postOrder(root);
        
        return res;
    }
    
    private Pair postOrder(TreeNode root) {
        if (root == null) return new Pair(Integer.MAX_VALUE, Integer.MIN_VALUE);
        
        if (root.right == null && root.left == null) return new Pair(root.val, root.val);
        
        Pair left = postOrder(root.left);
        Pair right = postOrder(root.right);
        
        int lMax = left.max, lMin = left.min;
        int rMax = right.max, rMin = right.min;
        
        int childMin = Math.min(lMin, rMin);
        int childMax = Math.max(lMax, rMax);
        //System.out.println(root.val + ", " + childMin + ", " + childMax);
        res = Math.max(res, Math.max(Math.abs(root.val - childMin), Math.abs(root.val - childMax)));
        
        return new Pair(Math.min(root.val, childMin), Math.max(root.val, childMax));
    }
}
class Pair {
    int min, max;
    Pair(int min, int max) {
        this.min = min; 
        this.max = max;
    }
}
