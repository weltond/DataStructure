## [304. Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/)

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner `(row1, col1)` and lower right corner `(row2, col2)`.

Range Sum Query 2D
The above rectangle (with the red border) is defined by `(row1, col1) = (2, 1)` and `(row2, col2) = (4, 3)`, which contains `sum = 8`.

Example:
```
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
```

Note:
- You may assume that the matrix does not change.
- There are many calls to sumRegion function.
- You may assume that `row1 ≤ row2` and `col1 ≤ col2`.

## Answer
### Method 1 - DP - :rabbit: 57ms (57.82%)
```java
class NumMatrix {
    // ========== Method 1: Naive DP =============
    // 57ms (57.82%)
    int[][] dp;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        
        dp = new int[matrix.length][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                dp[i][j] = matrix[i][j] + ((j == matrix[0].length - 1) ? 0 : dp[i][j + 1]);
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int i = row1; i <= row2; i++) {
            res = res + dp[i][col1] - dp[i][col2 + 1];
        }
        
        return res;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
```
