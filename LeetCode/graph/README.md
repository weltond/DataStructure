## Depth First Search (DFS)
### Idea
DFS is an algorithm for traversing a graph

### [Applications](http://ww3.algorithmdesign.net/handouts/DFS.pdf)
- For an unweighted graph, DFS traversal of the graph produces the minimum spanning tree and all pair shortest path tree
- **Detecting Cycle in a Graph**
  - A graph has cycle if and only if we see a back edge during DFS. So we can run DFS for the graph and check for back edges
- **Path Finding**
  - We can specialize the DFS algorithm to find a path between two given vertices u and z.
    - Call DFS(G, u) with u as the start vertex
    - Use a stack S to keep track of the path between the start vertex and the current vertex
    - As soon as destination vertex z is encountered, return the path as the contents of the stack.
- [Topological Sorting](#topological-Sort)
  - Mainly used for scheduling jobs from the given dependencies among jobs. 
- **Bipartite**
  - We can augment either BFS or DFS when we first discover a new vertex, color it opposited its parents, and for each other edge, check it doesn't link
  two vertices of the same color.
- **Finding Strongly Connected Components of a Graph**
  - A directed graph is called strongly connected if there is a path from each vertex in the graph to every other vertex.
- **Solving Puzzles with only one solution**
  - **Maze**. DFS can be adapted to find all solutions to a maze by only including nodes on the current path in the visited set.

### Permutation P(l, n)
```
P(nums, d, n, used, curr, ans):
    if d == n:
        ans.append(curr)
        return
    
    for i = 0 to len(nums):
        if used[i]: continue
        used[i] = true
        curr.add(nums[i])
        P(nums, d + 1, n, curr, ans)
        curr.remove()
        used[i] = false
```

```
P([1,2,3], 0, 3, [false] * 3, [], ans)
[[1,2,3], [1,3,2], 
 [2,1,3], [2,3,1],
 [3,1,2], [3,2,1]]

P([1,2,3], 0, 2, [false] * 3, [], ans)
[[1,2],[1,3],[2,1],[2,3],[3,1],[3,2]]
```
### Combination C(l, n)
```
C(nums, d, n, s, curr, ans):
    if d == n:
        ans.append(curr)
        return
    
    for i = s to len(nums):
        curr.add(nums[i])
        C(nums, d + 1, n, i + 1, curr, ans)
        curr.remove()
```

```
C([1,2,3], 0, 3, [], ans)
[[1,2,3]]

C([1,2,3], 0, 2, [], ans)
[[1,2],[1,3],[2,3]]
```

## Breadth First Search (BFS)
### Applications
- **Shortest Path and Minimum Spanning Tree for unweighted graph**
  - In an unweighted graph, the shortest path is the path with least number of edges. With BFS, we always reach a vertex from given
  source using the minimum number of edges. Also, in case of unweighted graphs, any spanning tree is Minimum Spanning Tree and we can
  use either DFS or BFS for finding a spanning tree
- **Peer to Peer Networks**
  - In Peer to Peer Network like BitTorrent, BFS is used to find all neighbor nodes.
- **Crawler in Search Engines**
  - Crawlers build index using BFS. The idea is to start from source page and follow all links from source and keep doing same. DFS can 
  also be used for crawlers, but the advantage with BFS is, depth or levels of the build tree can be limited.
- **Social Network Websites**
  - In social networks, we can find people within a given distance 'k' from a person using BFS till 'k' levels.
- **GPS Navigation Systems**
  - BFS is used to find all neighboring locations
- **Garbage Collection**
  - BFS is used in copying garbage collection using [Cheney's algorithm](https://lambda.uta.edu/cse5317/notes/node48.html). 
  BFS is preffered over DFS becuase of better locality of reference.
- **Cycle detection in undirected graph**
  - In undirected graphs, either BFS or DFS can be used to detect cycle.
  - In directed graph, **only DFS can be used**
- **Ford-Fulkerson algo**
  - We can either use BFS or DFS to find the maximm flow. BFS is preferred as it reduces worst case time complextiy to O(VE^2)
- **Is Bipartite**
  - Either use BFS or DFS
- **Path Finding**
  - Either use BFS or DFS to find if there is a path between two vertices
- **Finding all nodes within one connected component**
  - Either use BFS or DFS to find all nodes reachable from a given node
  
  
## Topological Sort
### Idea
For a Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge(u, v), vertex u comes before v in the ordering.

### Applications
- **Build Systems/Projects** (like Eclipse or IntelliJ)
  - If your project has various libraries that are dependent on each other, then IDE can do a topological sort to know which library should be built first.
- **Advanced-Packaging Tool** (apt-get)
- **Task Scheduling**
- **Pre-requisite problems**

