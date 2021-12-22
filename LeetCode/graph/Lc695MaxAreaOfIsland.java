// 2ms (99.31%)
class Solution {
    int[] dir = {0, 1, 0, -1, 0};
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }

        return res;
    }

    private int dfs(int[][] grid, int x, int y) {
        if (x >= grid.length || y >= grid[0].length || x < 0 || y < 0 ||
        grid[x][y] == 0) {
            return 0;
        }

        int count = 1;
        grid[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            count += dfs(grid, x + dir[i], y + dir[i + 1]);
        }

        return count;
    }
}

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;
        
        boolean[][] visited = new boolean[n][m];
        
        int[] res = {0};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    int[] cnt = {0};
                    dfs(grid, i, j, visited, cnt, res);
                }
            }
        }
        return res[0];
    }
    
    private void dfs(int[][] grid, int x, int y, boolean[][] visited, int[] cnt, int[] res) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0 || visited[x][y])
            return;
        
        cnt[0] += 1;
        res[0] = Math.max(res[0], cnt[0]);
        
        visited[x][y] = true;
        
        int[] dirX = {-1, 0, 1, 0};
        int[] dirY = {0, 1, 0, -1};
        
        for (int i = 0; i < 4; i++) {
            dfs(grid, x + dirX[i], y + dirY[i], visited, cnt, res);
        }
        
    }
}
