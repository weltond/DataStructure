## [1584. Min cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given an array points representing integer coordinates of some points on a 2D-plane, where `points[i] = [xi, yi]`.

The cost of connecting two points `[xi, yi]` and `[xj, yj]` is the manhattan distance between them: `|xi - xj| + |yi - yj|`, where `|val|` denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

 

Example 1:

<img width="239" alt="image" src="https://user-images.githubusercontent.com/9000286/206604228-1b775bad-4788-444e-a1b8-29be9bb9ef13.png">


```
Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation: 

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
```

Example 2:

```
Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18
``` 

**Constraints**:

- 1 <= points.length <= 1000
- -106 <= xi, yi <= 106
- All pairs (xi, yi) are distinct.

## Answer
### Overview
After reading the problem description, we can say we need to connect some points (the connection between any two points will be an edge whose weight is the Manhattan distance between those points) such that all points become connected and the sum of the weights of the chosen edges is minimized.

We can say this problem is a variant of graph problems. More precisely, it is a Minimum Spanning Tree (MST) problem, where we are given nodes (points) and weighted edges (distance between two points) and we have to form an MST using them.


- How do we know that this is an MST problem?

> Given a connected, weighted, and undirected graph, a minimum spanning tree is a subset of edges that connect all vertices while the total weights of these edges are minimum among all possible subsets.

We can draw some similarities between the above definition and the problem here. We can consider our input as a complete graph (each point has an edge to every other point), and in this complete graph, we have to connect each point with minimum cost (sum of edge weights). Thus, we can rephrase the problem as "Find the Minimum Spanning Tree for the given set of points."

