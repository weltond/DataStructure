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
### Method 1 - Union Find - 🐢: 707ms (35.49%)

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

### Method 2 - Graph - :turtle: 4ms (12.43%)

```java
class Solution {
    // <dividend, <divisor, ratio>>
    // <divisor, <dividend, 1 / ratio>>
    Map<String, Map<String, Double>> map = new HashMap();
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int idx = 0;
        for(List<String> equation : equations) {
            String dividend = equation.get(0), divisor = equation.get(1);
            double ratio = values[idx++];
            
            // what if same equation exists?
            map.computeIfAbsent(dividend, o -> new HashMap()).put(divisor, ratio);
            
            map.computeIfAbsent(divisor, o -> new HashMap()).put(dividend, 1.0 / ratio);
        }
        System.out.println(map);
        idx = 0;
        double[] res = new double[queries.size()];
        for (List<String> query : queries) {
            String dividend = query.get(0), divisor = query.get(1);
            if (!map.containsKey(dividend)) {
                res[idx++] = -1.0;
                continue;
            }
            
            res[idx++] = dfs(map, dividend, divisor, new HashSet());
            
        }
        
        return res;
    }
    
    private double dfs(Map<String, Map<String, Double>> map, String dividend, String divisor, Set<String> visited) {
        if (dividend.equals(divisor)) return 1.0;
        
        if (!map.containsKey(dividend)) return -1.0;
        if (visited.contains(dividend)) return -1.0;
        
        visited.add(dividend);
        
        Map<String, Double> ratioMap = map.get(dividend);
        for (String div : ratioMap.keySet()) {
            Double ratio = ratioMap.get(div);

            Double rec = dfs(map, div, divisor, visited);
            
            if (rec != -1.0) {
                return ratio * rec;
            }
        }
        
        return -1.0;
    }
}
```

```java
    // ============ Sol 1: GRAPH ==============
    // 35ms
class Solution {
    Map<String, HashMap<String, Double>> map = new HashMap();
    
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        double[] res = new double[queries.length];
        
        // build graph
        for (int i = 0; i < equations.length; i++) {
            String x = equations[i][0];
            String y = equations[i][1];
            double k = values[i];
            // x / y = k : x -> <y, k>
            map.computeIfAbsent(x, n -> new HashMap<String, Double>()).put(y, k);
            // y / x = 1/k : y -> <x, 1/k>
            map.computeIfAbsent(y, n -> new HashMap<String, Double>()).put(x, 1 / k);
        }

        // query
        for (int i = 0; i < queries.length; i++) {
            String x = queries[i][0];
            String y = queries[i][1];
            if (!map.containsKey(x) || !map.containsKey(y)) {
                res[i] = -1;
                continue;
            }
            res[i] = dfs(x, y, new HashSet<String>());
        }
        
        return res;
    }
    
    public double dfs(String x, String y, HashSet<String> visited) {
        // base case
        if (x.equals(y)) return 1.0;
        
        if (!map.containsKey(x)) return -1.0;
        
        visited.add(x);
        
        for (String s : map.get(x).keySet()) {
            if (visited.contains(s)) continue;
            visited.add(s);
            
            double d = dfs(s, y, visited);
            
            // we don't have negative number in the problem.
            if (d != -1.0) {
                // a / c = (a / b) * (b / c) 
                return d * map.get(x).get(s);
            }
        }
        
        return -1.0;
    }
}
```
