## [789. The Maze III](https://www.lintcode.com/problem/the-maze-iii/description)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example 1:

```
Input:
[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
[4,3]
[0,1]

Output:
"lul"
```

Example 2:

```
Input:
[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
[0,0]
[1,1]
[2,2]
[3,3]
Output:
"impossible"
```

Notice
- There is only one ball and one hole in the maze.
- Both the ball and hole exist on an empty space, and they will not be at the same position initially.
- The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
- The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

## Answer
### LintCode Solution

```java
public class Solution {
    class Point {
        int x, y, dist;
		String path;
		public Point(int x, int y, int dist, String path) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.path = path;
		}
		public boolean lessThan(Point p){
			if(this.dist < p.dist){
				return true;
			} else if (this.dist == p.dist) {
				if(path.compareTo(p.path) < 0){
					return true;
				}
			}
			return false;
		}
	}
	int rows, cols;
    public String findShortestWay(int[][] maze, int[] start, int[] destination) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        String[] directStr = {"r","d","l","u"};
        rows = maze.length;
        cols = maze[0].length;
        Queue<Point> queue = new LinkedList<>();
        Point[][] cost = new Point[rows][cols];
        for(int r = 0; r < rows; r++){
        	for(int c =0; c < cols; c++){
        		cost[r][c] = new Point(r, c, Integer.MAX_VALUE, "");
        	}
        }
        cost[start[0]][start[1]].dist = 0;
        queue.offer(cost[start[0]][start[1]]);
        boolean updateQueue = false;
        while(!queue.isEmpty()){
        	Point cur = queue.poll();
        	for(int i = 0; i < 4; i++){
        		Point nb = new Point(cur.x, cur.y, cur.dist, cur.path + directStr[i]);
				while (nb.x + dx[i] >= 0 && nb.x + dx[i] < rows && nb.y + dy[i] >= 0 && nb.y + dy[i] < cols
						&& maze[nb.x + dx[i]][nb.y + dy[i]] == 0) {
					nb.x += dx[i];
					nb.y += dy[i];
					nb.dist++;
					if(nb.lessThan(cost[nb.x][nb.y])){
	        			cost[nb.x][nb.y].dist = nb.dist;
	        			cost[nb.x][nb.y].path = nb.path;
	        			queue.offer(nb);
	        		}
				}
        	}
        }
        return cost[destination[0]][destination[1]].dist == Integer.MAX_VALUE ? "impossible" : cost[destination[0]][destination[1]].path;
    }
}
```

### Method 1 - Dijkstra - :turtle: 484ms (14.00%)

```java
public class Solution {
    /**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // corner case
        if (maze == null || (ball[0] == hole[0] && ball[1] == hole[1])) return "impossible";
        
        PriorityQueue<Node> pq= new PriorityQueue<Node>((o1, o2) -> o1.dist - o2.dist);
        int m = maze.length, n = maze[0].length;
        Set<Node> set = new HashSet();
        
        Node start = new Node(ball[0], ball[1], 0, "");
        //set.add(start);
        pq.offer(start);
        
        boolean[][] visited = new boolean[m][n];
        
        
        int[] dir = {0, 1, 0, -1, 0};
        String[] str = {"r", "d", "l", "u"};
        
        List<String> list = new ArrayList();
        int diff = Integer.MAX_VALUE;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int x = cur.x, y = cur.y, dist = cur.dist;
            String last = cur.last;
            visited[x][y] = true;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i], ny = y + dir[i + 1], cnt = 0;
                while (nx >= 0 && ny >= 0 && nx < m && ny < n && maze[nx][ny] == 0) {
                    
                    if (nx == hole[0] && ny == hole[1]) {
                        if (diff > cnt + dist) {
                            diff = cnt + dist;
                            list.clear();
                            list.add(last + str[i]);
                        } else if (diff == cnt + dist) {
                            list.add(last + str[i]);
                        }
                    }
                    
                    nx += dir[i];
                    ny += dir[i + 1];
                    cnt++;
                }
                
                int cx = nx - dir[i], cy = ny - dir[i + 1];
                if (visited[cx][cy]) continue;
                
                //System.out.println(diff+";"+(dist+cnt) + "<-" + cx+","+cy + "==" + last + str[i]);
                // pruning
                if (diff < dist + cnt) continue;
                
                pq.offer(new Node(cx, cy, dist + cnt, last + str[i]));
            }
        }
        if (list.isEmpty())
            return "impossible";
            
        Collections.sort(list);
        
        return list.get(0);
    }
}

class Node {
    int x, y, dist;
    String last;
    public Node(int x, int y, int dist, String last) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.last = last;
    }
}
```
