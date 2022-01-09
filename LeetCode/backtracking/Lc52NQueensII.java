// https://leetcode.com/problems/n-queens-ii/

/**
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/
class Solution {
    /**   col + row               col - row
            0   1   2   3           0   1   2   3
            -------------------------------------
        0 | 0   1   2   3           0   1   2   3
        1 | 1   2   3   4           -1  0   1   2   
        2 | 2   3   4   5           -2  -1  0   1        
        3 | 3   4   5   6           -3  -2  -1  0            
        */
    boolean[] cols, diag, antidiag;
    public int totalNQueens(int n) {
        cols = new boolean[n];
        diag = new boolean[2 * n - 1];
        antidiag = new boolean[2 * n - 1];
        
        return bt(n, 0);
    }
    
    private int bt(int n, int row) {
        if (row == n) return 1;
        
        int res = 0;
        
        for (int col = 0; col < n; col++) {
            int dIdx = row + col;
            int aIdx = row - col + n - 1; // to make index positive
            
            if (cols[col] || diag[dIdx] || antidiag[aIdx]) continue;
            
            cols[col] = diag[dIdx] = antidiag[aIdx] = true;
            
            res += bt(n, row + 1);
            
            cols[col] = diag[dIdx] = antidiag[aIdx] = false;
        }
        
        return res;
    }
}

class Solution {
    
    // ========= Method 1: Back tracking ===========
    // Approach 2: Use the property of  diagonals. Not store Queens.
    // 1ms (94.73%)
    int res;
    public int totalNQueens(int n) {
        res = 0;
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2 * n];  // diagnoals \
        boolean[] d2 = new boolean[2 * n];  // diagnoals /
        
        bt(0, cols, d1, d2, n);
        return res;
    }
    
    private void bt(int row, boolean[] cols, boolean[] d1, boolean[] d2, int n) {
        if (row == n) {
            res++;
            return;
        }
        /**   col + row               col - row
            0   1   2   3           0   1   2   3
            -------------------------------------
        0 | 0   1   2   3           0   1   2   3
        1 | 1   2   3   4           -1  0   1   2   
        2 | 2   3   4   5           -2  -1  0   1        
        3 | 3   4   5   6           -3  -2  -1  0            
        */
        for (int col = 0; col < n; col++) {
            int id1 = col - row + n;
            int id2 = col + row;
            
            if (cols[col] || d1[id1] || d2[id2]) continue;
            
            cols[col] = true; d1[id1] = true; d2[id2] = true;
            bt(row + 1, cols, d1, d2, n);
            cols[col] = false; d1[id1] = false; d2[id2] = false;
        }
    }
    
    // Approach1 : 2ms (56.10%)
    char[][] board;
    int ans;
    public int totalNQueens(int n) {
        board = new char[n][n];
        ans = 0;
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         board[i][j] = 'X';
        //     }
        // }
        dfs(0);
        
        return ans;
    }
    
    private void dfs(int col) {
        if (col == board.length) {
            // for (int i = 0; i < board.length; i++) {
            //     for (int j = 0; j < board.length; j++) {
            //         System.out.print(board[i][j]);
            //     }
            //     System.out.println();
            // }
            // System.out.println("================");
            ans++;
            return;
        }
        
        for (int i = 0; i < board.length; i++) {
            if (isSafe(i, col)) {
                board[i][col] = 'Q';
                dfs(col + 1);
                board[i][col] = 'X';
            }
        }
    }
    
    private boolean isSafe(int x, int y) {
        // left row
        for (int i = 0; i < y; i++) {
            if (board[x][i] == 'Q') return false;
        }
        // upper diag
        for (int i = x, j = y; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        
        //lower diag
        for (int i = x, j = y; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') return false;
        }
        
        return true;
    }
}
