// https://leetcode.com/problems/combination-sum-iii/

// 0ms
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList();
        
        dfs(n, k, 1, new ArrayList(), res);
    
        return res;
    }
    
    private void dfs(int rem, int k, int start, List list, List res) {
        if (rem == 0 && k == 0) {
            res.add(new ArrayList(list));
            return;
        }
        
        for (int i = start; i <= 9 - k + 1; i++) {
            if (rem < i) break;
            
            list.add(i);
            dfs(rem - i, k - 1, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}

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


class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList();
        
        bt(k, 1, 0, n, new ArrayList(), res);
        
        return res;
    }
    
    private void bt(int totalNum, int lvl, int cnt, int rem, List list, List res) {
        if (cnt > totalNum) return;
        
        if (rem == 0 && cnt == totalNum) {
            res.add(new ArrayList(list));
            return;
        }
        
        for (int i = lvl; i <= 9; i++) {
            if (rem < i) break;     // pruning
            
            list.add(i);
            
            bt(totalNum, i + 1, cnt + 1, rem - i, list, res);
            
            list.remove(list.size() - 1);
        }
    }
}
