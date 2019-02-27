//https://leetcode.com/problems/diagonal-traverse/

class Solution {
    // n * m matrix.
    // If out of:
    // 1. top: row = 0
    // 2. left: col = 0
    // 3. right: row += 2, col = m - 1
    // 4. bottom: col += 2, row = n - 1
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[]{};
        
        int n = matrix.length, m = matrix[0].length;
        int[] res = new int[n * m];
        int[][] dir = {{-1, 1}, {1, -1}};  // dir[0] is upper-right, dir[1] is bottom-left
        int row = 0, col = 0, idx = 0;
        for (int i = 0; i < n * m; i++) {
            res[i] = matrix[row][col];
            row += dir[idx][0];
            col += dir[idx][1];
 
            // continue if row & col not out of bound
            if (row >= 0 && col >= 0 && row < n && col < m) continue;
            
            // change direction since row / col out of bound
            idx = 1 - idx;
            
            // IMPORTANT: the order of following case matters!!!
            // exceed right
            if (col >= m) {
                row += 2;
                col = m - 1;
            }
            
            // exceed bottom
            if (row >= n) {
                row = n - 1;
                col += 2;
            }
            
            // exceed top
            if (row < 0) {
                row = 0;
            }
            // exceed left
            if (col < 0) {
                col = 0;
            }
            
        }
        
        return res;
    }
}
