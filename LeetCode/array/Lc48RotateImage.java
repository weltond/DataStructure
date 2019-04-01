// https://leetcode.com/problems/rotate-image/

class Solution {
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
