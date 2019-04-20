// https://leetcode.com/problems/combination-sum-iv/

/**
Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
*/

class Solution {
    // ============== Method 2: DP ===============
    // 1ms (86.36%)
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0; 
        
        int[] dp = new int[target + 1];
        dp[0] = 1;
        // dp[i] represent how many combinations to get sum i
        // dp[i] = dp[i - nums[0]] + dp[i - nums[1]] + ... + dp[i - nums[n]]
        
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i < nums[j]) continue;
                dp[i] += dp[i - nums[j]];
            }
        }
        
        return dp[target];
    }
    
    
    // ============== Method 1: DFS + Memo ===============
    // 1ms (86.36%)
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0; 
        
        Arrays.sort(nums);
  
        /** Without Memo -> TLE
        int[] res = new int[]{0};
        dfs(nums, target, res);
        return res[0];
        */
        
        int[] memo = new int[target + 1];
        
        Arrays.fill(memo, -1);  // without this line, cause TLE because we can't tell whether not calculated or 0 return.
        
        return dfs(nums, target, memo);
    }
    
    private int dfs(int[] nums, int rem, int[] memo) {
        if (memo[rem] != -1) return memo[rem];
        
        if (rem == 0) {
            return 1;
        }
        
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (rem < nums[i]) break;
            
            sum += dfs(nums, rem - nums[i], memo);
        }
        
        memo[rem] = sum;
        
        return sum;
    }
    
    // If not consider memo, we could simply use void return. BUT, this will cause TLE.
//     private void dfs(int[] nums, int rem, int[] res) {
//         if (rem == 0) {
//             res[0] += 1;
//             return;
//         }
        
//         for (int i = 0; i < nums.length; i++) {
//             if (rem < nums[i]) break;
            
//             dfs(nums, rem - nums[i], res);
//         }
//     }
}
