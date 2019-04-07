// https://leetcode.com/problems/unique-paths-ii/

class Solution {
    
    // ================ Method 2: DP ==================
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int[][] dp = new int[m + 1][n + 1];
        dp[0][1] = 1;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (obstacleGrid[i - 1][j - 1] == 1) dp[i][j] = 0;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m][n];
    }
    
    // ================ Method 1: Recursion + Memo ==================
    // TLE
    int m;
    int n;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        this.m = obstacleGrid.length;
        this.n = obstacleGrid[0].length;
        
        return dfs(obstacleGrid, 0, 0, new int[m][n]);
    }
    
    private int dfs(int[][] arr, int x, int y, int[][] memo) {
        if (x < 0 || y < 0 || x >= this.m || y >= this.n || arr[x][y] == 1) return 0;
        if (memo[x][y] != 0) return memo[x][y];
        if (x == this.m - 1 && y == this.n - 1) return 1;
        
        int sum = dfs(arr, x + 1, y, memo) + dfs(arr, x, y + 1, memo);
        
        memo[x][y] = sum;
        
        return sum;
    }
}
