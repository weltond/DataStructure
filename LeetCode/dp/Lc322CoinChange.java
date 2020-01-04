// https://leetcode.com/problems/coin-change/
// Compare the following DFS + memo with Method 2 below.
// 69ms (6.52%)
public class Solution {
    int ans = Integer.MAX_VALUE;
    int[][] memo;
    public int coinChange(int[] coins, int amount) {
        // write your code here
        if (coins == null || coins.length == 0) return 0;
        
        Arrays.sort(coins);

        memo = new int[amount + 1][coins.length];
        
        ans = dfs(coins, amount, coins.length - 1);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private int dfs(int[] coins, int rem, int lvl) {
        if (rem == 0) {
            return 0;
        }
        if (memo[rem][lvl] != 0) return memo[rem][lvl];
        
        int ret = Integer.MAX_VALUE;
        for (int i = lvl; i >= 0; i--) {
            if (rem < coins[i] || coins[i] == 0) continue;
            int r = dfs(coins, rem - coins[i], i);
            if (r >= 0 && r < ret) {
                ret = r + 1;
            }
        }
        
        memo[rem][lvl] = ret == Integer.MAX_VALUE ? -1 : ret;
        
        return ret;
    }
}

class Solution {
    // =========== Method 4: DFS + greedy + pruning ==========
    // PROBLEM!!!! TLE for this test case: 
    // [8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90,8,7,4,3,90] 1905
    // if [1,2,5], upper bound of time complexicity is 3 ^ m, where m is max(amount / coins[i]).
    // 7ms
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins); // greedy. Iterate from largest value
        
        int[] ans = new int[]{Integer.MAX_VALUE};
        helper(coins.length - 1, coins, amount, 0, ans);
        
        return ans[0] == Integer.MAX_VALUE ? -1 : ans[0];
    }
    
    private void helper(int level, int[] coins, int rem, int cnt, int[] ans) {
        // last element
//         if (level == 0) {
//             if (rem % coins[level] == 0) {
//                 ans[0] = Math.min(ans[0], cnt + rem / coins[level]);
//             }
//             return;
//         }
        
        if (rem == 0) {
            ans[0] = Math.min(ans[0], cnt);
            return;
        }
        
        if (level == -1) return;
        
        int maxVal = rem / coins[level];
        
        // start from max element (greedy) and pruning
        // if current needed coins greater than ans[0], just ignore
        for (int i = maxVal; i >= 0 && i + cnt < ans[0]; i--) {
            helper(level - 1, coins, rem - i * coins[level], i + cnt, ans);
        }
    }
    
    // =========== Method 3: DP - Bottom Up ============
    // Approach 1 : Naive. Time = O(n * amount ^ 2) 425ms 
    /** 
        dp[i][j] represents min number of coins needed to make up amount j with only coins[i..n-1]
        Base case:
            dp[n][0] = 0, dp[n][1..m] = inf
        Induction rule:
            dp[i][j] = min(dp[i+1][j-k*coins[i]]+k), 0<=k<=j/coins[i]
    */
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        // base case
        Arrays.fill(dp[coins.length], Integer.MAX_VALUE);
        dp[coins.length][0] = 0;
        // induction rule
        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i + 1][j];
                int maxK = j / coins[i];
                // Notice 1: k must start from 1, because j - coins[i] might be less than 0.
                for (int k = 1; k <= maxK; ++k) {
                    int prev = dp[i + 1][j - k * coins[i]];
                    if (prev < Integer.MAX_VALUE) {
                        // Notice 2: must explicity compare prev with MAX_VALUE,
                        // because if prev is MAX, prev + k will become to MIN_VALUE
                        dp[i][j] = Integer.min(dp[i][j], prev + k);
                    }
                }
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }
    
    // =====
    // Approach 2. Time = O(n * amount) 19ms 
    /**
    Base case:
        dp[n][0] = 0, dp[n][1...m] = inf or amount
    Induction rule:
        dp[i][j] = min(dp[i+1][j], dp[i-coins[j]] + 1)
    */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;             
        int[] dp = new int[amount + 1];  
        Arrays.fill(dp, max);  
        dp[0] = 0;   
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    
    // =========== Method 2: DP - Top down ============
    // 32ms
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return helper(coins, amount, new int[amount + 1]);
    }
    
    private int helper(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        
        if (count[rem] != 0) return count[rem];
        
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = helper(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        
        count[rem] = (min == Integer.MAX_VALUE) ? -1 : min;
        
        return count[rem];
    }
    
    
    // =========== Method 1: Recursion (TLE) ==========
    // Time = O(S ^ n) where n is the coins length
//     public int coinChange(int[] coins, int amount) {
//         return helper(0, coins, amount);
//     }
    
//     private int helper(int level, int[] coins, int amount) {
//         if (amount == 0) return 0;
        
//         if (level >= coins.length || amount < 0) return -1;
        
//         int maxVal = amount / coins[level];
//         int minCost = Integer.MAX_VALUE;
        
//         for (int x = 0; x <= maxVal; x++) {
//             if (amount >= x * coins[level]) {
//                 int res = helper(level + 1, coins, amount - x * coins[level]);
                
//                 if (res != -1)
//                     minCost = Math.min(minCost, res + x);
//             }
//         }
        
//         return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
//     }
}
