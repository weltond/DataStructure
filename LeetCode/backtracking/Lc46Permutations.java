// https://leetcode.com/problems/permutations/submissions/

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        dfs(nums, 0, new ArrayList(), res);
        
        return res;
    }
    
    private void dfs(int[] nums, int level, List<Integer> list, List<List<Integer>> res) {
        if (level == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        
        for (int i = level; i < nums.length; i++) {
            list.add(nums[i]);      // add num to list before swap!
            swap(nums, level, i);

            dfs(nums, level + 1, list, res);
            
            list.remove(list.size() - 1);
            swap(nums, level, i);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// 0ms
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        
        if (nums == null) return res;
        
        dfs(nums, 0, res);
        
        return res;
    }
    
    private void dfs(int[] nums, int lvl, List<List<Integer>> res) {
        if (lvl == nums.length) {
            List<Integer> l = new ArrayList();
            for (int i : nums) {
                l.add(i);
            }
            res.add(l);
            return;
        }
        
        for (int i = lvl; i < nums.length; i++) {
            
            // if (i != lvl) {
            //     swap(nums, i, i - 1);    
            // }
            swap(nums, lvl, i);
            
            dfs(nums, lvl + 1, res);  // lvl + 1, NOT i+1
            
            // if (i != lvl) {
            //     swap(nums, i, i - 1);
            // }
            swap(nums, lvl, i);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}

// 1ms
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
