## Question

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

### Method 2 Simulation
:rocket: **0ms**
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
### Method 1
