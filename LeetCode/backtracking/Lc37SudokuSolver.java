/**
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.
*/

class Solution {
    public void solveSudoku(char[][] board) {
        // List<Set<Integer>> listRow = new ArrayList();
        // List<Set<Integer>> listCol = new ArrayList();
        // for (int i = 0; i < 9; i++) {
        //     listRow.add(new HashSet());
        //     listCol.add(new HashSet());
        // }
        // // put original values into hash set to limit possible trial
        // for (int i = 0; i < 9; i++) {
        //     for (int j = 0; j < 9; j++) {
        //         if (board[i][j] != '.') {
        //             listRow.get(i).add(board[i][j] - '0');
        //             listRow.get(j).add(board[i][j] - '0');
        //         }
        //     }
        // }
        //backtracking(board, listRow, listCol);
        backtracking(board);
    }
    
    private boolean backtracking(char[][] board) {
        int row = 0, col = 0;
        boolean isFinish = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    row = i;
                    col = j;
                    isFinish = false;
                    break;
                }
            }
            if (!isFinish) break;
        }
        
        if (isFinish) return true;
        
        // put every possible values into current row,col
        for (int k = 1; k <= 9; k++) {
            if (isValid(board, row, col, k)) {
                board[row][col] = (char) (k + '0');
                
                if (backtracking(board)) {
                    return true;
                }
                
                board[row][col] = '.';
            }
        }
        
        return false;
    }
    
    private boolean isValid (char[][] board, int x, int y, int num) {  
        if (x < 0 || x >= 9 || y < 0 || y >= 9 || board[x][y] != '.') {
            return false;
        }
        
        // no duplicate in its current row
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == num + '0') return false;
        }
        
        // no duplicate in its current col
        for (int j = 0; j < 9; j++) {
            if (board[j][y] == num + '0') return false;
        }
        
        // no duplicate in its cube
        int startRow = x - x % 3;
        int startCol = y - y % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num + '0') {
                    return false;
                }
            }
        }
        
        return true;
         
    }
}
