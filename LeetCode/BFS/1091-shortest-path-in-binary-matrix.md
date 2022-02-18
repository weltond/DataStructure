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

## Leetcode Answers
### Method 3 - A*
- **Intuition**

For this problem, A* has a slightly worse time complexity, but in practice, performs quite well. It is a lot more complicated to code, and on problems such as this one, it doesn't offer a clear benefit. In saying that, it's worth knowing about as it allows you to show the depth of your ability while discussing the pros and cons of your BFS implementation.

In an interview, you need to be a bit careful, though. Going straight to A* could make you come across as somebody who tends to overengineer code. After all, there is nothing wrong with the BFS approach, and A* is a lot more complicated. A* is also extremely risky to code in an interview, as the implementation details can be quite challenging to get right.

So, feel free to briefly mention A* as a possibility in the initial "approaches" discussion, but unless the interviewer encourages you to pursue it, then you should point out that BFS is a far simpler approach, ideal for this particular problem. If you coded your BFS very quickly, then explaining and possibly implementing A* is a possible follow up question you could be asked.

Anyway, now that we've talked about how A* could fit into an interview for this problem let's get started on learning what it is! In approaches 1 and 2, we used a min-queue to keep track of cells we still needed to explore the neighbors of. The use of a min-queue ensured that we always explored the cells that we had traveled the smallest distance so far to get to. This meant that when we found the bottom-right cell, we knew we must have traveled the minimum possible distance to get to it.

You might have noticed that this approach somewhat lacked "common sense", though. For example, here is the state of a grid, partway through Approach 2.

The previous example, explored up to a depth of 7.

At this point, one of the two cells to explore seemed far more promising than the other. We want to modify the algorithm to prioritize promising paths over not so promising paths. To do this, we need to come up with a heuristic that, given a potential option, it measures how much "promise" that option has. Then we prioritize the options (exploring cells) with the highest "promise". In the previous approaches, we were actually doing this; our heuristic was simply distance traveled so far. But we can do better than that!

Instead, we're going to score them based on distance traveled so far plus our most optimistic estimate of how many more steps it would take to get to the bottom-right cell from there. For our example above, these estimates would be as follows. Indeed, this new heuristic favors the option that looks better.

Estimates of the two options.

Mathematically, this optimistic estimate for the remainder of the path is simply the maximum out of the number of rows and columns remaining (intuitively, we can always cover the smaller distance within the larger one by moving diagonally).

Here is an animation of attempting to modify one of our earlier approaches to use this new heuristic. As a warning, though, this exact approach ** doesn't work in all cases**, and we'll be making a tweak to it shortly. We're looking at it anyway as a starting point, though, as a lot of people are likely to initially come up with it, and so it really illustrates the level of caution needed when implementing A*!

Current
1 / 21
If you weren't warned beforehand, you might have believed this algorithm was fine. In fact, at the time of writing, it actually passed 64/84 test cases here on LeetCode! Here is an animation of a case it fails on, though. Try to spot exactly where it started going wrong.

Current
1 / 17
So, what's going on? We have made the wrong assumption that we can take the most promising cell and assume that the best possible distance to all of its neighbors is 1 more. But this is not always the case. In the above example, the most promising looking path went back up to a cell that could have been better accessed by initially taking a not so great looking path.

The algorithm did not take the best option.

To solve this, we simply need to remove the bad assumption; we shouldn't conclude that the first path we discover into a cell is the best one. Instead, we should keep track of all the options and then choose the best one when we get to it. Here is an animation of this improved algorithm.

Current
1 / 17
We'll now take a closer look at what exactly A* is doing to help you build some intuition around it. After that, we'll formally prove that this new heuristic is indeed guaranteed to give an optimal answer.

The estimates we wrote into cells represented the shortest possible path we could get from the top-left to the bottom-right if we were to go through that cell. This was guaranteed, as we had identified the shortest possible path to get to that cell from the top-left, and we knew that after that, the path could make at most 1 move right and 1 move down in a single step. It was simply impossible that it could be less than that. The actual best path that goes through this cell could be longer than the estimate, but not shorter.

Here is an image showing estimates for all the cells in a large grid. Note that A* doesn't necessarily need to find estimates for all the cells; however, this is what it would do if we allowed it to run until the priority queue was empty, as opposed to until it had found the bottom-right cell. The smaller numbers are the distances and are included to help you relate it back to BFS.

The estimate for each cell in a large grid.

And here is the same grid, except with arrows showing where each cell was entered from. Did you notice that the estimate in the "parent" cell is never more than the estimate in the "child" cell? Can you figure out why this is?

The above image but with arrows to show the direction of movement.

A "child" cell could never have a lower estimate than a "parent" cell. If it did, this would mean that the "parent" cell's estimate was not the lowest possible; we would have just found a way for it to be lower, which is contradictory. So when a "parent" cell puts options to get to "child" cells onto the priority queue, the estimates for these options are never less than the estimate for the parent. If the parent's estimate is x, then it will only put options with estimates of x or higher onto the priority queue.

The key implication of this is that if there is a way to get to a cell that assigns it an estimate of x, then we know that we won't inadvertently accept a higher estimate, only to later discard the x estimate like our first attempt at this algorithm might have done. Estimates of x can only be put onto the queue by cells with an estimate of x or lower. So by definition, we know that once we're taking off options with an estimate of x + 1, we have already exhausted all possible ways of getting estimates of x. Each estimate value "tier" has all of its members identified before and during the processing of that "tier".

No cell can have an estimate lower than that of the top-left cell.

