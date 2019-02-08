package com.weltond.greedy.dijkstra;

import java.util.LinkedList;
import java.util.Queue;

/** Time = O(V^2)
 *  If the input graph is represented in adjacency list, it can be reduced to ElogV with the help of binary heap
 *
 *  Note: it doesn't work for graphs with negative weight edges. Please refer to Bellman-Ford algorithm.
 * Steps:
 *  1. Create a set sptSet(shortest path tree set) that keeps track of vertices
 *     included in shortest path tree, i.e., whose minimum distance from source
 *     is calculated and finalized. Initially, this set is empty.
 *  2. Asign a distance value to all vertices in the input graph. Initialize all
 *     distance values as INFINITE. Assign distance value as 0 for the source vertex
 *     so that it is picked first.
 *  3. While sptSet doesn't include all vertices:
 *     a) pick a vertex u which is not there in sptSet and has minimum distance value.
 *     b) include u to sptSet
 *     c) update distance value of all adjacent vertices of u. To update the distance
 *        vertex v, if sum of distance value of u(from source) and weight of edge u-v,
 *        is less than the distance value of v, then update the distance value of v.
 * @author weltond
 * @project LeetCode
 * @date 2/7/2019
 */
public class ShortestPath {
    int V;  // No. of vertices

    public ShortestPath(int v) {
        V = v;
    }

    private int src;

    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    int minDistance(int dist[], boolean[] sptSet) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] < min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    // A utility function to print the constructed distance
    void printSolution(int[] dist, int n, int[] parent) {
        System.out.println("Vertex \tDistance from Source \tPath");
        for (int i = 0; i < V; i++) {
            StringBuilder sb = new StringBuilder();
            printPath(parent, i, sb);
            System.out.println(i + "\t\t" + dist[i] + "\t\t\t\t\t\t" + sb.toString());
        }
    }

    // A utility to print path
    private LinkedList<Integer> q = new LinkedList<>();

    void printPath(int[] parent, int n, StringBuilder sb) {
        q.offerFirst(n);
        if (n == this.src) {
            while (!q.isEmpty()) {
                int x = q.poll();
                sb.append(x).append("-");
            }
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        printPath(parent, parent[n], sb);
    }

    // function that implement Dijkstra's single source shortest path algorithm
    // for a graph represented using adjacency matrix representation
    void dijkstra(int[][] graph, int src) {
        this.src = src;
        int[] dist = new int[V];    // output array
        int[] parent = new int[V];  // parent array

        //sptSet[i] will true if vertex i isincluded in shortest path tree
        // or shortest distance from src to i is finalized
        boolean[] sptSet = new boolean[V];

        // initialize all distances as INFINITE
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;
        parent[src] = -1;   // parent of source is always itself

        // Find shortest path for all vertices
        for (int i = 0; i < V ; i++) {  // or i < V - 1 to minimize the run time
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed.
            // u is always equal to src in first iteration.
            int u = minDistance(dist, sptSet);
            // Mark the picked vertex as processed
            sptSet[u] = true;

            // update dist value of the adacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                // update disv[v] only if it is not in sptSet,
                // there is an edge from u to v,
                // and total weight of path from src to v through u is smaller than current value of dist[v]
                if (!sptSet[v] &&
                        graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        printSolution(dist, V, parent);
    }

    public static void test() {
        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        ShortestPath t = new ShortestPath(graph.length);
        t.dijkstra(graph, 0);
        /**
         * Vertex 	Distance from Source 	Path
         * 0		0						0
         * 1		4						0-1
         * 2		12						0-1-2
         * 3		19						0-1-2-3
         * 4		21						0-7-6-5-4
         * 5		11						0-7-6-5
         * 6		9						0-7-6
         * 7		8						0-7
         * 8		14						0-1-2-8
         */
    }

}
