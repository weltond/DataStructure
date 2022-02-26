## [71. Simplify Path](https://leetcode.com/problems/combination-sum/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)



## Answers
### Method 2 - DP - 3ms (92.11%)

subproblem dp(i, j) is defined as all the combinations of using candidates[i] (i = 0 to i) to sum to j.

Base case: `dp(0, 0) = [[]];` target 0 can be sum by no selecting any candidate

State transition is defined as: `dp(i, j) = dp[i -1, j) + dp(i, j - candidates[i]);`

solution state: `dp(n, target)`.

Following solution is space optimized from 2D dp array to 1D dp array.

```java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>>[] dp = new List[target + 1];
        for (int i = 0; i <= target; i++)
            dp[i] = new ArrayList<>();
        
        dp[0].add(new ArrayList<>());
        
        for (int candidate: candidates) {
            for (int j = candidate; j <= target; j++) {                
                for (List<Integer> comb: dp[j - candidate]) {
                    List<Integer> newComb = new ArrayList(comb);
                    newComb.add(candidate);
                    dp[j].add(newComb);
                }                    
            }
        }
        
        return dp[target];
    }
}
```
### Method 1 - DFS 
Time: O(N^(T/M + 1)), T is target value, M is minimal value among candidates. Because it is N-ary tree, max depth is T/M

Space: O(T/M) which is the depth of tree

```java
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
```
