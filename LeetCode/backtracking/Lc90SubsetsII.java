// https://leetcode.com/problems/subsets-ii/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        
        if (nums == null || nums.length == 0) return res;
        
        Arrays.sort(nums);
        
        dfs(nums, 0, new ArrayList<Integer>(), res);
        
        return res;
    }
    
    private void dfs(int[] nums, int level, List<Integer> l, List<List<Integer>> res) {
        if (level == nums.length) {
            res.add(new ArrayList<>(l));
            return;
        }

        l.add(nums[level]);

        dfs(nums, level + 1, l, res);

        l.remove(l.size() - 1);

        // to skip duplicate levels
        int i = 1;
        while (level + i < nums.length && nums[level] == nums[level + i]) {
            i++;
        }
        
        dfs(nums, level + i, l, res);
        
    }
}
