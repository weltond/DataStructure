## [1514. Path with Maximum Probability](https://leetcode.com/problems/path-with-maximum-probability/description/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.

 

Example 1:


```
Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
```
Example 2:
```
Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000
```
Example 3:
```
Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
Output: 0.00000
Explanation: There is no path between 0 and 2.
``` 

Constraints:

- 2 <= n <= 10^4
- 0 <= start, end < n
- start != end
- 0 <= a, b < n
- a != b
- 0 <= succProb.length == edges.length <= 2*10^4
- 0 <= succProb[i] <= 1
- There is at most one edge between every two nodes.

## Answers
### Method 1 - Dijkstra - 30ms (93.07%) 🚀
Use Max Heap because require get max prob. So we want to process node with larger prob first (greedy).

```java
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // probTo[i] is the max prob from start to node i.
        double[] probTo = new double[n];
        Arrays.fill(probTo, -1);
        probTo[start] = 1;

        List<Pair>[] graph = buildGraph(edges, n, succProb);

        // max heap, the largest probFromStart is at the top 
        PriorityQueue<State> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b.probFromStart, a.probFromStart)
        );

        pq.offer(new State(start, 1));

        while(!pq.isEmpty()) {
            State cur = pq.poll();
            int curId = cur.id;
            double curProbFromStart = cur.probFromStart;

            // reach end
            if (curId == end) {
                return curProbFromStart;
            }

            // already has a path with larger prob to reach curId
            if (curProbFromStart < probTo[curId]) continue;

            // offer neighbors if possible
            for (Pair pair : graph[curId]) {
                int nextId = pair.neighbor;
                double prob = pair.succProb;

                double probToNext = probTo[curId] * prob;

                if (probToNext > probTo[nextId]) {
                    probTo[nextId] = probToNext;
                    pq.offer(new State(nextId, probToNext));
                }

            }
        }

        return (double) 0;
    }

    private List<Pair>[] buildGraph(int[][] edges, int n, double[] succProb) {
        List<Pair>[] graph = new List[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0], to = edges[i][1];
            double prob = succProb[i];

            // undirect graph
            graph[from].add(new Pair(to, prob));
            graph[to].add(new Pair(from, prob));
        }

        return graph;
    }
}

class State {
    int id;
    double probFromStart;

    State(int id, double probFromStart) {
        this.id = id;
        this.probFromStart = probFromStart;
    }
}

class Pair {
    int neighbor;
    double succProb;
    public Pair(int neighbor, double succProb) {
        this.neighbor = neighbor;
        this.succProb = succProb;
    }
}
```
