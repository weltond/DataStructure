## [310. Minimum Height Trees](https://leetcode.com/problems/minimum-height-trees/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

*Format*
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

*Note:*

- According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
- The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

## Answer

### Method 1 - BFS - 

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
