## [Question](https://leetcode.com/problems/spiral-matrix/)

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

```
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
```

Example 2:

```
Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
```
## Answer
### Method 2 - Simulation
:rocket: **0ms**
- Time: O(n)
- Space: O(n)
```java
class Solution {
    // ======= Method 2: Simulation ========
    // 0ms 
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList();
        if (matrix.length == 0) return ans;
        
        int m = matrix.length, n = matrix[0].length;
        boolean[][] seen = new boolean[m][n];
        
        int[] dr = {0, 1, 0, -1};   // right, down, left, up
        int[] dc = {1, 0, -1, 0};
        
        int r = 0, c = 0, dir = 0;
        for (int i = 0; i < m * n; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && !seen[nr][nc]) {
                r = nr;
                c = nc;
            } else {
                dir = (dir + 1) % 4;
                r += dr[dir];
                c += dc[dir];
            }
        }
        
        return ans;
    }
}
```
### Method 1 - Recursion
:rocket: 0ms
```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList();
        // base case
        if (matrix == null || matrix.length == 0) return res;
        
        int n = matrix.length, m = matrix[0].length;
        
        // matrix, startX, startY, needed_row_number, needed_col_number, res
        print(matrix, 0, 0, n, m, res);
        
        return res;
    }
    
    private void print(int[][] matrix, int startX, int startY, int n, int m, List<Integer> res) {
        if (n <= 0 || m <= 0) return;
        
        if (n == 1 && m == 1) {
            res.add(matrix[startX][startY]);
            return;
        } 
        // avoid duplicate for situations like :  [1 2 3]
        else if (n == 1 || m == 1) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    res.add(matrix[startX + i][startY + j]);
                }
            }
            return;
        }
        
        int rowNum = m - 1;
        int colNum = n - 1;
        // top row (left -> right)
        for (int i = startX; i < rowNum + startY; i++) {
            res.add(matrix[startX][i]);
        }
        
        // right col (top -> down)
        for (int i = startX; i < colNum + startX; i++) {
            res.add(matrix[i][m - 1 + startY]);
        }
        
        // bottom row (right -> left)
        for (int i = m - 1 + startY; i >= m - rowNum + startY; i--) { // startY + 1 = m - rowNum + startY ?
            res.add(matrix[n - 1 + startY][i]);
        }
        
        // left col (down -> top)
        for (int i = n - 1 + startX; i >= n - colNum + startX; i--) {
            res.add(matrix[i][startY]);
        }
        
        // recursive call
        print(matrix, startX + 1, startY + 1, n - 2, m - 2, res);
    }
}
```
