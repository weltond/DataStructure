/**
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/

// DFS backtracking

// Approach 2: 4ms (100%)
class Solution {
    
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) return false;
        
        char c = word.charAt(0);
        
        boolean flag = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == c) {
                    flag = flag || dfs(board, i, j, word, 0);
                }
            }
        }
        
        return flag;
    }
    
    private boolean dfs(char[][] board, int x, int y, String word, int lvl) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == '1') return false;
        if (word.charAt(lvl) != board[x][y]) return false;

        if (lvl + 1 == word.length()) {
            return true;
        }
        
        char tmp = board[x][y];
        board[x][y] = '1';
        
        int[] dir = {-1, 0, 1, 0, -1};
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i];
            int ny = y + dir[i + 1];
            
            if (dfs(board, nx, ny, word, lvl + 1)) return true;
        }
        
        board[x][y] = tmp;
        
        return false;
    }
}

// Approach 1 : 9ms
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null || board.length == 0 || word.length() == 0) {
            return false;
        }
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (word.charAt(0) == board[i][j]) {
                    if (backtracking(board, word, 0, i, j, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean backtracking(char[][] board, String word, int level, int row, int col, boolean[][] visited) {
        if (level == word.length()) {
            return true;
        }
        
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]) {
            return false;
        }
        
        visited[row][col] = true;

        
        int[] r = new int[]{-1, 0, 1, 0};
        int[] c = new int[]{0, 1, 0, -1};
        
        if (word.charAt(level) == board[row][col]) {
            for (int k = 0; k < 4; k++) {
                if (backtracking(board, word, level + 1, row + r[k], col + c[k], visited)) {
                    return true;
                }
            } 
        }
        visited[row][col] = false;
        
        return false;
    }
    
}
