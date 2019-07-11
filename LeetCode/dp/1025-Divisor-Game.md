## [1025. Divisor Game](https://leetcode.com/problems/divisor-game/)

Alice and Bob take turns playing a game, with Alice starting first.

Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:

Choosing any x with 0 < x < N and N % x == 0.
Replacing the number N on the chalkboard with N - x.
Also, if a player cannot make a move, they lose the game.

Return True if and only if Alice wins the game, assuming both players play optimally.

Example 1:
```
Input: 2
Output: true
Explanation: Alice chooses 1, and Bob has no more moves.
```
Example 2:
```
Input: 3
Output: false
Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
```

Note:

- `1 <= N <= 1000`

## Answer
### Method 1 - DP
#### Approach 1 :turtle: 4ms (25.14%)
```java
class Solution {
    // ============== Approach 1 =============
    // 4ms (25.14%)
    public boolean divisorGame(int n) {
        boolean[] dp = new boolean[n + 1];
        dp[1] = false;
        for (int i = 2; i <= n; i++) {
            dp[i] = false;
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
}
```
