## [694. Number of Distinct Islands]()

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical). You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island has the same shape as another island (and not rotated or reflected).

Notice that:

```
11
1
```
and

```
 1
11
```

are considered different island, because we do not consider reflection / rotation.

Example 1:

```
Input: 
  [
    [1,1,0,0,1],
    [1,0,0,0,0],
    [1,1,0,0,1],
    [0,1,0,1,1]
  ]
Output: 3
Explanation:
  11   1    1
  1        11   
  11
   1
```

Example 2:

```
Input:
  [
    [1,1,0,0,0],
    [1,1,0,0,0],
    [0,0,0,1,1],
    [0,0,0,1,1]
  ]
Output: 1
```

Notice
- The length of each dimension in the given grid does not exceed 50.

## Answer
### Method 1 - DFS - :rabbit: 322ms (48.80%)

```java
public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
    public int numberofDistinctIslands(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0) return 0;
        
        int res = 0;
        Set<String> set = new HashSet();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    String s = dfs(grid,i,j);
                    //System.out.println(s);
                    set.add(s);
                }
            }
        }
        
        return set.size();
    }
    
    private String dfs(int[][] grid, int x, int y) {
        if (x >= grid.length || y >= grid[0].length || x < 0 || y < 0 || grid[x][y] != 1) return ";";
        
        grid[x][y] = 2;
        
        int[] dir = {0, 1, 0, -1, 0};
        String ret = "";
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i], ny = y + dir[i + 1];
            ret += i + dfs(grid, nx, ny);
        }
        
        return ret;
    }
}
```

```java
public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
    boolean[][] seen;
    public int numberofDistinctIslands(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0) return 0;
        
        seen = new boolean[grid.length][grid[0].length];
        Set<String> set = new HashSet();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 || seen[i][j]) continue;
                String s = dfs(grid, i, j);
                //System.out.println(s);
                set.add(s);
            }
        }
        
        return set.size();
    }
    private String dfs(int[][] grid, int x, int y) {
        if (x >= grid.length || y >= grid[0].length || x < 0 || y < 0 || grid[x][y] == 0 || seen[x][y]) return "";
        
        int[] dir = {0, 1, 0, -1, 0};
        
        String res = "";
        seen[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i], ny = y + dir[i + 1];
            
            res += i + "" + dfs(grid, nx, ny);
        }
        
        return res;
    }
    
}
```
