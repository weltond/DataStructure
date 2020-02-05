// https://leetcode.com/problems/permutations-ii/


// 0ms
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        
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
        
        Set<Integer> set=  new HashSet();
        for (int i = lvl; i < nums.length; i++) {
            if (!set.add(nums[i])) continue;
            
            swap(nums, lvl, i);
            
            dfs(nums, lvl + 1, res);
            
            swap(nums, lvl, i);
        }
    }
    
    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}

// 1ms 
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        
        if (nums == null || nums.length == 0) return res;
        
        boolean[] visited = new boolean[nums.length];
        dfs(nums, 0, new ArrayList<Integer>(), res, visited);
        
        return res;
    }
    
    private void dfs(int[] nums, int level, List<Integer> list, List<List<Integer>> res, boolean[] visited) {
        if (level == nums.length) {
            res.add(new ArrayList(list));
            return;
        }
        
        Set<Integer> set = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            // should verify visited FIRST!
            // then skip duplicated values
            if (visited[i] || !set.add(nums[i])) continue;
            
            visited[i] = true;
            list.add(nums[i]);
            
            dfs(nums, level + 1, list, res, visited);
            
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
