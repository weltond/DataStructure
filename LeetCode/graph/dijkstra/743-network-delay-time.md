## [743. Network Delay Time](https://leetcode.com/problems/network-delay-time/description/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

 

Example 1:

```
Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
```
Example 2:
```
Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
```
Example 3:
```
Input: times = [[1,2,1]], n = 2, k = 2
Output: -1
``` 

Constraints:

- 1 <= k <= n <= 100
- 1 <= times.length <= 6000
- times[i].length == 3
- 1 <= ui, vi <= n
- ui != vi
- 0 <= wi <= 100
- All the pairs (ui, vi) are unique. (i.e., no multiple edges.)

## Answers
### Method 1 - Dijkstra - 10ms (89.72%) 🐰
```java
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = buildGraph(times, n);
        
        // get min dist from k to every other node
        int[] distTo = dijkstra(graph, k);

        int res = 0;
        for (int i = 1; i < distTo.length; i++) {
            // one node is un-reachable
            if (distTo[i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, distTo[i]);
        }
        return res;
    }

    private int[] dijkstra(List<int[]>[] graph, int start) {
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;

        PriorityQueue<State> pq = new PriorityQueue<State>((a, b) -> a.distFromStart - b.distFromStart);

        pq.offer(new State(start, 0));

        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curId = curState.id, curDistFromStart = curState.distFromStart;

            if (curDistFromStart > distTo[curId]) continue;
            
            // curDistFromStart == distTo[curId] is guruanteed
            
            for (int[] next : graph[curId]) { 
                int nextId = next[0], weight = next[1];
                int distToNext = curDistFromStart /* or distTo[curId] */+ weight;

                if (distTo[nextId] > distToNext) {
                    distTo[nextId] = distToNext;

                    pq.offer(new State(nextId, distToNext));
                }
            }
        }

        return distTo;
    }

    private List<int[]>[] buildGraph(int[][] times, int n) {
        List<int[]>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge: times) {
            int from = edge[0], to = edge[1], weight = edge[2];
            // from -> List<(to, weight)>
            graph[from].add(new int[] {to,  weight});
        }

        return graph;
    }
}

class State {
    int id;
    int distFromStart;
    public State(int id, int distFromStart) {
        this.id = id;
        this.distFromStart = distFromStart;
    }
}
```
