/**
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. 
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 
Two cells are connected if they are adjacent cells connected horizontally or vertically.
*/

// 1ms 99.76%
class Solution {
    public void solve(char[][] b) {
        // Set for first and last row
        for (int c = 0; c < b[0].length; c++) {
            if (b[0][c] == 'O') {
                dfs(b, 0, c);
            }
            if (b[b.length - 1][c] == 'O'){
                dfs(b, b.length - 1, c);
            }
        }
        
        // Set for first and last column
        for (int r = 0; r < b.length; r++) {
            if (b[r][0] == 'O') dfs(b, r, 0);
            if (b[r][b[0].length - 1] == 'O') dfs(b, r, b[0].length - 1);
        }
        
        // Set all
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (b[i][j] == 'z') b[i][j] = 'O';
                else if (b[i][j] == 'O') b[i][j] = 'X';
            }
        }
    }
    
    private void dfs(char[][] b, int x, int y) {
        if (x < 0 || y < 0 || x >= b.length || y >= b[0].length || b[x][y] == 'z') return;
        
        int[] dir = {0, 1, 0, -1, 0};
        
        if (b[x][y] != 'X') {
            b[x][y] = 'z';
            
            for (int k = 0; k < 4; k++) {
                dfs(b, x + dir[k], y + dir[k+1]);
            }
        }

    }
}


class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length <= 1) return;
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        boolean[][] setB = new boolean[board.length][board[0].length];
        
        // set two row boards
        for (int col = 0; col < board[0].length; col++) {
            setBoard(board, 0, col, visited, setB);
        }
        for (int col = 0; col < board[0].length; col++) {
            setBoard(board, board.length - 1, col, visited, setB);   
        }
        
        // set two col boards
        for (int row = 0; row < board.length; row++) {
            setBoard(board, row, 0, visited, setB);
        }
        for (int row = 0; row < board.length; row++) {
            setBoard(board, row, board[0].length - 1, visited, setB);
        }
        
        // find all remaining 
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !setB[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    public void setBoard(char[][] board, int x, int y, boolean[][] visited, boolean[][] setB) {
        if (x >= board.length || y >= board[0].length || x < 0 || y < 0 || visited[x][y]) {
            return;
        }
        
        visited[x][y] = true;
        
        if (board[x][y] == 'O') {
            setB[x][y] = true;
        } else {
            return;
        }
        
        setBoard(board, x + 1, y, visited, setB);
        setBoard(board, x - 1, y, visited, setB);
        setBoard(board, x, y + 1, visited, setB);
        setBoard(board, x, y - 1, visited, setB);
  
    }
}
