## [477. Surrounded Regions](https://www.lintcode.com/problem/surrounded-regions/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a 2D board containing `'X'` and `'O'`, capture all regions surrounded by `'X'`.

A region is captured by flipping all `'O''s` into `'X''s` in that surrounded region.

Example 1:

```
Input:
  X X X X
  X O O X
  X X O X
  X O X X
Output:
  X X X X
  X X X X
  X X X X
  X O X X
```

Example 2:

```
Input:
  X X X X
  X O O X
  X O O X
  X O X X
Output:
  X X X X
  X O O X
  X O O X
  X O X X
```

## Answer
### Method 1 - DFS - :rabbit: 2594ms (52.20%)

```java
public class Solution {
    /*
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
    public void surroundedRegions(char[][] board) {
        // write your code here
        if (board == null || board.length == 0) return;
        
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][board[0].length - 1] == 'O') {
                dfs(board, i, board[0].length - 1);
            }
        }
        
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O') {
                dfs(board, 0, i);
            } 
            if (board[board.length - 1][i] == 'O') {
                dfs(board, board.length - 1, i);
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'Y') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int x, int y) {
        if (x >= board.length || y >= board[0].length || x < 0 || y < 0 || board[x][y] != 'O') return;
        
        board[x][y] = 'Y';
        int[] dir = {0, 1, 0, -1, 0};
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i], ny = y + dir[i + 1];
            dfs(board, nx, ny);
        }
    }
}
```
