## [416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/)

Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 

Example 1:
```
Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
``` 

Example 2:
```
Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
```

## Answer
### Method 2 - DFS - :rocket: 4ms(87.33%)
#### Approach 2 

```java
class Solution {
    Boolean[][] memo;
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        
        if (sum % 2 != 0) return false;
        
        int target = sum / 2;
        
        memo = new Boolean[nums.length][target + 1];
        
        return dfs(nums, target, 0, 2, target, new boolean[nums.length]);
    }
    
    private boolean dfs(int[] nums, int subSum, int start, int k, int target, boolean[] used) {
        if (start >= nums.length) return false;
        
        if (memo[start][subSum] != null) return memo[start][subSum];
        
        if (subSum == 0) {
            return dfs(nums, target, 0, k - 1, target, used);
        }
        
        if (k == 0) {
            return true;
        }
        
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > subSum) continue;

            if (dfs(nums, subSum - nums[i], i + 1, k, target, used)) return true;
        }
        
        memo[start][subSum] = false;
        
        return false;
    }
}
```

#### Approach 1

```java
class Solution {
    Boolean[][] memo;
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        
        
        
        int sum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        
        if (sum % 2 != 0) return false;
        
        int each = sum / 2;
        memo = new Boolean[nums.length][each + 1];

        return dfs(nums, each, 0, 2, each, new boolean[nums.length]);
    }
    
    private boolean dfs(int[] nums, int subSum, int start, int k, int sum, boolean[] used) {
        if (memo[start][subSum] != null) return memo[start][subSum];
        
        if (k == 0) return true;
        
        if (subSum == 0) {
            return dfs(nums, sum, 0, k - 1, sum, used);
        }
    
        for (int i = start; i < nums.length; i++) {
            if (used[i] || subSum < nums[i]) continue;
            
            used[i] = true;
            
            if (dfs(nums, subSum - nums[i], i, k, sum, used)) return true;
            
            used[i] = false;
        }
        
        memo[start][subSum] = false;
        return false;
    }
}
```

### Method 1 - DP - :rabbit: 17ms (43.08%)

```java
class Solution {
    // ======== Method 1: DP =========
    // Find subsets that has sum j.
    // 17ms (43.08%)
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 1) return false;
        
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        
        if ((sum & 1) != 0) return false;
        
        sum >>= 1;
        
        // dp[i][j] means whether the specific sum j can be gotten from the first i numbers
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        dp[0][0] = true;
        
        // dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]];     ===> not pick i || pick i
        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        
        return dp[nums.length][sum];
    }
}
```
