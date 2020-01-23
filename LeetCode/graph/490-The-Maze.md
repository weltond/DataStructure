## [490. The Maze]()

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1:

```
Input:
map = 
[
 [0,0,1,0,0],
 [0,0,0,0,0],
 [0,0,0,1,0],
 [1,1,0,1,1],
 [0,0,0,0,0]
]
start = [0,4]
end = [3,2]
Output:
false
```

Example 2:

```
Input:
map = 
[[0,0,1,0,0],
 [0,0,0,0,0],
 [0,0,0,1,0],
 [1,1,0,1,1],
 [0,0,0,0,0]
]
start = [0,4]
end = [4,4]
Output:
true
```

Notice

- There is only one ball and one destination in the maze.
- Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
- The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
- The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

## Answer
### Method 1 - DFS - :rocket: 369ms (95.80%)

- We need to use a **boolean array** instead of **changing the visited coordinate to a different value**. Because we want the next lvl to go to the first visited value as well so that the next lvl won't use wrong coordinate as start.

```java
public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
     boolean[][] visited;
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // write your code here
        visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start[0], start[1], destination);
    }
    
    private boolean dfs(int[][] maze, int x, int y, int[] end) {
        //if (x >= maze.length || y >= maze[0].length || x < 0 || y < 0 || maze[x][y] == 2) return false;
        if (visited[x][y]) return false;
        if (x == end[0] && y == end[1]) return true;
        
        int[] dir = {0, 1, 0, -1, 0};

        visited[x][y] = true;
        
        //maze[x][y] = 2;   // CANNOT use this! Think of WHY.
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i];
            int ny = y + dir[i + 1];
            int cx = nx, cy = ny;
            if (nx >= maze.length || ny >= maze[0].length || nx < 0 || ny < 0 || maze[nx][ny] != 0) continue;
            
            while (nx >= 0 && ny >= 0 && nx < maze.length && ny < maze[0].length && maze[nx][ny] == 0) {
                cx = nx;
                cy = ny;
                nx = nx + dir[i];
                ny = ny + dir[i + 1];
            }
            //System.out.println(cx+","+cy);
            if (dfs(maze, cx, cy, end)) return true;
        }
        return false;
    }
}
``` 

### Old Post

```java
// =============== Method 2: BFS ======================
public class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] dirs={{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Queue < int[] > queue = new LinkedList < > ();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] s = queue.remove();
            if (s[0] == destination[0] && s[1] == destination[1])
                return true;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
                if (!visited[x - dir[0]][y - dir[1]]) {
                    queue.add(new int[] {x - dir[0], y - dir[1]});
                    visited[x - dir[0]][y - dir[1]] = true;
                }
            }
        }
        return false;
    }
}

// =============== Method 1: DFS ======================
// TLE
// Approach 2
class Solution {
    private int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, visited, start, destination);
    }
    
    private boolean dfs(int[][] maze, boolean[][] visited, int[] c, int[] des) {
        if (visited[c[0]][c[1]]) return false;
        if (c[0] == des[0] && c[1] == des[1]) return true;
        
        visited[c[0]][c[1]] = true;
        boolean result = false;
        for (int[] d : dir) {
            int x = c[0] + d[0];
            int y = c[1] + d[1];
            while ( 0 <= x && x < maze.length && 0 <= y && y < maze[0].length && maze[x][y] == 0) {
                x += d[0];
                y += d[1];
            }
            result = result || dfs(maze, visited, new int[]{ x - d[0], y - d[1]}, des);
        }
        return result;
    }
}
// Approach 1
public class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }
    public boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        if (visited[start[0]][start[1]])
            return false;
        if (start[0] == destination[0] && start[1] == destination[1])
            return true;
        visited[start[0]][start[1]] = true;
        int r = start[1] + 1, l = start[1] - 1, u = start[0] - 1, d = start[0] + 1;
        while (r < maze[0].length && maze[start[0]][r] == 0) // right
            r++;
        if (dfs(maze, new int[] {start[0], r - 1}, destination, visited))
            return true;
        while (l >= 0 && maze[start[0]][l] == 0) //left
            l--;
        if (dfs(maze, new int[] {start[0], l + 1}, destination, visited))
            return true;
        while (u >= 0 && maze[u][start[1]] == 0) //up
            u--;
        if (dfs(maze, new int[] {u + 1, start[1]}, destination, visited))
            return true;
        while (d < maze.length && maze[d][start[1]] == 0) //down
            d++;
        if (dfs(maze, new int[] {d - 1, start[1]}, destination, visited))
            return true;
        return false;
    }
}
```
