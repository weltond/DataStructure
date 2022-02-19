## [317. Shortest Distance from All Buildings](https://leetcode.com/problems/shortest-distance-from-all-buildings/)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

You are given an m x n grid grid of values 0, 1, or 2, where:

- each 0 marks an empty land that you can pass by freely,
- each 1 marks a building that you cannot pass through, and
- each 2 marks an obstacle that you cannot pass through.

You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.

Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.

The total travel distance is the sum of the distances between the houses of the friends and the meeting point.

The distance is calculated using Manhattan Distance, where `distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|`.



Example 1:

<img width="207" alt="image" src="https://user-images.githubusercontent.com/9000286/152659200-bb83f874-ee8a-472c-8f38-2e8982b76231.png">

```
Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
Output: 7
Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
So return 7.
```

Example 2:

```
Input: grid = [[1,0]]
Output: 1
```

Example 3:

```
Input: grid = [[1]]
Output: -1
``` 

**Constraints**:

- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 50
- grid[i][j] is either 0, 1, or 2.
- There will be at least one building in the grid.

## Answers

The problem here is that we have some blocked cells. For example, in the below configuration the formula does not give the correct distance between P1 and P2. This is because there are obstacles between the two points.

Since obstacles preclude us from using the formula, we will instead perform a level-wise Breadth-First Search (BFS) for each cell, where each level is 1 distance further away from the starting cell (traversing 4-directionally). As we expand our Breadth-First Search, we will not visit any cell that is blocked or any cell that has already been visited.

### Method 3 - BFS from Hourses to Empty Land (Optimized)

<img width="320" alt="image" src="https://user-images.githubusercontent.com/9000286/152659169-5e49ae0b-b337-4d29-a4bd-01bf93854751.png">


Time: O(N2 * M2) 

Space: O(N * M)

```java
class Solution {
    public int shortestDistance(int[][] grid) {
        // Next four directions.
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Total Mtrix to store total distance sum for each empty cell.
        int[][] total = new int[rows][cols];

        int emptyLandValue = 0;
        int minDist = Integer.MAX_VALUE;

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {

                // Start a BFS from each house.
                if (grid[row][col] == 1) {
                    minDist = Integer.MAX_VALUE;

                    // Use a queue to perform a BFS, starting from the cell at (r, c).
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{ row, col });

                    int steps = 0;

                    while (!q.isEmpty()) {
                        steps++;

                        for (int level = q.size(); level > 0; --level) {
                            int[] curr = q.poll();

                            for (int[] dir : dirs) {
                                int nextRow = curr[0] + dir[0];
                                int nextCol = curr[1] + dir[1];

                                // For each cell with the value equal to empty land value
                                // add distance and decrement the cell value by 1.
                                if (nextRow >= 0 && nextRow < rows &&
                                    nextCol >= 0 && nextCol < cols &&
                                    grid[nextRow][nextCol] == emptyLandValue) {
                                    grid[nextRow][nextCol]--;
                                    total[nextRow][nextCol] += steps;

                                    q.offer(new int[]{ nextRow, nextCol });
                                    
                                    System.out.println(nextRow+","+nextCol+":"+total[nextRow][nextCol] + "---" + minDist);
                                    
                                    minDist = Math.min(minDist, total[nextRow][nextCol]);
                                }
                            }
                        }
                    }

                    // Decrement empty land value to be searched in next iteration.
                    emptyLandValue--;
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
```

- Output:
```
1,0:1---2147483647
0,1:1---1
2,0:2---1
1,1:2---1
2,1:3---1
1,2:3---1
1,3:4---1
2,3:5---1
0,3:5---1
1,4:5---1
2,4:6---1
1,4:6---2147483647
0,3:6---6
2,4:8---6
1,3:6---6
2,3:8---6
1,2:6---6
1,1:6---6
2,1:8---6
0,1:6---6
1,0:6---6
2,0:8---6
1,2:7---2147483647
2,3:9---7
2,1:9---7
1,3:8---7
1,1:8---7
2,4:10---7
2,0:10---7
0,3:9---7
1,4:9---7
0,1:9---7
1,0:9---7

```

### Method 2 - BFS from Houses to Empty Land

Time: O(N2 * M2) 

Space: O(N * M)

<img width="324" alt="image" src="https://user-images.githubusercontent.com/9000286/152659134-a119ada0-bac5-47aa-93bc-e8d2bd556976.png">

