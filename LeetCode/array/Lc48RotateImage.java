// https://leetcode.com/problems/rotate-image/
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        
        int n = matrix.length;
        /**
        [
          [1,2,3],              [1,4,7]
          [4,5,6],      ===>    [2,5,8]
          [7,8,9]               [3,6,9]
        ],
        */
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        
        /**
        [
          [1,4,7],              [7,4,1]
          [2,5,8],      ===>    [8,5,2]
          [3,6,9]               [9,6,3]
        ],
        */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) { 
                swap(matrix, i, j, i, n - j - 1);
            }
        }
    }
    
    private void swap(int[][] arr, int x1, int y1, int x2, int y2) {
        int tmp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = tmp;
    }
}

class Solution {
    // ============= Iteration ==================
    // 0ms
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        /**
        [
          [1,2,3],              [9,6,3]
          [4,5,6],      ===>    [8,5,2]
          [7,8,9]               [7,4,1]
        ],
        */
        int len = matrix.length;
        // swap diag
        for (int i = 0; i < len; i++) {         // row
            for (int j = 0; j < len - i; j++) { // col
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[len - 1 - j][len - 1 - i];
                matrix[len - 1 - j][len - 1 - i] = tmp;
            }
        }
        /**
        [
          [9,6,3],              [7,4,1]
          [8,5,2],      ===>    [8,5,2]
          [7,4,1]               [9,6,3]
        ],
        */
        // swap top and bottom
        for (int i = 0; i < len; i++) {         // col
            for (int j = 0; j < len / 2; j++) { // row
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[len - 1 - j][i];
                matrix[len - 1 - j][i] = tmp;
            }
        }
    }
    
    // ============= Recursion ==================
    // 0ms (100%)
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        /**
        [
          [1,2,3],              [9,6,3]
          [4,5,6],      ===>    [8,5,2]
          [7,8,9]               [7,4,1]
        ],
        */
        int len = matrix.length;
        dfs(matrix, 0, len - 1);
        
        /**
        [
          [9,6,3],              [7,4,1]
          [8,5,2],      ===>    [8,5,2]
          [7,4,1]               [9,6,3]
        ],
        */
        for (int i = 0; i < len; i++) {
            int s = 0, e = len - 1;
            while (s < e) {
                swap(matrix, s++, i, e--, i);
            }
        }
    }
    
    private void dfs(int[][] matrix, int x, int y) {
        if (x == matrix.length - 1 && y == 0) {
            return;
        }
        
        for (int i = 0; i < y; i++) {
            swap(matrix, x, i, matrix.length - 1 - i, y);
        }
        
        dfs(matrix, x + 1, y - 1);
    }
    
    private void swap(int[][] matrix, int a, int b, int c, int d) {
        //System.out.println(a + ", " + b + " ; " + c + ", " + d);
        int tmp = matrix[a][b];
        matrix[a][b] = matrix[c][d];
        matrix[c][d] = tmp;
    }
    
}
