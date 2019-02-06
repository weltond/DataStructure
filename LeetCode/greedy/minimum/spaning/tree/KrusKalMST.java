package com.weltond.greedy.minimum.spaning.tree;

import java.util.Arrays;

/**
 * - Spanning Tree: Given a connected and undirected graph, a spanning tree of that graph is a subgraph that is a tree and connects all the vertices together
 * - Minimum Spanning Tree: The spanning tree of the graph whose sum of weights of edges is minimum
 *      - A graph may have more than 1 minimum spanning tree.
 *
 * Steps:
 *  1. Sort all the edges in non-decreasing order of their weight
 *  2. (union find)Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far.
 *      If the cycle is not formed, include this edge. Else, discard it.
 *  3. Repeat step 2 until there are (V-1) edges in the spanning tree.
 *
 *
 *  Time Complexity: O(ElogE) or O(ElogV).
 *      1. Sorting of edges takes O(ElogE) time.
 *      2. After sorting, we iterate through all edges and
 *         apply union-find algorithm which takes at most O(logV) time.
 *      ==> Overall, O(ElogE　+ ElogV).
 *      The value of E can be at most O(V^2). so O(logV) and O(logE) are same.
 *      Therefore, overall time is O(ElogE) or O(ElogV)
 *
 * @author weltond
 * @project LeetCode
 * @date 2/6/2019
 */
public class KrusKalMST {
    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        // sort edges based on weight ascending
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    class subset {
        int parent, rank;
    }

    // No. of vertices and edges
    int V, E;
    // collection of all edges
    Edge edge[];

    // contructor to create a graph with V and E
    KrusKalMST(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; i++) {
            edge[i] = new Edge();
        }
    }

    // = union find =
    int find(subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    void union(subset[] subsets, int x, int y) {
        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        if (subsets[xRoot].rank < subsets[yRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else if (subsets[xRoot].rank > subsets[xRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }

    // main func to construct MST using Kruskal's algo
    void generateMST() {
        Edge[] result = new Edge[V];    // store final result
        int e = 0;  // index variable, used for result[]
        int i = 0;  // index variable, used for sorted edges
        for (i = 0; i < V; i++) {
            result[i] = new Edge(); // result is like [[Edge0], [Edge1],...]
        }

        // Step 1
        // Sort all the edges in non-decreasing order
        // if not allowed to change the given graph, we can create a copy of array of edges
        Arrays.sort(edge);

        // Allocate memory for creating V subsets
        subset[] subsets = new subset[V];
        for (i = 0; i < V; i++) {
            System.out.println(i);
            subsets[i] = new subset();
        }

        // Create V subsets with single elements
        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0; //index to pick next edge

        // Number of edges to be taken is equal to V - 1;
        while (e < V - 1) {
            // step 2
            // pick the smallest edge. and increment the index for next iteration
            Edge nextEdge = new Edge();
            nextEdge = edge[i++];   // edge has been sorted. this edge has the smallest weight

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            // if including this edge doesn't cause cycle
            // include it in the result and increment the index of result for next edge
            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }

            // else discard the edge
        }

        System.out.println("Following are edges in the constructed MST");

        for (i = 0; i < e; i++) {
            System.out.println(result[i].src + "--" + result[i].dest + " == " + result[i].weight);
        }
    }

    public static void test() {
        /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */

        int V = 4;  // No. of vertices
        int E = 5;  // No. of edges

        KrusKalMST graph = new KrusKalMST(V, E);
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;

        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;

        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;

        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;

        // add edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        graph.generateMST();
    }

//    public static void main(String[] args) {
//        KrusKalMST k = new KrusKalMST(2,3);
//        KrusKalMST.Edge e1 = k.new Edge();
//        KrusKalMST.Edge e2 = k.new Edge();
//        e1.weight = 2;
//        e2.weight = 3;
//        System.out.println();
//    }
}
