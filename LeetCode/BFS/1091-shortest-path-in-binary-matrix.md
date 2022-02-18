## [1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

- All the visited cells of the path are 0.
- All the adjacent cells of the path are **8-directionally** connected (i.e., they are different and they share an edge or a corner).

The length of a clear path is the number of visited cells of this path.

 

Example 1:

<img width="239" alt="image" src="https://user-images.githubusercontent.com/9000286/154756467-c60f7195-2c6c-48f6-bdcc-54aadc220c09.png">

```
Input: grid = [[0,1],[1,0]]
Output: 2
```

Example 2:

<img width="234" alt="image" src="https://user-images.githubusercontent.com/9000286/154756494-f65fd2f0-8b17-44bb-a5f1-89bc309ad635.png">

```
Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
```

Example 3:

```
Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1
``` 

**Constraints**:

- n == grid.length
- n == grid[i].length
- 1 <= n <= 100
- grid[i][j] is 0 or 1

## Answers

Corner cases like :
- grid[0][0] == 1
- grid[m - 1][n - 1] == 1
- grid => [[0]]

### Method 1 - BFS - 30ms (46.81%)

```java
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1 || grid[m-1][n-1] == 1) return -1;
        
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        
        Deque<int[]> q = new LinkedList();
        
        q.offer(new int[]{0, 0});
        
        int[][] dir = {{0,1},{0,-1},{-1,0},{1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
        
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] pos = q.poll();
                
                int x = pos[0], y = pos[1];
                
                if (x == m - 1 && y == n - 1) {
                    return res + 1;
                }
                
                for (int k = 0; k < 8; k++) {
                    int nx = x + dir[k][0], ny = y + dir[k][1];
                    
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] == 1 || visited[nx][ny]) continue;
                    
                    visited[nx][ny] = true;
                    
                    q.offer(new int[]{nx, ny});
                }
            }
            
            res++;  // add at last because we check res when poll, rather than next
        }
        
        return -1;
    }
}
```
