// https://leetcode.com/problems/validate-binary-search-tree/solution/

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
    // ============ Method 3: InOrder ==============
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = - Double.MAX_VALUE;
        
        while (!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
    
    // ============ Method 2: Iteration ==============
    // DFS would be faster than BFS
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        
        LinkedList<TreeNode> stack = new LinkedList();
        LinkedList<Integer> upper = new LinkedList();
        LinkedList<Integer> lower = new LinkedList();
        stack.add(root);
        upper.add(null);
        lower.add(null);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.poll();
            Integer low = lower.poll();
            Integer up = upper.poll();
            
            if (node.right != null) {
                if (node.right.val > node.val) {
                    if ((up != null) && (node.right.val >= up)) {
                        return false;
                    }
                    stack.add(node.right);
                    lower.add(node.val);
                    upper.add(up);
                } else {
                    return false;
                }
            }
            
            if (node.left != null) {
                if (node.left.val < node.val) {
                    if ((low != null) && (node.left.val <= low)) return false;
                    stack.add(node.left);
                    lower.add(low);
                    upper.add(node.val);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    // ============ Method 1: Recursion ===============
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        
        long minVal = Long.MIN_VALUE;
        long maxVal = Long.MAX_VALUE;
        
        return helper(root, minVal, maxVal);
    }
    
    private boolean helper(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val <= minVal || root.val >= maxVal) return false;
    
        return helper(root.left, minVal, root.val) && helper(root.right, root.val, maxVal);
    }
    
}
