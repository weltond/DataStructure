## [877. Stone Game](https://leetcode.com/problems/stone-game/)

Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.

Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.

Example 1:
```
Input: [5,3,4,5]
Output: true
Explanation: 
Alex starts first, and can only take the first 5 or the last 5.
Say he takes the first 5, so that the row becomes [3, 4, 5].
If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
``` 

Note:

- `2 <= piles.length <= 500`
- `piles.length is even.`
- `1 <= piles[i] <= 500`
- `sum(piles) is odd.`

## Answer
### Method 2 - Math - :rocket: 0ms
```java
class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}
```
### Method 1 - DP
#### Approach 2 :turtle: 6ms (40.56%)
```java
class Solution {
    // 6ms
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        
        int[][] dp = new int[n][n];
        
        // dp[i][j] largest score Alex can achieve from piles[i...j]
        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                int parity = (j - i) % 2;
                // Alex
                if (parity == 1) {
                    dp[i][j] = Math.max(dp[i + 1][j] + piles[i], dp[i][j - 1] + piles[j]);
                }
                // Lee
                else {
                    dp[i][j] = Math.max(dp[i + 1][j] - piles[i], dp[i][j - 1] - piles[j]);
                }
            }
        }
        
        return dp[0][n - 1] > 0;
    }
}
```
#### Approach 1 :rabbit: 4ms (50.91%)
```java
class Solution {
    // ========== Method 1: DP ===========
    // Approach 1: 4ms (50.91%)
    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        
        // dp[i][j] = biggest number of stones you can get more than opponent picking piles in piles[i:j]
        for (int i = 0; i < piles.length; i++) {
            dp[i][i] = piles[i];
        }
        
        // induction rule: 
        // Alice either pick piles[i] or piles[j]. Turns is like <LC1025.Divisor Game>. true->false, positive -> negative.
        // dp[i][j] = max(piles[i] - dp[i+1][j], piles[j]-dp[i][j-1])
        for (int i = piles.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < piles.length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        
        return dp[0][piles.length - 1] > 0;
    }
}
```
```java
class Solution {
    // 6ms (40.56%)
    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        
        for (int i = 0; i < piles.length; i++) {
            dp[i][i] = piles[i];
        }
        
        for (int len = 1; len < piles.length; ++len) {
            for (int i = 0; i < piles.length - len; ++i) {
                int j = i + len;
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        
        return dp[0][piles.length - 1] > 0;
    }
}
```
