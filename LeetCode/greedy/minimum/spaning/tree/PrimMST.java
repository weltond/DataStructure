package com.weltond.greedy.minimum.spaning.tree;

/**
 * A spanning tree means all vertices must be connected.
 *
 *
 * Prim's algo:
 *   The two disjoint subsets of vertices must be connected to make a spanning tree.
 *   And they must be connected with minimum weight edge to make it MST.
 *
 *   Steps:
 *   1. create a set mstSet that keeps track of vertices already included in MST
 *   2. Assign a key value to all V in the input graph.
 *      Initialize all key values as INFINITE.
 *      Assign key value as 0 for the first vertex so that it is picked first.
 *   3. While mstSet doesn't include all vertices.
 *      a. Pick a vertex u which is not there in mstSet and has minimum key value
 *      b. include u to mstSet
 *      c. update key value of all adjacent vertices of u.
 *         To update key values, iterate through all adjacent vertices.
 *         For every adjacent vertex v, if weight of edge u-v is less than the previous key value of v,
 *           update the key value as weight of u-v
 * @author weltond
 * @project LeetCode
 * @date 2/6/2019
 */

// Time = O(V^2).
// mstSet[]: represent the set of vertices included in MST.
// key[]: store key values of all vertices
// parent[]: store indexes of parent nodes in MST. output array
public class PrimMST {
    // Number of vertices in the graph
    private int V;

    public PrimMST(int v) {
        V = v;
    }

    // A utility function to find the vertex with minimum key value
    // from the set of vertices not yet included in mstSet
    int minKey(int[] key, boolean[] mstSet) {
        // initialize with min value
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // A utility function to print the constructed MST stored in parent[]
    void printMST(int[] parent, int n, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {   // start from 1 because the first vertex is always the root and has no parent.
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    // Function to construct and print MST for a graph
    // represented ussing adjacency matrix
    void printMST(int[][] graph) {
        // Array to store constructed MST
        int[] parent = new int[V];

        // key values used to pick minimum weight edge in cut
        int[] key = new int[V];

        // to represnet set of vertices not yet included in MST
        boolean[] mstSet = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // always include first vertex in MST
        key[0] = 0; // make key 0 so that this vertex is picked as first vertex
        parent[0] = -1; // first node is always root of MST

        // MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // pick the minimum key vertex from the set of vertices not yet included in MST
            int u = minKey(key, mstSet);
            // add the picked vertex to the MST set.
            mstSet[u] = true;

            // update key value and parent index of the adjacent vertices of the picked vertex
            // Consider only those vertices which are not yet included in MST
            for (int v = 0; v < V; v++) {
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, V, graph);
    }

    public static void test() {
        /* Let us create the following graph
            2    3
        (0)--(1)-- (2)
        |    / \    |
       6|  8/   \5  |7
        | /      \  |
        (3)-------(4)
              9         */
        PrimMST t = new PrimMST(5);
        int graph[][] = new int[][] {{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};

        // Print the solution
        t.printMST(graph);
    }

}
