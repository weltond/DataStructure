// https://leetcode.com/problems/find-duplicate-subtrees/

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
    // ======== Method 2: Unique Id ==========
    // 46ms
    int t;
    Map<String, Integer> trees;
    Map<Integer, Integer> count;
    List<TreeNode> ans;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        t = 1;
        trees = new HashMap();
        count = new HashMap();
        ans = new ArrayList();
        dfs(root);
        return ans;     
    }
    
    public int dfs(TreeNode node) {
        if (node == null) return 0;
        
        String serial = node.val + "," + dfs(node.left) + "," + dfs(node.right);
        
        int uid = trees.computeIfAbsent(serial, x->t++);
        count.put(uid, count.getOrDefault(uid, 0) + 1);
        
        if (count.get(uid) == 2) {
            ans.add(node);
        }
        
        return uid;
    }
    
    // ======== Method 1: DFS ==========
    // 20ms
    List<TreeNode> ans;
    Map<String, Integer> map;
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) return new ArrayList();
        
        map = new HashMap();
        ans = new ArrayList();
        
        dfs(root);
        
        return ans;
    }
    
    private String dfs(TreeNode root) {
        if (root == null) return "#";
        
        String left = dfs(root.left);
        String right = dfs(root.right);
        
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(",").append(left).append(",").append(right);
        
        String ret = sb.toString();
        if (map.containsKey(ret) && map.get(ret) == 1) {
            //System.out.println(root.val + ", " + map.get(ret));
            ans.add(root);
        }
        
        map.put(ret, map.getOrDefault(ret, 0) + 1);
        
        return sb.toString();
    }
}
