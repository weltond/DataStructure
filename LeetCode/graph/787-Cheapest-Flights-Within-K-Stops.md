## [787. Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/)

There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, 
your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
```
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:

The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
```
Example 2:
```
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:

The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
```

Note:

- The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
- The size of flights will be in range [0, n * (n - 1) / 2].
- The format of each flight will be (src, dst, price).
- The price of each flight will be in the range [1, 10000].
- k is in the range of [0, n - 1].
- There will not be any duplicated flights or self cycles.

## Answer
### Method 3 - Dijkstra - :rabbit: 12ms (61.72%)

```java
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> map = new HashMap();
        
        for (int[] arr : flights) {
            int start = arr[0];
            int end = arr[1];
            int cost = arr[2];
            map.computeIfAbsent(start, o -> new LinkedList<int[]>()).add(new int[]{end, cost});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{src, 0, k + 1});
                
        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int cur = top[0];
            int curCost = top[1];
            int stops = top[2];

            if (cur == dst) return curCost;    
            
            if (stops > 0) {
                if (!map.containsKey(cur)) continue;
                for (int[] nexts : map.get(cur)) {
                    int next = nexts[0];
                    int cost = nexts[1];
                    pq.offer(new int[]{next, curCost + cost, stops - 1});
                }
            }
        }
        
        return -1;
    }
    
}
```

### Method 2 - BFS - :rabbit: 8ms (80.89%)

```java
class Solution {
    int res;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        res = Integer.MAX_VALUE;
        
        Map<Integer, List<int[]>> map = new HashMap();
        
        for (int[] arr : flights) {
            int start = arr[0];
            int end = arr[1];
            int cost = arr[2];
            map.computeIfAbsent(start, o -> new LinkedList<int[]>()).add(new int[]{end, cost});
        }
        
        Queue<int[]> q = new LinkedList();
        q.offer(new int[]{src, 0});
        
        // Set<Integer> seen = new HashSet();
        
        while (!q.isEmpty()) {
            int size = q.size();
            k--;
            for (int i = 0; i < size; i++) {
                int[] array = q.poll();
                int cur = array[0];
                int curCost = array[1];
                
                // ==== SAME ====
                /**
                if (cur == dst) {
                    res = Math.min(res, curCost);
                } 
                */
                
                if (!map.containsKey(cur)) continue;
                
                //seen.add(cur);
                
                for (int[] a : map.get(cur)) {
                    
                    if (a[0] == dst) {
                        res = Math.min(res, a[1] + curCost);
                    }

                    if (a[1] + curCost > res) continue;

                    q.offer(new int[]{a[0], a[1] + curCost});
                }
            }
            
            if (k == -1) break;
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
}
```

### Method 1 - DFS - :turtle: 145ms (5.00%)

```java
class Solution {
    // ========= Method 1 : DFS ==========
    // 145ms (5.00%)
    int res = Integer.MAX_VALUE;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = buildMap(flights);    // <src, list of dst>
        Set<Integer> visited = new HashSet();

        dfs(map, src, dst, K, visited, 0);

        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    private void dfs(Map<Integer, List<int[]>> map, int src, int dst, int k, Set<Integer> visited, int total) {
        if (k < -1) return;
        if (src == dst) {
            
            res = Math.min(res, total);
            System.out.println(res);
            return;
        }
        
        if (!map.containsKey(src)) return;  // DON'T FORGET!
        
        for (int[] next : map.get(src)) {
            int nextDst = next[0], nextCost = next[1];

            if (visited.contains(nextDst) || nextCost + total >= res) continue;
            
            visited.add(nextDst);
            dfs(map, nextDst, dst, k - 1, visited, total + nextCost);
            visited.remove(nextDst);
        }
    }
    
    private Map<Integer, List<int[]>> buildMap(int[][] flights) {
        Map<Integer, List<int[]>> map = new HashMap();
        
        for (int i = 0; i < flights.length; i++) {
            map.computeIfAbsent(flights[i][0], o -> new ArrayList()).add(new int[]{flights[i][1], flights[i][2]});
        }
        
        return map;
    }
}


```
