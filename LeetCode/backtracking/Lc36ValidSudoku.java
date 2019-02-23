// https://leetcode.com/problems/valid-sudoku/
class Solution {
    char[][] board;
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0) return true;
        
        this.board = board;
        
        boolean[][] visited = new boolean[9][9];
        
        return bt(visited);
    }
    
    public boolean bt(boolean[][] visited) {
        int row = 0, col = 0;
        boolean isFinish = true;
        // find non-empty
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.board[i][j] != '.' && !visited[i][j]) {
                    row = i;
                    col = j;
                    isFinish = false;
                    break;
                }
            }
            if (!isFinish) break;
        }
        
        if (isFinish) return true;
        
        int tmp = board[row][col];
        if(isValid(row, col, board[row][col])) {
            visited[row][col] = true;
            
            if(bt(visited)) return true;
            
        }
        return false;
    }
    
    public boolean isValid(int x, int y, char num) {
        // base case
        if (x >= this.board.length || y >= this.board[0].length || x < 0 || y < 0) {
            return false;
        }
        
        // no duplicate in its current row
        for (int i = 0; i < this.board[0].length; i++) {
            if (i != y && this.board[x][i] == num) {
                return false;
            }
        }
        
        // no duplicate in its current col
        for (int i = 0; i < this.board.length; i++) {
            if (i != x && this.board[i][y] == num) {
                return false;
            }
        }
        
        // no duplicate in its cube
        // sqrt(board.length) = 3
        int startRow = x - x % 3;
        int startCol = y - y % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if((i + startRow) != x && (j + startCol) != y && this.board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
