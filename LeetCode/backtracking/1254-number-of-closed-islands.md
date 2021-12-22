## [1254. Number of closed islands](https://leetcode.com/problems/number-of-closed-islands/description/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 

Example 1:
```
Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
```
Example 2:

```
Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
```

Example 3:
```
Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2
```

**Constraints:
**
- 1 <= grid.length, grid[0].length <= 100
- 0 <= grid[i][j] <=1

## Answer
### Method 1 - DFS 2ms (66.67%) 🐰
Flood fill all land on the edges. The rest are closed islands.

```java
class Solution {
    int[] dir = {0, 1, 0, -1, 0};
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // flood fill top row and bot row
        for (int i = 0; i < n; i++) {
            dfs(grid, 0, i);
            dfs(grid, m - 1, i);
        }

        // flood fill first col and last col
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }

        int res = 0;

        // rest are closed islands
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }

        return res;
    }

    private void dfs(int[][] grid, int x, int y) {
        if (x >= grid.length || y >= grid[0].length || 
        x < 0 || y < 0 || grid[x][y] == 1) {
            return;
        }

        // turn to water
        grid[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            dfs(grid, x + dir[i], y + dir[i + 1]);
        }
    }
}
```
