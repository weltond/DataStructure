## [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

**Note**: Given n will be a positive integer.

Example 1:
```
Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
```
Example 2:
```
Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
```

## Answer
### Method 1 - DP - :rocket: 0ms
#### Approach 2
- O(1) Space
```java
class Solution {
    public int climbStairs(int n) {
        int prev = 0, cur = 1;
        for (int i = 1; i <= n; i++) {
            int tmp = cur;
            cur = cur + prev;
            prev = tmp;
        }
        
        return cur;
    }
}
```
#### Approach 1
- O(n) Space
```java
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + (i < 2 ? 0 : dp[i - 2]);
        }
        
        return dp[n];
    }
}
```
