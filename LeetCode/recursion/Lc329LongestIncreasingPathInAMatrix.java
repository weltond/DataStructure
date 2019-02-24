// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

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
