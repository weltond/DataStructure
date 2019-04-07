// https://leetcode.com/problems/unique-paths/

class Solution {
    // ========== Method 2: DP ============
    // 0ms
    // Approach 2:
    public int uniquePaths(int m, int n) {
        // dp[i][j] means how many possible unique paths to reach position (i,j)
        // dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        
        int[][] dp = new int[m + 1][n + 1]; // edges are 0
        dp[0][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m][n];
    }
    
    // Approach 1: 
    // dp[m][n] = dp[m+1][n] + dp[m][n+1]
    public int uniquePaths(int m, int n) {
        
        int[][] dp = new int[m+1][n+1];
        // base case
        dp[m][n-1] = 1;
        int t1 = 1, t2 = 0;
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }
        
        return dp[0][0];

    }
    
    // ========== Method 1: Backtracking =========
    // 0ms
    // Approach 2:
    int m, n;
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        this.m = m;
        this.n = n;
        //Arrays.fill(memo, -1);
        return dfs(0, 0, memo);
    }
    
    private int dfs(int x, int y, int[][] memo) {
        if (x >= m || y >= n || x < 0 || y < 0) return 0;
        
        if (memo[x][y] != 0) return memo[x][y];
        
        if (x == m - 1 && y == n - 1) return 1;
        
        int sum = dfs(x + 1, y, memo) + dfs(x, y + 1, memo);
        
        memo[x][y] = sum;
        
        return sum;
    }
    
    // Approach 1:
    int m, n;
    public int uniquePaths(int m, int n) {
        this.m = m; this.n = n;
        int[][] memo = new int[m+1][n+1];
        int res = dfs(1, 1, new boolean[m+1][n+1], memo);
        
        return res;
    }
    
    // DFS with MEMO
    private int dfs(int x, int y, boolean[][] visited, int[][] memo) {
        if (x == m && y == n) {
            return 1;
        }
        
        if (x > m || y > n || visited[x][y]) return 0;
        
        if (memo[x][y] != 0) return memo[x][y];
        
        visited[x][y] = true;
        
        // right
        int right = dfs(x, y+1, visited, memo);
        
        // down
        int down = dfs(x + 1, y, visited, memo);
        
        memo[x][y] = right + down;

        visited[x][y] = false;
        
        return memo[x][y];
    }
    
    // DFS without MEMO. (TLE)
//     private void dfsWithouMemo(int x, int y, int[] res, boolean[][] visited) {
//         if (x == m && y == n) {
//             res[0] += 1;
//             return;
//         }
        
//         if (x > m || y > n || visited[x][y]) return;
        
//         visited[x][y] = true;
        
//         // right
//         dfs(x, y+1, res, visited);
        
//         // down
//         dfs(x + 1, y, res, visited);
        
//         visited[x][y] = false;
//     }
}
