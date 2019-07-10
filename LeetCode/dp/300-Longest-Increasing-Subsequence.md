## [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)

Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:
```
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
```
Note:

- There may be more than one LIS combination, it is only necessary for you to return the length.
- Your algorithm should run in O(n2) complexity.

**Follow up: Could you improve it to O(n log n) time complexity?**

## Answer
### Method 2 - DP with Binary Search
- Time: O(nlogn)
```java
// TO DO...
```
### Method 1 - DP - :rabbit: 8ms (64.68%)
- Time: O(n^2)
```java
class Solution {
    // =========== Method 1: DP ============
    // 8ms (64.68%)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        
        int[] dp = new int[nums.length];

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        
        return ans;
    }
}
```
