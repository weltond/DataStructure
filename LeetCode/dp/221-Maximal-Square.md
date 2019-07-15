## [221. Maximal Square](https://leetcode.com/problems/maximal-square/)

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:
```
Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
```

## Answer
### Method 1 - DP - :rocket: 4ms (95.34%)
```java
class Solution {
    // ============ Method 1: DP ===============
    // 4ms (95.34%)
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length <= 0) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        // dp[i][j] represents max square consists from bottom-right to (i,j)
        int[][] dp = new int[m + 1][n + 1];
        
        int res = 0;
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    int right = dp[i][j + 1];
                    int down = dp[i + 1][j];
                    int diag = dp[i + 1][j + 1];
                    
                    dp[i][j] = Math.min(right, Math.min(down, diag)) + 1;
                    res = Math.max(dp[i][j], res);
                }
            }
        }
        
        return res * res;
    }
}
```
