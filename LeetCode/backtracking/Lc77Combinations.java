// https://leetcode.com/problems/combinations/
class Solution {
    /* ==============Elegant==============*/
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList();

        List<Integer> list = new ArrayList();
        
        dfs(1, n, k, list, res);
        
        return res;
    }
    
    private void dfs(int start, int end, int depth, List<Integer> list, List<List<Integer>> res) {
        if (depth == 0) {
            res.add(new ArrayList(list));
            return;
        }
        
        for (int i = start; i <= end; i++) {
            list.add(i);
            dfs(i + 1, end, depth - 1, list, res);
            list.remove(list.size() - 1);
        }
    }
    
    /*================Original==============*/
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList();
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        List<Integer> list = new ArrayList();
        
        dfs(arr, k, 0, 0, list, res);
        
        return res;
    }
    
    private void dfs(int[] arr, int k, int start, int depth, List<Integer> list, List<List<Integer>> res) {
        if (depth == k) {
            res.add(new ArrayList(list));
            return;
        }
        
        for (int i = start; i < arr.length; i++) {
            list.add(arr[i]);
            dfs(arr, k, i + 1, depth + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}
