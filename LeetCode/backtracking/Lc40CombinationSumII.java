// https://leetcode.com/problems/combination-sum-ii/

class Solution {
    // ============ Backtracking ==========
    // 4ms (99.74%)
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        
        if (candidates == null || candidates.length == 0 || target == 0) return res;
        
        Arrays.sort(candidates);
        
        bt(candidates, 0, target, new ArrayList(), res);
        
        return res;
    }
    
    private void bt(int[] arr, int level, int rem, List list, List res) {
        if (rem == 0) {
            res.add(new ArrayList(list));
            return;
        }
        
        
        Set<Integer> set = new HashSet();
        for (int i = level; i < arr.length; i++) {
            // skip same value on the same level
            if (!set.add(arr[i])) continue;
            // NOT compare with the next or previous one.
            //if (arr[i] == arr[level - 1]) continue;    
            
            if (rem - arr[i] < 0) break;    // stop early if rem is smaller than current
            
            list.add(arr[i]);
            
            bt(arr, i + 1, rem - arr[i], list, res);
            
            list.remove(list.size() - 1);
        }
    }
}
