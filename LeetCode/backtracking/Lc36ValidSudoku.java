// https://leetcode.com/problems/valid-sudoku/
class Solution {
    // ========== Method 3: Array =============
    // 12ms
    // Idea is: each row, col and cube is 3 different things. Calculate each by using its coordinates.
    //      For row and col is easy, as to 9 cubes: they are mapped by -> (i / 3) * 3 + j / 3
    public boolean isValidSudoku(char[][] board) {
        if (board == null) return false;
        
        int[][] row = new int[9][9];
        int[][] col = new int[9][9];
        int[][] cube = new int[9][9];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') continue;
                
                int num = board[i][j] - '0' - 1;
                
                if (row[i][num] == 1 || col[j][num] == 1 || cube[(i / 3) * 3 + (j / 3)][num] == 1) return false;
                
                row[i][num] = 1;
                col[j][num] = 1;
                // (i / 3) * 3 + (j / 3) is the k-th cube number where k = 0 ~ 9 
                cube[(i / 3) * 3 + (j / 3)][num] = 1;
            }
        }
        
        return true;
    }
    
    // ========== Method 2: Hash Style ===========
    // 16ms
    public boolean isValidSudoku(char[][] board) {
        if (board == null) return false;
        
        for (int i = 0; i < board.length; i++) {
            Set<Character> setRow = new HashSet();
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') continue;
                
                if (setRow.contains(board[i][j]) || checkCube(board, i, j)) return false;
                
                setRow.add(board[i][j]);
            }
        }
        
        for (int i = 0; i < board[0].length; i++) {
            Set<Character> setCol = new HashSet();
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == '.') continue;
                
                if (setCol.contains(board[j][i])) return false;
                
                setCol.add(board[j][i]);
            }
        }
        
        return true;
    }
    
    private boolean checkCube(char[][] board, int x, int y) {
        Set<Character> setCube = new HashSet();
        int startX = x - x % 3;
        int startY = y - y % 3;
        for (int i = startX; i < 3 + startX; i++) {
            for (int j = startY; j < 3 + startY; j++) {
                if (setCube.contains(board[i][j])) return true;
                if (board[i][j] != '.') {
                    setCube.add(board[i][j]);
                }
            }
        }
        return false;
    }
    
    // ========== Method 1: Backtracking Style ==========
    // 14ms
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
