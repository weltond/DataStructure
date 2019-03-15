// https://leetcode.com/problems/binary-tree-right-side-view/

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
    // ========= Method 2: BFS ==============
    // 1ms
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList();
        
        List<Integer> list = new ArrayList();
        
        Queue<TreeNode> q = new LinkedList();
        
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == 0) {
                    list.add(node.val);
                }
                
                if (node.right != null) q.offer(node.right);
                if (node.left != null) q.offer(node.left);
            }
        }
        
        return list;
    }
    
    // ========= Method 1: DFS ==============
    // 0ms
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList();
        
        List<Integer> list = new ArrayList();
        
        dfs(root, 0, list);
        
        return list;
    }
    
    private void dfs(TreeNode root, int level, List<Integer> list) {
        if (root == null) return;
        
        if (list.size() == level) {
            list.add(root.val);
        }
        
        dfs(root.right, level + 1, list);
        dfs(root.left, level + 1, list);
    }
}
