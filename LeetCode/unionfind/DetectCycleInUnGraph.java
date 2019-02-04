package com.weltond.unionfind;

/**
 * See also com.weltond.graph has two other solutions, i.e. BFS and DFS
 *
 * Time = O(ElogV)
 *
 * @author weltond
 * @project LeetCode
 * @date 2/4/2019
 */
public class DetectCycleInUnGraph {
    int V, E; // V-> no. of vertices & E -> no. of edges
    Edge edge[]; // collection of all edges
    class Edge {
        int src, dest;
    }

    // create a graph with V vertices and E edges
    DetectCycleInUnGraph(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i) {
            edge[i] = new Edge();
        }
    }

    // A utility function to find the subset of an element i
    int find(int parent[], int i) {
        if (parent[i] == -1) return i;
        return find(parent, parent[i]);
    }

    // unite two subsets
    void union(int parent[], int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    class Subset {
        int parent;
        int rank;
    }

    // find set of an element i uses path compression tech
    int findPathCompression(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = findPathCompression(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    // unite two sets of x and y using union by rank
    void unionByRank(Subset[] subsets, int x, int y) {
        int xroot = findPathCompression(subsets, x);
        int yroot = findPathCompression(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[xroot].parent = yroot;
            subsets[yroot].rank++;
        }
    }

    // main function to check whether a given graph contains cycle or not
    boolean isCycle(DetectCycleInUnGraph graph) {
        // allocate memory for creating V subsets
        int parent[] = new int[graph.V];

        // initialize all subsets as single element sets
        for (int i = 0; i < graph.V; ++i) {
            parent[i] = -1;
        }

        // iterate through all edges of graph
        // find subset of both vertices of every edge
        // if both subsets are same, then there is cycle in graph
        for (int i = 0; i < graph.E; ++i) {
            int x = graph.find(parent, graph.edge[i].src);
            int y = graph.find(parent, graph.edge[i].dest);

            if (x == y) {
                return true;
            }

            graph.union(parent, x, y);
        }

        return false;
    }
}
