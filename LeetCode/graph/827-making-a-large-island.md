## [827. Making A Large Island](https://leetcode.com/problems/making-a-large-island/)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

 

Example 1:

```
Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
```

Example 2:

```
Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
```

Example 3:

```
Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 ```

Constraints:

- n == grid.length
- n == grid[i].length
- 1 <= n <= 500
- grid[i][j] is either 0 or 1.

## Answers

## Wrong Answers - TLE

Time: O(N^4), Space: O(N)

```java
class Solution {
    int[] dir = {0, 1, 0, -1, 0};
    public int largestIsland(int[][] grid) {
        int res = 0; // maxIsland(grid);
        boolean hasZero = false;
        
        for (int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = 1;
                    res = Math.max(res, dfs(grid, i, j, new boolean[grid.length][grid.length]));
                    grid[i][j] = 0;
                    
                    hasZero = true;
                }
            }
        }
        
        return hasZero ? res : grid.length * grid.length;
    }
    
//     private int maxIsland(int[][] grid) {
//         int res = 0;
        
//         for (int i = 0; i < grid.length; i++) {
//             for (int j = 0; j < grid[0].length; j++) {
//                 if (grid[i][j] == 1) {
//                     res = Math.max(res, dfs(grid, i, j, new boolean[grid.length][grid[0].length]));
//                 }
//             }
//         }
        
//         return res;
//     }
    
    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) return 0;
        
        int res = 1;
        visited[i][j] = true;
        
        for (int k = 0; k < 4; k++) {
            int nx = i + dir[k], ny = j + dir[k + 1];
        
            res += dfs(grid, nx, ny, visited);
        }
        
        return res;
    }
}
```
