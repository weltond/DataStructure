// https://leetcode.com/problems/most-frequent-subtree-sum/

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
    // 3ms (100%)
    Map<Integer, Integer> map;
    int maxVal;
    public int[] findFrequentTreeSum(TreeNode root) {
        map = new HashMap();    // <sum, freq>
        maxVal = Integer.MIN_VALUE;
        List<Integer> res = new ArrayList();
        
        dfs(root, res);
        
        int size = res.size();
        int[] ans = new int[size];
        int i = 0;
        for (int n : res) {
            ans[i++] = n;   
        }
        
        return ans;
    }
    
    private int dfs(TreeNode root, List<Integer> list) {
        if (root == null) return 0;
        
        int sum = root.val + dfs(root.left, list) + dfs(root.right, list);
        
        int freq = map.getOrDefault(sum, 0) + 1;
        map.put(sum, freq);

        if (freq > maxVal) {
            list.clear();
            list.add(sum);
            maxVal = freq;
        } else if (freq == maxVal) {
            list.add(sum);
        }
        
        return sum;
    }
}
