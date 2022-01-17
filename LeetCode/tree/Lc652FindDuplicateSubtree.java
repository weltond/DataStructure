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
}

class Solution {
    List<TreeNode> res = new ArrayList<>();

    // <tree_structure, freq>
    Map<String, Integer> map = new HashMap<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);

        return res;
    }

    private String dfs(TreeNode root) {
        if (root == null) return "#";

        String left = dfs(root.left);
        String right = dfs(root.right);

        String subTree = left + "," + right + "," + root.val;

        int freq = map.getOrDefault(subTree, 0);

        // only add once
        if (freq == 1) {
            res.add(root);
        }

        map.put(subTree, freq + 1);

        return subTree;
    }
}
