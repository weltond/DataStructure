// https://leetcode.com/problems/min-cost-climbing-stairs/

class Solution {
    // ========== Method 1: DP ============
    // 7ms
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        
        int[] dp = new int[cost.length + 1];
        // base case
        dp[0] = 0;
        dp[1] = 0;
        
        // induction rule: dp[i] = min(dp[i-2] + cost[i-2], dp[i-1] + cost[i-1])
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        
        return dp[cost.length];
    }
    
    // DP : O(1) space
    public int minCostClimbingStairs(int[] cost) {
        int f1 = 0, f2 = 0;
        for (int i = cost.length - 1; i >= 0; --i) {
            int f0 = cost[i] + Math.min(f1, f2);
            f2 = f1;
            f1 = f0;
        }
        return Math.min(f1, f2);
    }

}
