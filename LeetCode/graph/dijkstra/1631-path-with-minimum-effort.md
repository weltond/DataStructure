## [1631. Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/description/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

 

Example 1:
```
Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
```

Example 2:
```
Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
```
Example 3:

```
Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.
 ```

**Constraints**:

- rows == heights.length
- columns == heights[i].length
- 1 <= rows, columns <= 100
- 1 <= heights[i][j] <= 106
## Answer
### Method 1 - Dijkstra - 48ms (69.23%) :rabbit

[note](https://app.gitbook.com/s/1yBzuwxqO90h7a4SnmnK/advanced-algorithms/dijkstra#1631.-path-with-minimum-effort)

```java
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;

        int[][] efforts = new int[m][n];    // effort from (0,0)
        for (int i = 0; i < m; i++) {
            Arrays.fill(efforts[i], Integer.MAX_VALUE);
        }
        efforts[0][0] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>(
            (a, b) -> a.effortFromStart - b.effortFromStart
        );
        
        pq.offer(new State(0, 0, 0));   // offer start point (0,0)

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int curX = cur.x, curY = cur.y, currentEfforFromStart = cur.effortFromStart;
            
            // reach end point
            if (curX == m - 1 && curY == n - 1) {
                return currentEfforFromStart;
            }
            // skip if already find min effort from start.
            if (currentEfforFromStart > efforts[curX][curY]) continue;

            // put neighbor into pq if possible
            for (int[] next : adj(heights, curX, curY)) {
                int nextX = next[0], nextY = next[1];

                // calc effor from (curX, curY) to (nextX, nextY)
                // we need to get max of (max_weight_from_start_to_cur, edge_weight)
                int effortToNext = Math.max(
                    efforts[curX][curY],
                    Math.abs(heights[curX][curY] - heights[nextX][nextY])
                );

                if (effortToNext < efforts[nextX][nextY]) {
                    efforts[nextX][nextY] = effortToNext;
                    pq.offer(new State(nextX, nextY, effortToNext));
                }
            }
        }

        return -1;

    }

    private List<int[]> adj(int[][] h, int x, int y) {
        int m = h.length, n = h[0].length;
        int[] dir = {0, 1, 0, -1, 0};
        List<int[]> neighbors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i], ny = y + dir[i + 1];

            if (nx >= m || ny >= n || nx < 0 || ny < 0) continue;
            
            neighbors.add(new int[]{nx, ny});
        }

        return neighbors;
    }
}

class State {
    int x, y, effortFromStart;
    public State (int x, int y, int efforFromStart) {
        this.x = x;
        this.y = y;
        this.effortFromStart = efforFromStart;
    }
}
```
