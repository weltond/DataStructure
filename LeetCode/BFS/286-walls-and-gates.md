## [286. Walls and Gates](https://leetcode.com/problems/walls-and-gates/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given an `m x n` grid `rooms` initialized with these three possible values.

- `-1` A wall or an obstacle.
- `0` A gate.
- `INF` Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

 

Example 1:

<img width="454" alt="image" src="https://user-images.githubusercontent.com/9000286/155852733-e50cf2f8-b7c2-4946-ad7b-45c8d08137e3.png">

```
Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],
[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]

Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
```

Example 2:

```
Input: rooms = [[-1]]
Output: [[-1]]
``` 

Constraints:

- m == rooms.length
- n == rooms[i].length
- 1 <= m, n <= 250
- rooms[i][j] is -1, 0, or 231 - 1.

## Answers

### Method 1 - BFS - 11ms (65.28%)
This approach put all GATE into queue one time, so we don't have to do BFS for every GATE from start, and it is guruanteed that each EMPTY is shortest.

Difference with below [Wrong Answer]() is this approach enques **ALL gates** instead of one by one.

Time: O(m*n), Space: O(m*n)

```java
class Solution {
    int[] dir = {0, 1, 0, -1, 0};
    
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        
        Deque<int[]> q = new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // queue every gate
                if (rooms[i][j] == 0) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            step++;
            
            for (int k = 0; k < size; k++) {
                int[] loc = q.poll();
                int x = loc[0], y = loc[1];
                
                for (int s = 0; s < 4; s++) {
                    int nx = x + dir[s], ny = y + dir[s + 1];
                    
                    // skip out of range, or not Empty
                    if (nx < 0 || ny < 0 || nx >= rooms.length || ny >= rooms[0].length || rooms[nx][ny] != Integer.MAX_VALUE) continue;
                    
                    q.offer(new int[]{nx, ny});
                    
                    rooms[nx][ny] = step;
                }
            }
        }
    }
}
```

## Wrong Answers - BFS - TLE 61/62 passed
This approach start BFS for every GATE from the beginning, which may TLE for a large array with a lot GATE.

[[0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0,2147483647],[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,2147483647,0]]

```java
class Solution {
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        
        Deque<int[]> q = new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // bfs for every gate
                if (rooms[i][j] == 0) {
                    bfs(rooms, q, i, j);
                }
            }
        }
    }
    
    int[] dir = {0, 1, 0, -1, 0};
    
    private void bfs(int[][] rooms, Deque<int[]> q, int i, int j) {
        q.offer(new int[]{i, j});
        
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            step++;
            
            for (int k = 0; k < size; k++) {
                int[] loc = q.poll();
                int x = loc[0], y = loc[1];
                
                for (int s = 0; s < 4; s++) {
                    int nx = x + dir[s], ny = y + dir[s + 1];
                    
                    // skip out of range, or wall or gate
                    if (nx < 0 || ny < 0 || nx >= rooms.length || ny >= rooms[0].length || rooms[nx][ny] == 0 || rooms[nx][ny] == 1) continue;
                    
                    // skip if cur step is greater than existing
                    if (rooms[nx][ny] < step) continue;
                    
                    q.offer(new int[]{nx, ny});
                    
                    rooms[nx][ny] = Math.min(rooms[nx][ny], step);
                }
            }
        }
    }
    
    
}
```