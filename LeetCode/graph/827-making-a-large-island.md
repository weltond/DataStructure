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
### Approach 1 - DFS
- Explanation

Only 2 steps:

1. Explore every island using DFS, count its area, give it an island index and save the result to a {index: area} map.
2. Loop every cell == 0, check its connected islands and calculate total islands area.

- Complexity
1. Time O(N^2)
2. Space O(N^2)

```java
/**
input: [[1,0,1],[0,1,1],[1,0,0]]
output: map: {2=1, 3=3, 4=1}
*/
class Solution {
    int[] dir = {0, 1, 0, -1, 0};
    public int largestIsland(int[][] grid) {
        Map<Integer, Integer> map = new HashMap();  // <index, count>
        
        int n = grid.length;
        int index = 2;
        
        int res = 0;
        
        // iterate through all 1's and store connected 1 in map with the same index.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int islands = dfs(grid, i, j, index);
                    map.put(index++, islands);
                    
                    // update res in case no zero
                    res = Math.max(res, islands);
                }
            }
        }

        // change 0 to 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> seen = new HashSet();  // stores used index
                    
                    int local = 1;
                    for (int[] neighbor : getNeighbors(grid, i, j)) {
                        int x = neighbor[0], y = neighbor[1];
                        index = grid[x][y];
                        
                        // skip connected
                        if (seen.contains(index)) continue;
                        
                        seen.add(index);
                        int count = map.get(index);
                        local += count;
                    }
                    
                    res = Math.max(res, local);
                }
            }
        }
        
        return res;
    }
    
    private List<int[]> getNeighbors(int[][] grid, int i, int j) {
        List<int[]> list = new ArrayList();
        
        for (int k = 0; k < 4; k++) {
            int nx = i + dir[k], ny = j + dir[k + 1];
            
            if (nx >= 0 && ny >= 0 && nx < grid.length && ny < grid.length && grid[nx][ny] > 1) {
                list.add(new int[]{nx, ny});
            }
        }
        
        return list;
    }
    
    private int dfs(int[][] grid, int i, int j, int index) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) return 0;
        
        int res = 1;
        grid[i][j] = index;
        
        for (int k = 0; k < 4; k++) {
            int nx = i + dir[k], ny = j + dir[k + 1];
        
            res += dfs(grid, nx, ny, index);
        }
        
        return res;
    }
}
```

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
