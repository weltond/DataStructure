// https://leetcode.com/problems/subsets-ii/

/**
    Input: [1,2,2]
    Output:
    [
      [2],
      [1],
      [1,2,2],
      [2,2],
      [1,2],
      []
    ]
*/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        dfs(nums, 0, new ArrayList(), res);

        return res;
    }

    private void dfs(int[] nums, int idx, List<Integer> list, List<List<Integer>> res) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        list.add(nums[idx]);

        dfs(nums, idx + 1, list, res);

        list.remove(list.size() - 1);

        // skip adding "" if there are duplicate
        idx++;
        while (idx < nums.length && nums[idx] == nums[idx - 1]) idx++;

        dfs(nums, idx, list, res);
    }
}

class Solution {
    // on each level, add or not add
    // output is :      [[1,2,2],[1,2],[1],[2,2],[2],[]]
    //     if not skip: [[1,2,2],[1,2],[1,2],[1],[2,2],[2],[2],[]]
    // We SKIP the FIRST Duplicating one.
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

/*Another Solution*/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList(), res);
        
        return res;
    }
    
    private void dfs(int[] nums, int start, List<Integer> list, List res) {
        res.add(new ArrayList(list));
        
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            dfs(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}

class Solution {
    // like combination sum.
    // output is : [[],[1],[1,2],[1,2,2],[2],[2,2]]
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return res;
        }
        Arrays.sort(nums);
        List<Integer> curr = new ArrayList<>();
        dfs(nums, 0, res, curr);
        return res;
    }
    private void dfs(int[] nums, int index, List<List<Integer>> res, List<Integer> curr){
        res.add(new ArrayList<>(curr));
        
        // we actually don't need this if statement.
        if(index == nums.length){
            return;
        }
        
        Set<Integer> visited = new HashSet<Integer>();
        for(int i = index; i < nums.length; i++){
            if(visited.add(nums[i])){
                curr.add(nums[i]);
                dfs(nums, i + 1, res, curr);
                curr.remove(curr.size() - 1);
            } 
        }
    }
}
