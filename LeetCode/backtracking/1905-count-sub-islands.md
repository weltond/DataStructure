## [1905. Count Sub Islands](https://leetcode.com/problems/count-sub-islands/description/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

 

Example 1:

```
Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
```

Example 2:

```
Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
 ```

**Constraints:**

- m == grid1.length == grid2.length
- n == grid1[i].length == grid2[i].length
- 1 <= m, n <= 500
- grid1[i][j] and grid2[i][j] are either 0 or 1.

## Answers
### Method 1 - DFS - 27ms (42.97%) :turtle

Flood fill grid2 when `grid2[i][j]` is 1 but `grid1[i][j]` is 0 ==> not a subset.

```java
class Solution {
    int[] dir = {0, 1, 0, -1, 0};
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;

        // flood fill group that grid1 is 0 and grid2 is 1
        // which means grid2 is not a sub set of grid1.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    dfs(grid1, grid2, i, j);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if (grid2[i][j] == 1) {
                    dfs(grid1, grid2, i, j);
                    res++;
                }
            }
        }

        return res;
    }

    private void dfs(int[][] grid1, int[][] grid2, int x, int y) {
        if (x >= grid1.length || y >= grid1[0].length ||  x < 0 || y < 0) {
            return;
        } 

        if (grid2[x][y] == 0) {
            return;
        }

        grid2[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            dfs(grid1, grid2, x + dir[i], y + dir[i + 1]);
        }
    }
}
```
