// https://leetcode.com/problems/combination-sum-ii/

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        
        if (candidates == null || candidates.length == 0 || target == 0) return res;
        
        Arrays.sort(candidates);
        
        List<Integer> list = new ArrayList();
        bt(candidates, target, 0, list, res);
        
        return res;
    }
    
    private void bt(int[] arr, int target, int start, List<Integer> list, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList(list));
            return;
        }
        Set<Integer> set = new HashSet();
        for (int i = start; i < arr.length; i++) {
            
            // avoid duplicate numbers in arr
            if (!set.add(arr[i])) continue;
            
            // cut(early stop) if current doesn't match
            // we can do this because the array is sorted
            if (target < arr[i]) break;
            
            list.add(arr[i]);
            
            // next level(depth) should start with the next element (i + 1)
            bt(arr, target - arr[i], i + 1, list, res);
            
            list.remove(list.size() - 1);
            
        }
    }
}