Because of all this, the order that A* visits cells is in order of the best possible estimate that could be assigned to them. The estimate assigned to the top-left cell is used as the starting point; the algorithm then identifies all cells that share this same estimate. If it finds the bottom-right cell now, then it knows it couldn't have possibly done better than this. If it doesn't, though, it then essentially adds 1 to that estimate and identifies all cells that can be assigned that new estimate. And so forth, until it finds the bottom-right cell, which it can guarantee was reached with the smallest possible path length.

This is somewhat similar to BFS; remember that BFS assigns distances to cells that represent the shortest path from the top-left corner to that cell. Cells are explored in non-decreasing order of these distances. For A*, we are assigning the most optimistic possible estimate to each cell and then exploring cells in non-decreasing order of these estimates.

Proof of Correctness

Note that this section is fairly advanced and is an optional extra aimed at those who are confident with college-level mathematics. It pulls together the intuition we investigated in the previous section.

The key idea is that the estimates for any given path from the top-left to bottom-right cell are non-decreasing; recall that the estimates are for the shortest possible path from the top-left to the bottom-right if we were to go through that cell. In A* terminology, we say the estimate heuristic is admissible for having this property.

How do we know A* will find the correct solution? Recall that if we have finished the algorithm, this means we have assigned some estimate, x, to the bottom-right cell. This estimate also happens to be the length of the shortest path to the bottom-right cell (the "remaining distance" component of the estimate is 0). So, how do we know there is not a path that would give an estimate b where b < x?

We can prove that such a b could not exist by using contradiction.

Consider the assumption that a path with an estimate of b for the bottom-right cell exists. If this is the case, we can write the path that leads to this better estimate as a list of nodes best_path = [start, node1, node2,…, goal]. Remember that along this path, the estimate is non-decreasing. This means that every node in this path must have an estimate of less than x. We also know that this path shares at least one node with the path that leads to an estimate of x (at the very least, the start node was shared).

Now, remember that when we explore a node, we add its neighbors to be explored later. This means at least one of the nodes, node, in best_path is still on the priority queue and unexplored. But this is a contradiction, as we know node has to have an estimate less than x, and so should have been explored first.

From this, we can conclude A* will definitely find the shortest path.

- Algorithm

We'll keep track of potential options into cells that we haven't yet visited using a priority queue. To keep track of distances, we'll simply use the "distances on queue" method from Approach 2. This means that we'll be putting 4 values onto the queue for each option; row, column, distance so far, and estimate of the total distance.

Because we might find more than one option for each cell, we need to check whether or not the cell has already been visited within the outer loop.

- Complexity Analysis

Let N be the number of cells in the grid.

Time complexity : O(N \log N)O(NlogN).

The difference between this approach and the previous one is that adding and removing items from a priority queue is O(\log N)O(logN), as opposed to O(1)O(1). Given that we are adding/ removing O(N)O(N) items, this gives a time complexity of O(N \log N)O(NlogN).

Space complexity : O(N)O(N).

Same as the previous approaches.

Interestingly, there are ways to reduce the time complexity back down to O(N)O(N). The simplest is to recognize that there will be at most 3 unique estimates on the priority queue at any one time, and so to maintain 3 lists instead of a priority queue. Adding and removing from lists is O(1)O(1), bringing the total time complexity back down to O(N)O(N).

```java
class Solution {
    
    // Candidate represents a possible option for travelling to the cell
    // at (row, col).
    class Candidate {
        
        public int row;
        public int col;
        public int distanceSoFar;
        public int totalEstimate;
        
        public Candidate(int row, int col, int distanceSoFar, int totalEstimate) {
            this.row = row;
            this.col = col;
            this.distanceSoFar = distanceSoFar;
            this.totalEstimate = totalEstimate;
        }
    }
    
    
    private static final int[][] directions = 
        new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        // Firstly, we need to check that the start and target cells are open.
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }
        
        // Set up the A* search.
        Queue<Candidate> pq = new PriorityQueue<>((a,b) -> a.totalEstimate - b.totalEstimate);
        pq.add(new Candidate(0, 0, 1, estimate(0, 0, grid)));
        
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        // Carry out the A* search.
        while (!pq.isEmpty()) {
            
            Candidate best = pq.remove();
            
            // Is this for the target cell?
            if (best.row == grid.length - 1 && best.col == grid[0].length - 1) {
                return best.distanceSoFar;
            }
            
            // Have we already found the best path to this cell?
            if (visited[best.row][best.col]) {
                continue;
            }
            
            visited[best.row][best.col] = true;
            
            for (int[] neighbour : getNeighbours(best.row, best.col, grid)) {
                int neighbourRow = neighbour[0];
                int neighbourCol = neighbour[1];
                
                // This check isn't necessary for correctness, but it greatly
                // improves performance.
                if (visited[neighbourRow][neighbourCol]) {
                    continue;
                }
                
                // Otherwise, we need to create a Candidate object for the option
                // of going to neighbor through the current cell.
                int newDistance = best.distanceSoFar + 1;
                int totalEstimate = newDistance + estimate(neighbourRow, neighbourCol, grid);
                Candidate candidate = 
                    new Candidate(neighbourRow, neighbourCol, newDistance, totalEstimate);
                pq.add(candidate);
            }   
        }
        // The target was unreachable.
        return -1;  
    }
    
    private List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbours = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow < 0 || newCol < 0 || newRow >= grid.length 
                    || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0) {
                continue;    
            }
            neighbours.add(new int[]{newRow, newCol});
        }
        return neighbours; 
    }
    
    // Get the best case estimate of how much further it is to the bottom-right cell.
    private int estimate(int row, int col, int[][] grid) {
        int remainingRows = grid.length - row - 1;
        int remainingCols = grid[0].length - col - 1;
        return Math.max(remainingRows, remainingCols);
    }
}
```