```java
class Solution {
    private void bfs(int[][] grid, int[][][] distances, int row, int col) {
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        int rows = grid.length;
        int cols = grid[0].length;

        // Use a queue to do a bfs, starting from each cell located at (row, col).
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ row, col });

        // Keep track of visited cells.
        boolean[][] vis = new boolean[rows][cols];
        vis[row][col] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                int[] curr = q.poll();
                row = curr[0];
                col = curr[1];

                // If we reached an empty cell, then add the distance
                // and increment the count of houses reached at this cell.
                if (grid[row][col] == 0) {
                    distances[row][col][0] += steps;
                    distances[row][col][1] += 1;
                }

                // Traverse the next cells which is not a blockage.
                for (int[] dir : dirs) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols) {
                        if (!vis[nextRow][nextCol] && grid[nextRow][nextCol] == 0) {
                            vis[nextRow][nextCol] = true;
                            q.offer(new int[]{ nextRow, nextCol });
                        }
                    }
                }
            }

            // After traversing one level cells, increment the steps by 1.
            steps++;
        }
    }

    public int shortestDistance(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        int rows = grid.length;
        int cols = grid[0].length;
        int totalHouses = 0;

        // Store { total_dist, houses_count } for each cell.
        int[][][] distances = new int[rows][cols][2];

        // Count houses and start bfs from each house.
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (grid[row][col] == 1) {
                    totalHouses++;
                    bfs(grid, distances, row, col);
                }
            }
        }

        // Check all empty lands with houses count equal to total houses and find the min ans.
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (distances[row][col][1] == totalHouses) {
                    minDistance = Math.min(minDistance, distances[row][col][0]);
                }
            }
        }

        // If we haven't found a valid cell return -1.
        if (minDistance == Integer.MAX_VALUE) {
            return -1;
        }
        return minDistance;
    }
};
```

### Method 1 - BFS from Empty Land to All Houses
Time: O(N2 * M2) 

Space: O(N * M)

```java
class Solution {
    private int bfs(int[][] grid, int row, int col, int totalHouses) {
        // Next four directions.
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        int rows = grid.length;
        int cols = grid[0].length;
        int distanceSum = 0;
        int housesReached = 0;
        
        // Queue to do a bfs, starting from (row, col) cell.
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ row, col });
        
        // Keep track of visited cells.
        boolean[][] vis = new boolean[rows][cols];
        vis[row][col] = true;

        int steps = 0;
        while (!q.isEmpty() && housesReached != totalHouses) {
            for (int i = q.size(); i > 0; --i) {
                int[] curr = q.poll();
                row = curr[0];
                col = curr[1];
                
                // If this cell is a house, then add the distance from source to this cell
                // and we go past from this cell.
                if (grid[row][col] == 1) {
                    distanceSum += steps;
                    housesReached++;
                    continue;
                }
                
                // This cell was empty cell, hence traverse the next cells which is not a blockage.
                for (int[] dir : dirs) {
                    int nextRow = row + dir[0]; 
                    int nextCol = col + dir[1];
                    if (nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols) {
                        if (!vis[nextRow][nextCol] && grid[nextRow][nextCol] != 2) {
                            vis[nextRow][nextCol] = true;
                            q.offer(new int[]{ nextRow, nextCol });
                        }
                    }
                }
            }
            
            // After traversing one level of cells, increment the steps by 1 to reach to next level.
            steps++;
        }

        // If we did not reach all houses, then any cell visited also cannot reach all houses.
        // Set all cells visted to 2 so we do not check them again and return MAX_VALUE.
        if (housesReached != totalHouses) {
            for (row = 0; row < rows; row++) {
                for (col = 0; col < cols; col++) {
                    if (grid[row][col] == 0 && vis[row][col]) {
                        grid[row][col] = 2;
                    }
                }
            }
            return Integer.MAX_VALUE;
        }
        
        // If we have reached all houses then return the total distance calculated.
        return distanceSum;
    }
    
    public int shortestDistance(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        int rows = grid.length; 
        int cols = grid[0].length;
        int totalHouses = 0;
        
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (grid[row][col] == 1) {
                    totalHouses++;
                }
            }
        }
        
        // Find the min distance sum for each empty cell.
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (grid[row][col] == 0) {
                    minDistance = Math.min(minDistance, bfs(grid, row, col, totalHouses));
                }
            }
        }
        
        // If it is impossible to reach all houses from any empty cell, then return -1.
        if (minDistance == Integer.MAX_VALUE) {
            return -1;
        }
        
        return minDistance;
    }
};
```

## Wrong Solution

Failed case : `[[1,0,1,0,1]]`. Output: 2, Expected: -1

```java
class Solution {
    int[] dir = {0, 1, 0, -1, 0};
    int res = Integer.MIN_VALUE;
    
    public int shortestDistance(int[][] grid) {
        
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (!bfs(grid, i, j)) return -1;    // building cannot be reached
                }
            }
        }
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0) {
                    res = Math.max(res, grid[i][j]);
                }
            }
        }
        return res == Integer.MIN_VALUE ? -1 : Math.abs(res);
    }
    
    private boolean bfs(int[][] grid, int x, int y) {
        Deque<int[]> q = new LinkedList();
        
        q.offer(new int[]{x, y});
        
        int step = 0;
        
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        boolean buildingCanBeReached = false;
        
        visited[x][y] = true;
        
        while (!q.isEmpty()) {
            step++;
            
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int cx = cur[0], cy = cur[1];
                
                for (int k = 0; k < 4; k++) {
                    int nx = cx + dir[k], ny = cy + dir[k + 1];
                    
                    if (nx < 0 || ny < 0 || nx >= grid.length || ny >= grid[0].length || visited[nx][ny] || grid[nx][ny] > 0) continue;
                    
                    grid[nx][ny] += -step;
                    visited[nx][ny] = true;
                    buildingCanBeReached = true;
                    
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        
        return buildingCanBeReached;
    }
    
    
}
```