![image](https://user-images.githubusercontent.com/9000286/206604593-971ab905-6df5-4d5d-8537-87aa183e921e.png)

Concerning the MST problem, there exist several classic algorithms. In particular, we will demonstrate two of them, namely Kruskal's algorithm and Prim's algorithm, which are the most popular ones and feasible to implement during an interview.

> Note: If you are not familiar with either of the above algorithms, we highly recommend you to visit the Graph Explore Card and watch the video explanations to gain a general understanding of these algorithms as these are standard graph algorithms which are used frequently in MST problems.
We will focus on their implementation in the given problem rather than going into detail that how these algorithms work.

### Method 1 - Kruskal

Kruskal's algorithm is a greedy algorithm for building a minimum spanning tree in a weighted and undirected graph.

> The algorithm operates by identifying the lowest-weighted edge that is not part of the MST. Then, if the nodes that belong to the edge are not connected, the edge is added to the MST. This process is repeated until all nodes are connected. Since we do not add an edge when its nodes are already connected, no cycles are formed.

Time complexity: O(N^2 * log(N))

<img width="663" alt="image" src="https://user-images.githubusercontent.com/9000286/206605133-12c595e6-9801-4f39-8976-9a4cbf034f3c.png">

Space: O(N^2)

```java
class UnionFind {
    public int[] group;
    public int[] rank;

    public UnionFind(int size) {
        group = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; ++i) {
            group[i] = i;
        }
    }

    public int find(int node) {
        if (group[node] != node) {
            group[node] = find(group[node]);
        }
        return group[node];
    }

    public boolean union(int node1, int node2) {
        int group1 = find(node1);
        int group2 = find(node2);
        
        // node1 and node2 already belong to same group.
        if (group1 == group2) {
            return false;
        }

        if (rank[group1] > rank[group2]) {
            group[group2] = group1;
        } else if (rank[group1] < rank[group2]) {
            group[group1] = group2;
        } else {
            group[group1] = group2;
            rank[group2] += 1;
        }

        return true;
    }
}

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        ArrayList<int[]> allEdges = new ArrayList<>();
        
        // Storing all edges of our complete graph.
        for (int currNext = 0; currNext < n; ++currNext) {
            for (int nextNext = currNext + 1; nextNext < n; ++nextNext) {
                int weight = Math.abs(points[currNext][0] - points[nextNext][0]) + 
                             Math.abs(points[currNext][1] - points[nextNext][1]);
                
                int[] currEdge = {weight, currNext, nextNext};
                allEdges.add(currEdge);
            }
        }
        
        // Sort all edges in increasing order.
        Collections.sort(allEdges, (a, b) -> Integer.compare(a[0], b[0]));   
        
        UnionFind uf = new UnionFind(n);
        int mstCost = 0;
        int edgesUsed = 0;
        
        for (int i = 0; i < allEdges.size() && edgesUsed < n - 1; ++i) {
            int node1 = allEdges.get(i)[1];
            int node2 = allEdges.get(i)[2];
            int weight = allEdges.get(i)[0];
            
            if (uf.union(node1, node2)) {
                mstCost += weight;
                edgesUsed++;
            }
        }
        
        return mstCost;
    }
}
```

### Method 2 - Prim

Prim's algorithm is also a greedy algorithm for building a minimum spanning tree in a weighted and undirected graph.

> In this algorithm, we include an arbitrary node in the MST and keep on adding the lowest-weighted edges of the nodes present in the MST until all nodes are included in the MST and no cycles are formed.

Just like the previous approach, we will use the input array indices to represent the nodes.

In this algorithm, we can pick any node to start with. Then we will choose the lowest-weighted edge that connects a node present in the MST to a node not present in the MST. We could keep all of the edges in an array and then sort them. But then, for each new node that we add to the MST, we would have to add the new node's edges to the array and sort the array again. This would be a costly operation when done repeatedly.

A more efficient way to track which edges are available and which of these edges has the lowest weight is to use a min-heap data structure. A min-heap is a tree-like data structure that always stores the minimum valued element (edge weight here) at the root and where insertion and removal of elements (edges) take logarithmic time.

Now, we know how to greedily pick the lowest-weighted edge, but how can we check if including an edge will form a cycle in the MST?
Consider the example below. We can say, for the node 00 of the MST there exists an edge that is greedily best to choose and it connects to node 44. If node 44 is already present in the MST it means there already exists a path from node 00 to 44 and hence adding this edge would form a loop.

<img width="668" alt="image" src="https://user-images.githubusercontent.com/9000286/206605289-59646866-58ae-473e-af20-9326c322442f.png">


```java
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        
        // Min-heap to store minimum weight edge at top.
        PriorityQueue<Pair<Integer, Integer>> heap = new PriorityQueue<>((a, b) -> (a.getKey() - b.getKey()));;
        
        // Track nodes which are included in MST.
        boolean[] inMST = new boolean[n];
        
        heap.add(new Pair(0, 0));
        int mstCost = 0;
        int edgesUsed = 0;
        
        while (edgesUsed < n) {
            Pair<Integer, Integer> topElement = heap.poll();
            
            int weight = topElement.getKey();
            int currNode = topElement.getValue();
            
            // If node was already included in MST we will discard this edge.
            if (inMST[currNode]) {
                continue;
            }
            
            inMST[currNode] = true;
            mstCost += weight;
            edgesUsed++;
            
            for (int nextNode = 0; nextNode < n; ++nextNode) {
                // If next node is not in MST, then edge from curr node
                // to next node can be pushed in the priority queue.
                if (!inMST[nextNode]) {
                    int nextWeight = Math.abs(points[currNode][0] - points[nextNode][0]) + 
                                     Math.abs(points[currNode][1] - points[nextNode][1]);
        
                    heap.add(new Pair(nextWeight, nextNode));
                }
            }
        }
        
        return mstCost;
    }
}
```

### Method 3 - Prim optimized

Although the min-heap method is often used to implement Prim's algorithm (as it's fairly easy to understand), due to its use of a heap to store the edges, its time complexity is suboptimal. Thus, we will present a more efficient way of implementing Prim's algorithm, which eliminates the use of min-heap to find the next lowest-weighted edge.

