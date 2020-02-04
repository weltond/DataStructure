## [518. Coin Change 2](https://leetcode.com/problems/coin-change-2/)

You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

Example 1:

```
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
```

Example 2:

```
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
```

Example 3:

```
Input: amount = 10, coins = [10] 
Output: 1
```

Note:

You can assume that

- 0 <= amount <= 5000
- 1 <= coin <= 5000
- the number of coins is less than 500
- the answer is guaranteed to fit into signed 32-bit integer

## Answer
### Method 2 - DP
#### Approach 2 - 1D Array 

~~We can iterate either from left to right OR top to bottom.~~

```
input: 5 [5,1,2]

However, if 1D Array, it's better to iterate from left to right!

    0   1   2   3   4   5
--|-------------------------
0 | 1   0   0   0   0   1
1 | 1   0   1   0   1   1
2 | 1   1   2   2   3   4

```

- Left to right - :rocket: 1ms (100%)
```java
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
```

- Left to right 2 - :rocket: 3ms (50.90%)

```java
class Solution {
    public int change(int amount, int[] coins) {
        if (amount == 0) return 1;
        
        if (coins == null || coins.length == 0) return 0;
        int[] dp = new int[amount + 1];
        
        dp[0] = 1;
        
        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i <= amount; i++) {
                dp[i] = (j == 0 ? 0 : dp[i]) + (i - coins[j] < 0 ? 0 : dp[i - coins[j]]);
            }
        }
        
        return dp[amount];
    }
}
```

**Important**: If we write this:

```java
for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                dp[i] = (j == 0 ? 0 : dp[i]) + (i - coins[j] < 0 ? 0 : dp[i - coins[j]]);
            }
        }
```
Then above is **WRONG** solution. And it is for [Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/).

See this [post](https://leetcode.com/problems/coin-change-2/discuss/141076/Logical-Thinking-with-Clear-Java-Code).

#### Approach 1 - 2D Array - :rabbit: 8ms (32.35%)

```java
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
```

```java
public int change(int amount, int[] coins) {
        if (amount == 0) return 1;
        
        if (coins == null || coins.length == 0) return 0;
        int[][] dp = new int[amount + 1][coins.length];
        
        for (int i = 0; i < coins.length; i++) {
            dp[0][i] = 1;
        }
        
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                dp[i][j] = (j - 1 < 0 ? 0 : dp[i][j - 1]) + (i - coins[j] < 0 ? 0 : dp[i - coins[j]][j]);
            }
        }
        
        return dp[amount][coins.length - 1];
    }
 ```
 


### Method 1 - Recursion

- Sort before DFS :rabbit: 7ms (38.27%)
- Not sort before DFS :turtle: 83ms

```java
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
```

- TLE
```java
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
```
