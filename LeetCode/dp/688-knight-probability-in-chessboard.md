## [688. Knight Probability in Chessboard](https://leetcode.com/problems/knight-probability-in-chessboard/)

On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.

 

Example:

```
Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
```

Note:

- N will be between 1 and 25.
- K will be between 0 and 100.
- The knight always initially starts on the board.

## Answer
### Method 1 - DFS + memo - :rocket: 4ms (83.09%)

```java
class Solution {
    int[][] dir = {{-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}};
    double[][][] memo;
    public double knightProbability(int N, int K, int r, int c) {
        memo = new double[N][N][K + 1];
        return dfs(N, K, r, c);
    }
    
    private double dfs(int n, int k, int x, int y) {
        if (x >= n || y >= n || x < 0 || y < 0) return 0.0;
        if (k == 0) return 1.0;
        if (memo[x][y][k] != 0) return memo[x][y][k];
        
        for (int i = 0; i < 8; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            
            memo[x][y][k] += dfs(n, k - 1, nx, ny) / 8;
        }
        
        return memo[x][y][k];
    }
}
```
