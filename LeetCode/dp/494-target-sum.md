## [494. Target Sum](https://leetcode.com/problems/target-sum/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols `+` and `-`. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:

```
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
```

Note:
- The length of the given array is positive and will not exceed `20`.
- The sum of elements in the given array will not exceed `1000`.
- Your output answer is guaranteed to be fitted in a 32-bit integer.

## Answer
### Method 1 - DFS + Memo - :rabbit: 143ms (43.63%)

```java
public class Solution {
    /**
     * @param nums: the given array
     * @param s: the given target
     * @return: the number of ways to assign symbols to make sum of integers equal to target S
     */
    Integer[][] memo;
    public int findTargetSumWays(int[] nums, int s) {
        // Write your code here
        memo = new Integer[20001][nums.length];
        
        return dfs(nums, s, 0, 0);
    }
    
    private int dfs(int[] nums, int s, int sum, int lvl) {
        if (lvl == nums.length) {
            return sum == s ? 1 : 0;
        }
        
        if (memo[sum + 1000][lvl] != null) return memo[sum + 1000][lvl];
        
        int res = dfs(nums, s, sum + nums[lvl], lvl + 1) + dfs(nums, s, sum - nums[lvl], lvl + 1);
        
        memo[sum + 1000][lvl] = res;
        
        return memo[sum + 1000][lvl];
    }
}
```
