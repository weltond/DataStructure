// https://leetcode.com/problems/combination-sum-iii/
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList();
        
        // 1 is start value, 0 is start level
        bt(k, n, 1, 0, new ArrayList<Integer>(), res);
        
        return res;
    }
    
    /**
    @Param count: k 
    @Param sum : n
    @Param start: represents 1 - 9
    @Param level: variable to represent depth, when it equals to k, we return
    @Param list
    @Param res
    */
    private void bt(int count, int sum, int start, int level, List<Integer> list, List<List<Integer>> res) {
        // if recurse to k level
        // check if current sum is 0
        if (level == count) {
            if (sum == 0) {
                res.add(new ArrayList(list));
            }
            return;
        }
        
        for (int i = start; i <= 9; i++) {
            // cut branches
            if (sum < i) break;
            
            list.add(i);
            
            bt(count, sum - i, i + 1, level + 1, list, res);
            
            list.remove(list.size() - 1);   // backtracking
        }
    }
}
