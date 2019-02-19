// https://leetcode.com/problems/combination-sum/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        if (candidates == null || candidates.length == 0) return res;
        
        // make sure we start from the smallest number
        Arrays.sort(candidates);
        
        List<Integer> list = new ArrayList();
        bt(candidates, target, 0, list, res);
        
        return res;
    }
    
    // Combination DFS backtracking
    private void bt(int[] arr, int target, int start, List<Integer> list, List<List<Integer>> res) {
        // base case
        if (target == 0) {
            res.add(new ArrayList(list));
            return;
        }
        
        // corner check
        // if (target < 0) {
        //     return;
        // }
        
        // recursion
        for (int i = start; i < arr.length; i++) { 
            // stop early to avoid unneccesary trial
            if (target < arr[i]) {
                break;
            }
            
            list.add(arr[i]);
            
            bt(arr, target - arr[i], i, list, res);
            
            list.remove(list.size() - 1);
        }
    }
    
    // private void print(List<Integer> list) {
    //     for (Integer i : list) {
    //         System.out.print(i + " ");
    //     }
    //     System.out.println();
    // }
}
