// https://leetcode.com/problems/find-largest-value-in-each-tree-row/

/**
You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
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
    // ============ Method 2: DFS ==============
    // 1ms (100%)
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList();
        if (root == null) return ans;
        
        dfs(root, ans, 0);
        
        return ans;
    }
    
    private void dfs(TreeNode root, List<Integer> ans, int lvl) {
        if (root == null) return;
        
        while (lvl >= ans.size()) {
            ans.add(Integer.MIN_VALUE);
        }
        
        if (root.val >= ans.get(lvl)) {
            ans.set(lvl, root.val);
        }
        
        dfs(root.left, ans, lvl + 1);
        dfs(root.right, ans, lvl + 1);
    }
    
    // =========== Method 1: BFS ============
    // 2ms (75.04)
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList();
        if (root == null) return ans;
        
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            int maxVal = Integer.MIN_VALUE;
            
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                maxVal = Math.max(maxVal, n.val);
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
            
            ans.add(maxVal);
        }
        
        return ans;
    }
}
