package com.weltond.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/** To detect a back edge, we can keep track of vertices currently in recursion stack of function for DFS traversal.
 *  If we reach a vertex that is alredy in the recursion stack, then there is a cycle.
 *
 *  Time = O(V + E)
 * @author weltond
 * @project LeetCode
 * @date 2/5/2019
 */
public class DetectCycleInDirGraph {
    private final int V;
    private final List<List<Integer>> adj;

    public DetectCycleInDirGraph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adj.add(new LinkedList<>());
        }
    }

    public static void pr(boolean[] booleans) {
        for (int i = 0; i < booleans.length; i++) {
            System.out.print(booleans[i] + " ");
        }
    }
    private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {
        // Mark the current node as visited and part of recursion stack
        if (recStack[i]) return true;
        //if (visited[i]) return false;

        visited[i] = true;
        recStack[i] = true;
        System.out.print(i + " -> b: ");
        pr(recStack);
        System.out.println();
        List<Integer> children = adj.get(i);

        for (Integer c : children) {
            if (isCyclicUtil(c, visited, recStack)) {
                return true;
            }
        }

        recStack[i] = false;
        System.out.print(i + " -> a: ");
        pr(recStack);
        System.out.println();
        return false;
    }

    private void addEdge(int source, int dest) {
        adj.get(source).add(dest);
    }

    private boolean isCyclic() {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i] && isCyclicUtil(i, visited, recStack)) {
                return true;
            }
        }

        return false;
    }

    public static void test() {
        DetectCycleInDirGraph graph = new DetectCycleInDirGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        //graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        //graph.addEdge(3, 3);

        if(graph.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");
    }
}
