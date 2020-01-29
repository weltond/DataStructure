## [598. Zombie in Matrix](https://www.lintcode.com/problem/zombie-in-matrix/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a 2D grid, each cell is either a wall 2, a zombie 1 or people 0 (the number zero, one, two).

Zombies can turn the nearest people(up/down/left/right) into zombies every day, but can not through wall. 

How long will it take to turn all people into zombies? 

Return -1 if can not turn all people into zombies.

Example 1:

```
Input:
[[0,1,2,0,0],
 [1,0,0,2,1],
 [0,1,0,0,0]]
Output:
2
```

Example 2:

```
Input:
[[0,0,0],
 [0,0,0],
 [0,0,1]]
Output:
4
```

## Answer
### Method 1 - BFS - :rabbit: 285ms (70.80%)

```java
public class Solution {
    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    public int zombie(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        Queue<int[]> q = new LinkedList();
        
        int cnt = grid.length * grid[0].length;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                    cnt--;
                } else if (grid[i][j] == 2) {
                    cnt--;
                }
            }
        }
        
        int[] dir = {0, -1, 0, 1, 0};
        int round = -1;

        while (!q.isEmpty()) {
            int size = q.size();
            round++;
            while (size-- > 0) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1];

                for (int k = 0; k < 4; k++) {
                    int nx = x + dir[k], ny = y + dir[k + 1];
                    if (nx < grid.length && ny < grid[0].length && nx >= 0 && ny >= 0 && grid[nx][ny] == 0) {
                        grid[nx][ny] = 1;
                        q.offer(new int[]{nx, ny});
                        cnt--;
                    }
                }
            }
        }
        
        return cnt == 0  ? round : -1;
    }
}
```
