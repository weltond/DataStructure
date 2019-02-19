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
