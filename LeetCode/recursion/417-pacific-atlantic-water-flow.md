## [417. Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)

Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:

- The order of returned grid coordinates does not matter.
- Both m and n are less than 150.
 

Example:

```
Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
```

## Answer
### Method 1 - DFS - :rocket: 4ms (98.26%)

```java
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];
        
        for (int i = 0; i < row; i++) {
            // check if can reach left pacific
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            // check if can reach right atlantic
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, col - 1);
        }
        
        for (int i = 0; i < col; i++) {
            // check if can reach top pacific
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            // check if can reach bottom atlantic
            dfs(matrix, atlantic, Integer.MIN_VALUE, row - 1, i);
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> list = new ArrayList();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }
        
        return res;
    }
    
    public void dfs(int[][] arr, boolean[][] visited, int height, int x, int y) {
        if (x >= arr.length || y >= arr[0].length || x < 0 || y < 0 || visited[x][y] || arr[x][y] < height) return;
        
        visited[x][y] = true;
        
        int[] dir = {0, 1, 0, -1, 0};
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i];
            int ny = y + dir[i + 1];
            
            dfs(arr, visited, arr[x][y], nx, ny);
        }
    }
}
```
