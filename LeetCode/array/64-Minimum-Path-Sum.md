## [Question](https://leetcode.com/problems/minimum-path-sum/)

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:
```
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
```

## Answer
### Method 1 - DFS + Memoization
:rocket: 1ms (99.67%) 
```java
class Solution {
    // ========= Method 1: DFS + memo ===========
    // 1ms (99.67%)
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        
        return dfs(grid, 0, 0, memo);
    }
    
    private int dfs(int[][] grid, int x, int y, int[][] memo) {
        if (x >= grid.length || x < 0 || y >= grid[0].length || y < 0) return Integer.MAX_VALUE;
        
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            return grid[x][y];
        }
        
        int right = dfs(grid, x, y + 1, memo);
        int down = dfs(grid, x + 1, y, memo);
        int res = grid[x][y] + Math.min(right, down);
        
        memo[x][y] = res;
        
        return res;
    }
}
```
### Method 2 - DP
:rabbit: 3ms (21.22%) 
```java
class Solution {
    // ======== Method 2 : DP =========
    // 3ms (21.22%)
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        
        int[][] arr = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    arr[i][j] = grid[0][0];
                    continue;
                } 
                
                arr[i][j] = Math.min(getDist(arr, i - 1, j), getDist(arr, i, j - 1)) + grid[i][j];
            }
        }
        
        return arr[m - 1][n - 1];
    }
    
    private int getDist(int[][] arr, int x, int y) {
        if (x < 0 || y < 0) {
            return Integer.MAX_VALUE;
        }
        
        return arr[x][y];
    }
}
```
