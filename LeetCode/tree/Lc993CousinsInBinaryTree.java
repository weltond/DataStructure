// https://leetcode.com/problems/cousins-in-binary-tree/

/**
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.

Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true

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
    // 1ms (69.65%)
    Map<Integer, Integer> depth;
    Map<Integer, TreeNode> parents;
    public boolean isCousins(TreeNode root, int x, int y) {
        depth = new HashMap();
        parents = new HashMap();
        dfs(root, null);
        return (depth.get(x) == depth.get(y) && parents.get(x) != parents.get(y));
    }
    
    private void dfs(TreeNode root, TreeNode parent) {
        if (root == null) return;
        
        depth.put(root.val, parent != null ? 1 + depth.get(parent.val) : 0);
        parents.put(root.val, parent);
        
        dfs(root.left, root);
        dfs(root.right, root);
    }
    
    // ============ Method 1: BFS ==============
    // 0ms (100%)
    public boolean isCousins(TreeNode root, int x, int y) {
        if (x == root.val || y == root.val) return false;
        
        int[] parent = {-1, -1};
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                
                if (n.left != null) {
                    if (n.left.val == x) parent[0] = n.val;
                    else if (n.left.val == y) parent[1] = n.val;
                    else q.offer(n.left);
                }
                if (n.right != null) {
                    if (n.right.val == x) parent[0] = n.val;
                    else if (n.right.val == y) parent[1] = n.val;
                    else q.offer(n.right);
                }
            }
            
            if ((parent[0] == -1 && parent[1] != -1) || (parent[0] != -1 && parent[1] == -1) || parent[0] == parent[1] && parent[0] != -1) return false;
            
            else if (parent[0] != parent[1]) return true;
        }
        
        return false;
    }
}
