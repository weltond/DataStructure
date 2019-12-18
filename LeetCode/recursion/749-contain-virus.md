## [749. Contain Virus](https://leetcode.com/problems/contain-virus/)

A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.

The world is modeled as a 2-D array of cells, where `0` represents uninfected cells, and `1` represents cells contaminated with the virus. A wall (and only one wall) can be installed **between any two 4-directionally adjacent cells**, on the shared boundary.

Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited. Each day, you can install walls around only one region -- the affected area (continuous block of infected cells) that threatens the most uninfected cells the following night. There will never be a tie.

Can you save the day? If so, what is the number of walls required? If not, and the world becomes fully infected, return the number of walls used.

Example 1:

```
Input: grid = 
[[0,1,0,0,0,0,0,1],
 [0,1,0,0,0,0,0,1],
 [0,0,0,0,0,0,0,1],
 [0,0,0,0,0,0,0,0]]
 
Output: 10

Explanation:
There are 2 contaminated regions.
On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:

[[0,1,0,0,0,0,1,1],
 [0,1,0,0,0,0,1,1],
 [0,0,0,0,0,0,1,1],
 [0,0,0,0,0,0,0,1]]

On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.
```

Example 2:

```
Input: grid = 
[[1,1,1],
 [1,0,1],
 [1,1,1]]
 
Output: 4

Explanation: Even though there is only one cell saved, there are 4 walls built.
Notice that walls are only built on the shared boundary of two different cells.
```

Example 3:

```
Input: grid = 
[[1,1,1,0,0,0,0,0,0],
 [1,0,1,0,1,1,1,1,1],
 [1,1,1,0,0,0,0,0,0]]
 
Output: 13

Explanation: The region on the left only builds two new walls.
```

Note:
- The number of rows and columns of grid will each be in the range `[1, 50]`.
- Each grid[i][j] will be either `0` or `1`.
- Throughout the described process, there is always a contiguous viral region that will infect **strictly more** uncontaminated squares in the next round.

## Answer
### Method 1 - DFS - :rocket: 4ms (100%)

```java
class Solution {
    private int R;
    private int C;
    private static final int[] dir = {0, 1, 0, -1, 0};
    
    public int containVirus(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        int ans = 0;
        
        while (true) {
            int walls = process(grid);
            ans += walls;
            if (walls == 0) break;  // no new walls to build
        }
        
        return ans;
    }
    
    private int process(int[][] grid) {
        int maxArea = 0, ans = 0, color = -1, row = -1, col = -1;
        
        // if grid is 1: then 1 means virus visited, otherwise 0
        // if grid is 0: different color to indicate being affected by different virus
        int[][] visited = new int[R][C];
        
        // find max zero area
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    int[] walls = {0};
                    int area = dfs(grid, visited, i, j, color, walls);
                    //System.out.println(walls[0]+","+area);
                    if (area > maxArea) {
                        maxArea = area;
                        ans = walls[0];
                        row = i;
                        col = j;
                    }
                    color--;    // different islands using different color
                }
            }
        }
        //System.out.println("--");
        // remove virus region
        removeIsland(grid, row, col);
        // spread by one step
        // here, 1 means 0 is visited.
        visited = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    spread(grid, visited, i, j);
                }
            }
        }
        
        return ans;
    }
    
    // get the area of zero around the island 1s. 
    // different 1s island using different color
    private int dfs(int[][] grid, int[][] visited, int r, int c, int color, int[] walls) {
        if (r < 0 || r >= R || c < 0 || c >= C) return 0;
        
        if (grid[r][c] == 0) {
            walls[0]++; // for every adjacent 0, we need to add a wall for it no matter visted or not
            if (visited[r][c] == color) return 0;   // already visited this zero point
            visited[r][c] = color;
            return 1;   // one zero point
        }
        
        // already visited point and remvoed islands
        if (visited[r][c] == 1 || grid[r][c] != 1) return 0;
        
        // set this point as visited
        visited[r][c] = 1;
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            int x = r + dir[i];
            int y = c + dir[i + 1];
            ans += dfs(grid, visited, x, y, color, walls);  // sum all zero point
        }
        
        return ans;
    }
    
    private void removeIsland(int[][] grid, int r, int c) {
        if (r < 0 || r >= R || c < 0 || c >= C || grid[r][c] != 1) return ;
        
        grid[r][c] = -1;    // remove it
        for (int i = 0; i < 4; i++) {
            int x = r + dir[i];
            int y = c + dir[i + 1];
            removeIsland(grid, x, y);
        }
    }
    
    private void spread(int[][] grid, int[][] visited, int r, int c) {
        if (r < 0 || r >= R || c < 0 || c >= C || visited[r][c] == 1 || grid[r][c] == -1) return ;
    
        visited[r][c] = 1;
        if (grid[r][c] == 0) {
            grid[r][c] = 1;
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int x = r + dir[i];
            int y = c + dir[i + 1];
            spread(grid, visited, x, y);
        }
    }
}
```
