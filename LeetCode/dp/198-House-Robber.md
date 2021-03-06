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
### Method 2 - DP - :rocket: 0ms
#### Approach 3
```java
class Solution {
    public int rob(int[] nums) {
        if (nums == null) return 0;
        
        int rob = 0, notRob = 0, n = nums.length;
        
        for (int i = 0; i < n; i++) {
            int preRob = rob, preNotRob = notRob;
            
            rob = preNotRob + nums[i];
            notRob = Math.max(preRob, preNotRob);
        }
        
        return Math.max(rob, notRob);
    }
}
```
#### Approach 2: O(1) Space
```java
class Solution {
    // ======== Method 2: DP ===========
    // Approach 2: O(1) space 0ms
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        
        if (nums.length == 1) return nums[0];
        
        // base case
        int p2 = nums[0];
        int p1 = Math.max(nums[0], nums[1]);
        // induction rule: dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
        for (int i = 2; i < nums.length; i++) {
            int tmp = p1;
            p1 = Math.max(p2 + nums[i], p1);
            p2 = tmp;
        }
        
        return p1;    
    }
}
```
#### Approach 1: O(n) Space
```java
class Solution {
    // ======== Method 2: DP ===========
    // Approach 1: O(n) space 0ms
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        
        if (nums.length == 1) return nums[0];
        
        int[] dp = new int[nums.length];
        
        // base case
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // induction rule: dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        
        return dp[nums.length - 1];    
    }
    
    // Or
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        
        int[] dp = new int[nums.length + 1];
        
        // base case
        dp[0] = 0;
        dp[1] = nums[0];
        // induction rule: dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
        for (int i = 1; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i - 1] + nums[i], dp[i]);
        }
        
        return dp[nums.length];    
    }
}
```
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
