// https://leetcode.com/problems/search-a-2d-matrix-ii/

/**
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
*/
// 5ms (99.99%)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
        int i = 0, j = matrix[0].length - 1;
        
        while (i < matrix.length && j >= 0) {
            if (target == matrix[i][j]) return true;
            else if (target < matrix[i][j]) j--;
            else i++;
        }
        
        return false;
    }
}
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return false;
        
        // return dfs(matrix, 0, 0, target);
        return dfs2(matrix, 0, matrix[0].length - 1, target);
    }
    
    // ============= Method 2: DFS : from top-right =============
    // 6ms (30.05%)
    private boolean dfs2(int[][] m, int x, int y, int t) {
        if (x >= m.length || y >= m[0].length || x < 0 || y < 0) {
            return false;
        }
        if (t == m[x][y]) {
            return true;
        } else if (t < m[x][y]) {
            return dfs(m, x, y - 1, t);
        } else {
            return dfs(m, x + 1, y, t);   
        }

    }
    // ========= Method 1: DFS: start from top-left ============
    // TLE
//     private boolean dfs(int[][] m, int x, int y, int t) {
//         if (x >= m.length || y >= m[0].length || x < 0 || y < 0 || m[x][y] > t) {
//             return false;
//         }
//         if (t == m[x][y]) return true;
        
//         return dfs(m, x + 1, y, t) || dfs(m, x, y + 1, t);
        
//     }
}
