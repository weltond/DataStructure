// https://leetcode.com/problems/subsets/
class Solution {
    // =========== Method 2: (n+1)-ary recursion Tree ===========
    // 0ms
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if (nums == null) return res;
        
        dfs(nums, 0, new ArrayList(), res);
        
        return res;
    }
    
    private void dfs (int[] nums, int start, List list, List res) {
        // if (start == nums.length) {
        //     res.add(new ArrayList(list));
        //     return;
        // }    // WRONG. This is Binary Tree style.
        res.add(new ArrayList(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
    
    // =========== Method 1: Binary recursion Tree ===========
    // 0ms
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if (nums == null) return res;
        
        dfs(nums, 0, new ArrayList(), res);
        
        return res;
    }
    
    private void dfs(int[] nums, int lvl, List list, List res) {
        if (lvl == nums.length) {
            res.add(new ArrayList(list));
            return;
        }
        
        list.add(nums[lvl]);
        dfs(nums, lvl + 1, list, res);
        list.remove(list.size() - 1);
        
        dfs(nums, lvl + 1, list, res);
    }
}
