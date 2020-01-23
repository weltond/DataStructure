## [505. The Maze II]()

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.


Example 1:

```
Input:  
	(rowStart, colStart) = (0,4)
	(rowDest, colDest)= (4,4)
	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0

	Output:  12
	
	Explanation:
	(0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(2,0)->(2,1)->(2,2)->(3,2)->(4,2)->(4,3)->(4,4)
```

Example 2:

```
Input:
	(rowStart, colStart) = (0,4)
	(rowDest, colDest)= (0,0)
	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0

	Output:  6
	
	Explanation:
	(0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(0,0)
```

Notice
- There is only one ball and one destination in the maze.
- Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
- The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
- The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

## Answer



### Method 1 - BFS - :turtle: 319ms (33.20%)

- O(mn*max(m,n)), O(mn)

```java
public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // write your code here
        int m = maze.length, n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList();
        q.offer(new int[]{start[0], start[1], 0});
        
        int res = Integer.MAX_VALUE;
        
        int[] dir = {0, 1, 0, -1, 0};
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1], step = cur[2];
                visited[x][y] = true;
                if (x == destination[0] && y == destination[1]) {
                    res = Math.min(res, step);
                }
                
                for (int i = 0; i < 4; i++) {
                    int nx = x + dir[i], ny = y + dir[i + 1], cnt = 1;
                
                    while (nx < m && ny < n && nx >= 0 && ny >= 0 && maze[nx][ny] == 0) {
                        nx += dir[i];
                        ny += dir[i + 1];
                        cnt++;
                    }
                    
                    int cx = nx - dir[i], cy = ny - dir[i + 1];
                    if (visited[cx][cy]) continue;

                    q.offer(new int[]{cx, cy, step + cnt - 1});
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
```

### Old post

```
// ==================== Method 3: Dijkstra ==========================
// Time = O(mnlog(mn)), space = O(mn)
public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dijkstra(maze, start, distance);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public void dijkstra(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
        PriorityQueue < int[] > queue = new PriorityQueue < > ((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{start[0],start[1],0});
        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            if(distance[s[0]][s[1]] < s[2])
                continue;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    queue.offer(new int[]{x - dir[0], y - dir[1], distance[x - dir[0]][y - dir[1]]});
                }
            }
        }
    }
}

// ==================== Method 2: BFS ====================
// Time O(mn*max(m,n)), space = O(mn)
public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
         int[][] dirs={{0, 1} ,{0, -1}, {-1, 0}, {1, 0}};
        Queue < int[] > queue = new LinkedList < > ();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] s = queue.remove();
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    queue.add(new int[] {x - dir[0], y - dir[1]});
                }
            }
        }
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }
}

// ==================== Method 1: DFS ====================
// Time = O(mn*max(n,m)), space = O(mn)
public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dfs(maze, start, distance);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public void dfs(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs={{0,1}, {0,-1}, {-1,0}, {1,0}};
        for (int[] dir: dirs) {
            int x = start[0] + dir[0];
            int y = start[1] + dir[1];
            int count = 0;
            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
                count++;
            }
            if (distance[start[0]][start[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
                dfs(maze, new int[]{x - dir[0],y - dir[1]}, distance);
            }
        }
    }
}
```
