## [198. House Robber](https://leetcode.com/problems/house-robber/)

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:
```
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
```
Example 2:
```
Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
```

## Answer
### Method 1 - DFS + Memo - :rocket: 0ms
```java
class Solution {
    // ======= Method 1: DFS + Memo =========
    // 0ms
    public int rob(int[] nums) {
        if (nums == null) return 0;
        
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1); // in case nums = [0,0,0,0...]
        return dfs(nums, 0, memo);
    }
    
    private int dfs(int[] nums, int x, int[] memo) {
        if (x >= nums.length) return 0;
        if (memo[x] != -1) return memo[x];
        
        // rob current x V.S. not rob current
        memo[x] = Math.max(nums[x] + dfs(nums, x + 2, memo), dfs(nums, x + 1, memo));
        
        return memo[x];
    }
}
```
