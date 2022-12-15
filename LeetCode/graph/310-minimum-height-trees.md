## [310. Minimum Height Trees](https://leetcode.com/problems/minimum-height-trees/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

**Format**

The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

```
Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

Output: [1]
```

Example 2 :

```
Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

Output: [3, 4]
```

**Note:**

- According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
- The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

## Answer
- For the graph with odd no. of nodes, only the node at the middle of the graph when made the root, will give a minimum height tree.
- For the graph with even no. of nodes, both the middle nodes when made the root give a minimum height tree.

We use **Topological Sort**.

- We create an array called indegree which keeps the count of the no. of edges approaching each node in the tree.
- We start with the nodes having the minimum indegree (ie; indegree=1, i.e the leaf nodes) and we go on removing them i.e decrementing the indegree of nodes that're connected to them, until we reach the middle nodes.
- So we can have only one root or at max two roots for minimum height depending on tree structure as explained above.
- For the implementation, we are going to use a BFS like approach.
- To begin with, all leaf node are pushed into the queue, then they are removed from the tree, next new leaf node is pushed in the queue, this procedure keeps on going until we have only 1 or 2 node in our tree, which represent the result.

### Method 1 - BFS - 🐰 (25ms 51.48%)
#### Approach 2
```java
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);

        int[] indegree = new int[n];

        // initial graph that saves edges info
        List<Set<Integer>> graph = new ArrayList();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        // use indegree array to get identify leaf
        for (int i = 0; i < edges.length; i++) {
            indegree[edges[i][0]]++;
            indegree[edges[i][1]]++;
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        // Use queue to save leaf
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1) {
                q.offer(i);
                indegree[i]--;
            }
        }

        // answer is the middle node of the tree
        // count can only be 1 or 2 nodes.
        List<Integer> ans = new LinkedList<>();

        // queue stores leaves.
        while(!q.isEmpty()) {
            int size = q.size();
            ans.clear();    // remove previous leaves from the final answer.
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                ans.add(cur);
                for (int next : graph.get(cur)) {
                    indegree[next]--;   // remove edge connection

                    // put into queue if it is a leaf
                    if (indegree[next] == 1) {
                        q.offer(next);
                    }
                }
            }
        }

        return ans;
    }
}
```
```java
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            List<Integer> ans = new ArrayList();
            ans.add(0);
            return ans;
        }
        int[] degree = new int[n];
        Map<Integer, List<Integer>> map = new HashMap();
        for (int i = 0; i < edges.length; i++) {
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;

            map.computeIfAbsent(edges[i][0], o -> new ArrayList()).add(edges[i][1]);
            map.computeIfAbsent(edges[i][1], o -> new ArrayList()).add(edges[i][0]);
        }

        Queue<Integer> q = new LinkedList();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                q.offer(i);
            }
        }
        List<Integer> ans = new ArrayList();
        while (!q.isEmpty()) {
            int size = q.size();
            ans.clear();
            for (int i = 0; i < size; i++) {
                int val = q.poll();
                ans.add(val);
                degree[val]--;
                List<Integer> l =  map.get(val);
                for (int k : l) {
                    degree[k]--;
                    if (degree[k] == 1) {
                        q.offer(k);
                    }
                }
            }
            
        }
        return ans;
    }
}
```
                   
#### Approach 1
```java
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new LinkedList();
        if (edges == null || edges.length == 0 || n <= 2) {
            for (int i = 0; i < n; i++) {
                ans.add(i);
            }
            return ans;
        }
        
        List<Set<Integer>> graph = new LinkedList();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet());
        }
        int[] ind = new int[n];
        for (int i = 0; i < edges.length; i++) {
            ind[edges[i][0]]++;
            ind[edges[i][1]]++;
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        
        Queue<Integer> q = new LinkedList();
        boolean[] seen = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (ind[i] == 1)  {
                q.offer(i);
                seen[i] = true;
            }
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            cnt += size;
            for (int i = 0; i < size;i ++) {
                int o = q.poll();
                for (int k : graph.get(o)) {
                    if (!seen[k] && (--ind[k] == 1 || ind[k] == 0)) {
                        q.offer(k);
                        seen[k] = true;
                    }
                }
            }
            if (n - cnt <= 2) break;
        }
        
        int size = q.size();
        for (int i = 0; i < size; i++) {
            ans.add(q.poll());
        }
        
        return ans;
    }
    
}
```
