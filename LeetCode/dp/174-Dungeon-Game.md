## [174. Dungeon Game](https://leetcode.com/problems/dungeon-game/)

The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

 

Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path `RIGHT-> RIGHT -> DOWN -> DOWN`.
```
-2 (K)	|   -3    |	3
-5	    |   -10   |	1
10	    |   30    | -5 (P)
```

Note:

- The knight's health has no upper bound.
- Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.

## Answer
### Method 1 - DP - :rocket: 1ms (98.03%)
#### Approach 2
- Time: O(m * n)
- Space: O(n)
```java
class Solution {
    // ======= Method 1: DP ======
    // Approach 2: O(n) Space. 1ms (98.03%)
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) return 0;
        
        int m = dungeon.length, n = dungeon[0].length;
        int[] dp = new int[n + 1];
        
        // base case: Outer are MAX except for the last corner's right and down which is 1.
        for (int c = 0; c <= n; c++) {
            dp[c] = Integer.MAX_VALUE;
        }
        dp[n - 1] = 1;
        
        // induction rule:
        // dp[i][j] = max(1, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j])
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[j] = Math.max(1, Math.min(dp[j + 1], dp[j]) - dungeon[i][j]);
            }
        }
        
        return dp[0];
    }
}
```
#### Approach 1
- Time: O(m * n)
- Space: O(m * n)
```java
class Solution {
    // ======= Method 1: DP ======
    // 1ms (98.03%)
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) return 0;
        
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        
        // base case: Outer are MAX except for the last corner's right and down which is 1.
        for (int r = 0; r <= m; r++) {
            dp[r][n] = Integer.MAX_VALUE;
        }
        for (int c = 0; c <= n; c++) {
            dp[m][c] = Integer.MAX_VALUE;
        }
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;
        
        // induction rule:
        // dp[i][j] = max(1, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j])
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        
        return dp[0][0];
    }
}
```
