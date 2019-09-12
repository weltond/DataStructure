package amazonOA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author weltond
 * @project LeetCode
 * @date 4/4/2019
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author weltond
 * @project LeetCode
 * @date 3/23/2019
 *
 * Input:
 * numTotalAvailableCities = 6
 * numTotalAvailableRoads = 3
 * roadsAvailable = [[1, 4], [4, 5], [2, 3]]
 * numNewRoadsConstruct = 4
 * costNewRoadsConstruct = [[1, 2, 5], [1, 3, 10], [1, 6, 2], [5, 6, 5]]
 *
 * Output: 7
 *
 * Explain:
 * There are 3 networks: [1,4,5],[2,3],[6].
 * We can connect these networks into a single network by connecting the city 1 to city 2 and to city 6 at minimum cost of 5 + 2 = 7
 *
 * Solution:
 * Minimum Spanning Tree Problem
 *
 * Steps:
 *  1. Sort all the edges in non-decreasing order of their weight
 *  2. (union find)Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far.
 *      If the cycle is not formed, include this edge. Else, discard it.
 *  3. Repeat step 2 until there are (V-1) edges in the spanning tree.
 *
 *
 * Time Complexity: O(ElogE) or O(ElogV).
 *  1. Sorting of edges takes O(ElogE) time.
 *  2. After sorting, we iterate through all edges and apply union-find algorithm which takes at most O(logV) time.
 *      ==> Overall, O(ElogEă€€+ ElogV).
 *     The value of E can be at most O(V^2). so O(logV) and O(logE) are same.
 *  Therefore, overall time is O(ElogE) or O(ElogV)
 */
public class MinCostToConstructCityRoad {
    class Edge implements Comparable<Edge> {
        int src, dest, cost;

        Edge(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }
        // sort edge based on cost ascending
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    class DisJoint {
        private int[] parent;
        private int[] rank;
        private int size;
        DisJoint(int size) {
            this.size = size;
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx == ry) return;
            if (rank[rx] > rank[ry]) parent[ry] = rx;
            else if (rank[rx] < rank[ry]) parent[rx] = ry;
            else {
                parent[ry] = rx;
                rank[rx] += 1;
            }
        }
    }
    public int getMinCostToConstruct(int numTotalAvailableCities,
                                     int numTotalAvailableRoads,
                                     List<List<Integer>> roadsAvailable,
                                     int numNewRoadsConstruct,
                                     List<List<Integer>> costNewRoadsConstruct) {
        int res = 0;
        // corner case depends on how you define the meaning of input. Not applicable here.
        // if (numTotalAvailableCities < 2 || numTotalAvailableRoads >= numTotalAvailableCities - 1) return res;

        // 0. initialize unionfind
        DisJoint dj = new DisJoint(numTotalAvailableCities);

        int existEdges = 0;

        for (List<Integer> pair : roadsAvailable) {
            int src = pair.get(0) - 1;
            int dest = pair.get(1) - 1;
            // NOTICE: Update existEdges only if two are not connected before!!
            int rs = dj.find(src);
            int rd = dj.find(dest);
            if (rs == rd) continue;
            dj.union(src, dest);
            existEdges++;
        }

        Edge[] edges = new Edge[numNewRoadsConstruct];

        // 1. generate available edges based on cost in ascending order
        int i = 0;
        for (List<Integer> list : costNewRoadsConstruct) {
            Edge edge = new Edge(list.get(0) - 1, list.get(1) - 1, list.get(2));
            edges[i++] = edge;

        }
        Arrays.sort(edges); // sort in ascending order

        // 2. iterate edges and pick smallest edge. Make sure there is no cycle.
        //    Stop if No.edge = No.Vertices - 1
        int newEdgesCnt = 0;
        i = 0;
        while (existEdges + newEdgesCnt < numTotalAvailableCities - 1) {
            // if not exist
            if (i >= numNewRoadsConstruct) return -1;

            Edge nextEdge = edges[i++]; // ascending order

            int x = dj.find(nextEdge.src);
            int y = dj.find(nextEdge.dest);

            // if popped edge doesn't have the same root, it means this is a valid case

            if (x != y) {
                newEdgesCnt++;
                dj.union(x, y);
                System.out.println(nextEdge.src + " , " + nextEdge.dest);
                res += nextEdge.cost;
            }
            // otherwise discard it
        }
        return res;
    }

    public static void main(String[] args) {
        MinCostToConstructCityRoad graph = new MinCostToConstructCityRoad();

        List<List<Integer>> roadsAvailable = new ArrayList<>();
        List<Integer> road1 = new ArrayList<>();
        road1.add(1); road1.add(4);
        roadsAvailable.add(road1);
        List<Integer> road2 = new ArrayList<>();
        road2.add(4); road2.add(5);
        roadsAvailable.add(road2);
        List<Integer> road3 = new ArrayList<>();
        road3.add(2); road3.add(3);
        roadsAvailable.add(road3);
        List<Integer> road4 = new ArrayList<>();
        road4.add(2); road4.add(3);
        roadsAvailable.add(road4);


        List<List<Integer>> costNewRoadsConstruct = new ArrayList<>();
        List<Integer> new1 = new ArrayList<>();
        new1.add(1); new1.add(2);new1.add(5);
        costNewRoadsConstruct.add(new1);
        List<Integer> new2 = new ArrayList<>();
        new2.add(1); new2.add(3);new2.add(10);
        costNewRoadsConstruct.add(new2);
        List<Integer> new3 = new ArrayList<>();
        new3.add(1); new3.add(6);new3.add(2);
        costNewRoadsConstruct.add(new3);
        List<Integer> new4 = new ArrayList<>();
        new4.add(5); new4.add(6);new4.add(5);
        costNewRoadsConstruct.add(new4);
        List<Integer> new5 = new ArrayList<>();
        new5.add(1); new5.add(4);new5.add(2);
        costNewRoadsConstruct.add(new5);
        List<Integer> new6 = new ArrayList<>();
        new6.add(2); new6.add(3);new6.add(7);
        costNewRoadsConstruct.add(new6);

        int res = graph.getMinCostToConstruct(6, 4, roadsAvailable, 6, costNewRoadsConstruct);

        System.out.println(res);
    }
}
