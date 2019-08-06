// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        
        if (root == null) return res;
        
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> l = new ArrayList();
            int sizeRes = res.size();
            for(int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (sizeRes % 2 == 0) {
                    l.add(n.val);
                } else {
                    l.add(0, n.val);
                }
                
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
            
            res.add(l);
        }
        
        return res;
    }
}
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        
        dfs(root, 0, res);
        
        return res;
    }
    
    private void dfs(TreeNode root, int lvl, List<List<Integer>> res) {
        if (root == null) return;
        
        if (lvl == res.size()) {
            res.add(new ArrayList());
        }
        
        if (lvl % 2 == 0) {
            res.get(lvl).add(root.val);
        } else {
            res.get(lvl).add(0, root.val);
        }
        
        dfs(root.left, lvl + 1, res);
        dfs(root.right, lvl + 1, res);
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
    // ============= Method 2: Iteration ================
    // 1ms (74.29%)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList();
        List<List<Integer>> res = new ArrayList();
        boolean change = true;
        Deque<TreeNode> queue = new LinkedList();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList();
            Deque<TreeNode> q = new LinkedList();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.removeFirst();

                list.add(n.val);
                if (!change) {
                    if (n.right != null) q.offerFirst(n.right);
                    if (n.left != null) q.offerFirst(n.left);
                } else {

                    if (n.left != null) q.offerFirst(n.left);
                    if (n.right != null) q.offerFirst(n.right);   
                }
            }
            change = !change;
            queue = q;

            res.add(new ArrayList(list));
        }
        
        return res;
    }
    
    // ============= Method 1: Recursion ================
    // 0ms (100%)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList();
        
        List<List<Integer>> res = new ArrayList();
        boolean change = true;
        dfs(root, 0, new ArrayList(), change, res);
        
        return res;
    }
    
    private void dfs(TreeNode root, int lvl, List<Integer> list, boolean change, List<List<Integer>> res) {
        if (root == null) return;
        
        // add elements for current lvl if not exists
        if (res.size() == lvl) res.add(new ArrayList<Integer>());
        
        if (change) {
            res.get(lvl).add(root.val);
        } else {
            res.get(lvl).add(0, root.val);
        }
        
        dfs(root.left, lvl + 1, list, !change, res);
        dfs(root.right, lvl + 1, list, !change, res);
        
        
    }
    
  
}
