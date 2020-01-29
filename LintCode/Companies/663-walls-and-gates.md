## [663. Walls and Gates](https://www.lintcode.com/problem/walls-and-gates/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given a m x n 2D grid initialized with these three possible values.

- `-1` - A wall or an obstacle.
- `0` - A gate.
- `INF` - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a Gate, that room should remain filled with INF


Example1

```
Input:
[[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output:
[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]

Explanation:
the 2D grid is:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
the answer is:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
```

Example2

```
Input:
[[0,-1],[2147483647,2147483647]]
Output:
[[0,-1],[1,2]]
```

## Answer
### Method 1 - BFS - :turtle: 2113ms (13.20%)

```java
public class Solution {
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    public void wallsAndGates(int[][] rooms) {
       if (rooms == null || rooms.length == 0) return;
       
       Queue<int[]> q = new LinkedList();
       
       for (int i = 0; i < rooms.length; i++) {
           for (int j = 0; j < rooms[0].length; j++) {
               if (rooms[i][j] == 0) {
                   q.offer(new int[]{i, j, 0});
               }
           }
       }
       
       int[] dir = {0, 1, 0, -1, 0};
       while (!q.isEmpty()) {
           int size = q.size();
           
           for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1], cost = cur[2];
                for (int k = 0; k < 4; k++) {
                    int nx = x + dir[k], ny = y + dir[k + 1];
                    if (nx >= 0 && ny >= 0 && nx < rooms.length && ny < rooms[0].length && rooms[nx][ny] == Integer.MAX_VALUE) {
                        rooms[nx][ny] = 1 + cost;
                        q.offer(new int[]{nx, ny, cost + 1});
                    }
                }
               
           }
       }
    }
}
```
