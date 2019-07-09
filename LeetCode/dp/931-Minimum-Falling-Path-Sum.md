## [931. Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum/)

Given a square array of integers A, we want the minimum sum of a falling path through A.

A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.

Example 1:
```
Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: 12
Explanation: 
The possible falling paths are:
- [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
- [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
- [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
The falling path with the smallest sum is [1,4,7], so the answer is 12.
```
 

Note:

- `1 <= A.length == A[0].length <= 100`
- `-100 <= A[i][j] <= 100`

## Answer
### Method 2 - DP
**Easy to downgrade Space Complexity from O(n^2) to O(n).**
#### Approach 1: Top-down :turtule: 4ms (39.20%)
```java
class Solution {
    // ========= Approach 1: Top-down =========
    // 4ms (39.20%)
    public int minFallingPathSum(int[][] a) {
        if (a == null) return 0;
        
        int m = a.length, n = a[0].length;
        int[][] dp = new int[m + 1][n + 2];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1) {
                    dp[i][j] = a[i - 1][j - 1];
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1]) + a[i - 1][j - 1];
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, dp[m][i]);
        }
        
        return ans;
    }
}
```
### Method 1 - DFS + Memo - :rocket: 1ms (100%)
```java
class Solution {
    // ========== Method 1: DFS + Memo ===========
    // 1ms (100%)
    public int minFallingPathSum(int[][] a) {
        if (a == null) return 0;
        
        int m = a.length, n = a[0].length;
        int[][] arr = new int[m][n];
        
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            ans = Math.min(ans, dfs(a, 0, i, arr));
        }
        
        return ans;
    }
    
    private int dfs(int[][] a, int row, int col, int[][] arr) {
        if (col < 0 || col >= a[0].length) return Integer.MAX_VALUE;
        if (row == a.length - 1) {
            return a[row][col];
        }
        if (arr[row][col] != 0) return arr[row][col];
        
        int left = dfs(a, row + 1, col - 1, arr);
        int mid = dfs(a, row + 1, col, arr);
        int right = dfs(a, row + 1, col + 1, arr);
        
        arr[row][col] = a[row][col] + Math.min(left, Math.min(mid, right));
        
        return arr[row][col];
    }
}
```