In this approach, we use one minDistminDist array, where minDist[i]minDist[i] stores the weight of the smallest weighted edge to reach the i^{th}i 
th
  node from any node in the current tree. We will iterate over the minDistminDist array and greedily pick the node that is not in the MST and has the smallest edge weight. We will add this node to the MST, and for all of its neighbors, we will try to update the value in minDistminDist.
We will repeat this process until all nodes are part of the MST.

Initially, we can start with any node, say node 00. Thus we markminDist[0] = 0minDist[0]=0, and for the remaining nodes, the min distance to reach them is \infty∞.
Just like in the previous approach, we assume a 00 weighted temporary edge is used to reach the first node.

Thus in this method, we will use this new way of selecting the min weight edges (instead of using a heap). Just like the previous method, we will use the inMSTinMST array to determine if adding the current edge will result in a cycle, and we can stop as soon as nn edges are included in MST (including our imaginary zero-weight edge to node 0).

<img width="640" alt="image" src="https://user-images.githubusercontent.com/9000286/206605380-8656d04b-4c75-4a1b-b951-01c51cd144f6.png">

```java
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int mstCost = 0;
        int edgesUsed = 0;
        
        // Track nodes which are visited.
        boolean[] inMST = new boolean[n];
        
        int[] minDist = new int[n];
        minDist[0] = 0;
        
        for (int i = 1; i < n; ++i) {
            minDist[i] = Integer.MAX_VALUE;
        }
        
        while (edgesUsed < n) {
            int currMinEdge = Integer.MAX_VALUE;
            int currNode = -1;
            
            // Pick least weight node which is not in MST.
            for (int node = 0; node < n; ++node) {
                if (!inMST[node] && currMinEdge > minDist[node]) {
                    currMinEdge = minDist[node];
                    currNode = node;
                }
            }
            
            mstCost += currMinEdge;
            edgesUsed++;
            inMST[currNode] = true;
            
            // Update adjacent nodes of current node.
            for (int nextNode = 0; nextNode < n; ++nextNode) {
                int weight = Math.abs(points[currNode][0] - points[nextNode][0]) + 
                             Math.abs(points[currNode][1] - points[nextNode][1]);
                
                if (!inMST[nextNode] && minDist[nextNode] > weight) {
                    minDist[nextNode] = weight;
                }
            }
        }
        
        return mstCost;
    }
}
```

### Method 4 - Union Find - 🐢: 707ms (35.49%)

```java
class Solution {
    public int minCostConnectPoints(int[][] points) {
        List<int[]> graph = buildGraph(points);
        // ascending sort by weight
        Collections.sort(graph, (a, b) -> a[2] - b[2]);
        
        UF uf = new UF(points.length);
        int mst = 0;

        for (int[] edge : graph) {
            int u = edge[0], v = edge[1], weight = edge[2];

            // skip if u and v are already connected
            if (uf.connected(u, v)) {
                continue;
            }

            mst += weight;
            uf.union(u, v);
        }

        return mst;
    }

    private List<int[]> buildGraph(int[][] points) {
        // weight between each point
        List<int[]> graph = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            int ix = points[i][0], iy = points[i][1];
            for (int j = i + 1; j < points.length; j++) {
                int jx = points[j][0], jy = points[j][1];

                // use (i,j) index as point
                graph.add(new int[] {
                    i,  // point i
                    j,  // point j
                    Math.abs(ix - jx) + Math.abs(iy - jy)   // weight of edge from i to j
                });
            }
        }

        return graph;
    }
}

class UF {
    int[] parent;
    int[] size;
    int count;

    public UF(int n) {
        count = n;
        size = new int[n];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }

        return x;
    }

    public void union(int p, int q) {
        int rp = find(p), rq = find(q);
        if (rp == rq) return;

        if (size[rp] > size[rq]) {
            parent[rq] = rp;
            size[rp] += size[rq];
        } else {
            parent[rp] = rq;
            size[rq] += size[rp];
        }
        
        count--;
    }

    public boolean connected(int u, int v) {
        return find(u) == find(v);
    }
}
```
