// https://leetcode.com/problems/unique-paths-iii/
class Solution {
    int ans;
    int[] dirX = new int[]{0, -1, 0, 1};
    int[] dirY = new int[]{1, 0, -1, 0};
    int endX = 0, endY = 0;
    public int uniquePathsIII(int[][] grid) {
        if (grid == null) return 0;
        
        int n = grid.length;
        int m = grid[0].length;
        
        int obs = 0;    // obstacles
        int startX = 0, startY = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == -1) {
                    obs++;
                }
                
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 2) {
                    endX = i;
                    endY = j;
                }
                
            }
        }
        
        int left = m * n - obs - 1; // total left to reach end
        boolean[][] visited = new boolean[n][m];
        dfs(grid, startX, startY, left, visited);
        
        return ans;
    }
    
    private void dfs(int[][] grid, int startX, int startY, int left, boolean[][] visited) {
        if (startX == endX && startY == endY) {
            if (left == 0) {
                ans++;
            }
            return;
        }
        
        if (startX < 0 || startY < 0 || startX >= grid.length || startY >= grid[0].length ||
           grid[startX][startY] == -1 || visited[startX][startY]) {
            return;
        }
        
        visited[startX][startY] = true;
        
        for (int i = 0; i < 4; i++) {
            dfs(grid, startX + dirX[i], startY + dirY[i], left - 1, visited);
        }
        
        visited[startX][startY] = false;
        
    }
}
