// https://leetcode.com/problems/combination-sum/

class Solution {
    // =========== Backtracking with pruning ==============
    // 2ms (100%)
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        if (candidates == null || candidates.length == 0) return res;
        
        // sort array in ascending order to speed up the pruning process
        Arrays.sort(candidates);
        
        bt(candidates, 0, target, new ArrayList(), res);
        
        return res;
    }
    
    private void bt(int[] arr, int level, int rem, List list, List res) {
        // base case
        if (rem == 0) {
            res.add(new ArrayList(list));
            return;
        }
        
        for (int i = level; i < arr.length; i++) {
            // pruning. break if greater than rem
            if (rem - arr[i] < 0) break;
            
            list.add(arr[i]);
            
            // we start next level from i instead of level + 1 here to avoid duplicate!
            bt(arr, i, rem - arr[i], list, res);
            
            list.remove(list.size() - 1);
        }
    }
}
