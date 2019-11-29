// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

class Solution {
    // 8ms (72.52%)
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int[][] memo = new int[matrix.length][matrix[0].length];
        
        for (int[] a : memo) {
            Arrays.fill(a, -1);
        }
        
        int res = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res = Math.max(res, dfs(matrix, i, j, memo));
            }
        }
        return res;
    }
    
    private int dfs(int[][] arr, int i, int j, int[][] memo) {
        if (memo[i][j] != -1) return memo[i][j];

        int res = 1;
        int[] dir = {0, -1, 0, 1, 0};
        
        for (int k = 0; k < 4; k++) {
            int x = i + dir[k];
            int y = j + dir[k + 1];

            if (x >= arr.length || y >= arr[0].length || x < 0 || y < 0 || arr[x][y] >= arr[i][j]) {
                continue;
            }
            
            res = Math.max(res, dfs(arr, x, y, memo) + 1);
        }
        
        memo[i][j] = res;
        
        return memo[i][j];
        
    }
}

// ====== DFS + Memoization ========
class Solution {
    int[][] arr;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int n = matrix.length;
        int m = matrix[0].length;
        arr = new int[n][m];
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res = Math.max(res, dfs(matrix, i, j));
            }
        }
        
        return res;
        
    }
    
    // max path from (x,y)
    private int dfs(int[][] matrix, int x, int y) {
        if (arr[x][y] != 0) return arr[x][y];
        
        int[] dir = {0, 1, 0, -1, 0};
        
        //current to current max path should be 1
        arr[x][y] = 1;

        // dfs 4 directions
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i];
            int ny = y + dir[i + 1];
            
            // out of bound or its neighbor values are even smaller than it
            if (nx >= matrix.length || ny >= matrix[0].length || nx < 0 || ny < 0 || matrix[x][y] >= matrix[nx][ny]) 
                continue;

            arr[x][y] = Math.max(arr[x][y], 1 + dfs(matrix, nx, ny));
        }
        
        return arr[x][y];
    }
}
