// https://leetcode.com/problems/check-completeness-of-a-binary-tree/

/**
Given a binary tree, determine if it is a complete binary tree.
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
    // =========== Method 1: BFS ==============
    // Approach 2: 1ms (94.44%)
    public boolean isCompleteTree(TreeNode root) {
        boolean end = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur == null) end = true;
            else{
                if(end) return false;
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return true;
    }
    
    // Approach 1: 1ms (94.44%)
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        
        boolean end = false;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (end && (n.left != null || n.right != null)) return false;
                if (n.right != null) {
                    if (n.left != null) {
                        q.offer(n.left);
                        q.offer(n.right);
                    } else {
                        return false;
                    }
                } else {
                    end = true; //should terminate next round
                    if (n.left != null) {
                        q.offer(n.left);    // don't forget to add its left to queue
                    }
                }
            }
        }
        
        return true;
        
    }
}
