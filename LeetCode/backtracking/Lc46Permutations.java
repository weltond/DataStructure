// https://leetcode.com/problems/permutations/submissions/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        
        if (nums == null || nums.length == 0) return res;
        
        boolean[] visited = new boolean[nums.length];
        
        dfs(nums, visited, 0, new ArrayList<Integer>(), res);
        
        return res;
    }
    
    private void dfs(int[] nums, boolean[] visited, int level, List<Integer> list, List<List<Integer>> res) {
        if (level == nums.length) {
            res.add(new ArrayList(list));
            return;
        }
        
        // unlike combination, we start at 0 index on every level
        for (int i = 0; i < nums.length; i++) {
            // check if visited before
            if (!visited[i]) {
                visited[i] = true;
                list.add(nums[i]);
                
                dfs(nums, visited, level + 1, list, res);

                visited[i] = false;
                list.remove(list.size() - 1);
            }
            
        }
    }
}
