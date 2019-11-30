// https://leetcode.com/problems/coin-change-2/

/**
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
*/

/**
5, [5,2,1]
                        5
        0               3                   4
                 1             2            3
             -1     0          1            2
                               1            1
                               0            0
                               
For recursion, if we only use dp[rem], when rem = 3 at first, dp[rem] = 2. However, the next time
when rem = 3, there should be only one result which means dp[rem = 3] cannot be used here.
Hence, we need a 2-d array to store dp[rem][first/last i coins are used]

Note: Be careful dp[][] can be 0. So we need to initialize the array or make it Integer. 
*/
class Solution {
    
    // =========== Method 2: DP =============
    // Appraoch 3: 1D array 1ms (100%)
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }
    
    // Approach 2: 2D array 7ms (38.27%)
    public int change(int amount, int[] coins) {
        // dp[i][j] : the number of combinations to make up amount j by using the first i types of coins
        // Initialization: dp[i][0] = 1
        // 1) not using the ith coin, only using the first i-1 coins to make up amount j, then we have dp[i-1][j] ways.
        // 2) using the ith coin, since we can use unlimited same coin, we need to know how many ways to make up amount j - coins[i-1] by using first i coins(including ith), which is dp[i][j-coins[i-1]]
        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0] = 1;
        
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }
    
    // Approach 1: 2D array: 8ms (32.35%)
    public int change(int amount, int[] coins) {
        if (amount == 0) return 1;
        if (coins == null || coins.length < 1) return 0;
        
        // dp[i][j] : the number of combinations to make up amount i by using the first j types of coins
        int[][] dp = new int[amount + 1][coins.length + 1];
        
        // base case: dp[0][j] = 1
        // induction rule: dp[i][j] = dp[i][j-1] if not using the j-th coin
        //                          + dp[i-coins[j-1]][j] if use the j-th coin
        
        for (int i = 0; i <= amount; i++) {
            for (int j = 1; j <= coins.length; j++) {
                if (i == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i][j - 1] + (i < coins[j - 1] ? 0 : dp[i - coins[j - 1]][j]);
                }
                
            }
        }
        return dp[amount][coins.length];
    }
    
    
    // =========== Method 1: Recursion ==========    
    // Approach  : 1) 7ms (38.27%)
    //             2) 83ms if not sort coins and start from 0.
    public int change(int amount, int[] coins) {
        if(amount == 0) 
            return 1;
        if(coins.length == 0) 
            return 0;
        Arrays.sort(coins);
        return memo(amount,coins,coins.length-1,new Integer[amount+1][coins.length]);
    }
    
    int memo(int curr, int[] coins, int last, Integer[][] dp){
        
        if(curr < 0)
            return 0;
        if(curr == 0)
            return 1;
        
        if (dp[curr][last] != null) {
            System.out.println(curr + ", " + last + " : " + dp[curr][last] + " -> " + coins[last]);
            return dp[curr][last];
        }
        
        int val = 0;
        for(int i = last; i >= 0; i--){
            val +=  memo(curr-coins[i],coins,i,dp);
            System.out.println("IN: " + curr + ", " + coins[i] + ": " + val);
        }
            
        dp[curr][last] = val;
        System.out.println("OUT: " + curr + ", " + coins[last] + ": " + val);
        return dp[curr][last];
    }
    
    // =========== Method 1: Recursion (TLE) ============
    //List<List<Integer>> res = new ArrayList();
//     public int change(int amount, int[] coins) {
//         if (amount == 0) return 1;
//         if (coins == null || coins.length == 0) return 0;
//         int[][] amountRem = new int[amount + 1][coins.length];  // memoization
//         int sum = helper(coins, amount, 0, amountRem);
//         // for (List<Integer> l : res) {
//         //     for (int i : l) {
//         //         System.out.print(i + " ");
//         //     }
//         //     System.out.println("");
//         // }
//         return sum;
//     }
    
//     private int helper(int[] coins, int rem, int level, int[][] amountRem) {
//         if (level == coins.length - 1) {
//             // if (rem % coins[level] == 0) {
//             //     list.add(rem / coins[level]);
//             //     res.add(new ArrayList(list));
//             //     list.remove(list.size() - 1);
//             // }

//             return rem % coins[level] == 0 ? 1 : 0;
//         }
        
//         if (amountRem[rem][level] != 0) {
//             //System.out.println(rem + ", " + level + " : " + amountRem[rem][level]);
//             return amountRem[rem][level];
//         }
        
//         int len = rem / coins[level], sum = 0;
//         for (int i = 0; i <= len; i++) {
//             //list.add(i);
//             sum += helper(coins, rem - i * coins[level], level + 1, amountRem);
//             //list.remove(list.size() - 1);
//         }
        
//         amountRem[rem][level] = sum;
        
//         return sum;
//     }

    
}
