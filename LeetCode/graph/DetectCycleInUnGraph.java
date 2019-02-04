package com.weltond.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/4/2019
 */
public class DetectCycleInUnGraph {

    private int V;  // no. of vertices
    private LinkedList<Integer> adj[];  // adjacency list

    public DetectCycleInUnGraph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // ============================ DFS ===============================
    // DFS use visited[] and parent to detect cycle in sub-graph reachable from vertex v
    boolean isCyclicUtilDFS(int v, boolean visited[], int parent) {
        // mark the current visited
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent to this index
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();

            // if an adjacent is not visited, then recur for that adjacent
            if (!visited[i]) {
                if (isCyclicUtilDFS(i, visited, v)) {
                    return true;
                }
            }
            // if an adjacent is visited and not parent of current vertex, then there is a cycle
            else if (i != parent) {
                return true;
            }
        }
        return false;
    }

    boolean isCyclicDFS() {
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (isCyclicUtilDFS(i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    // ============================ BFS ===============================
    boolean isCyclicUtilBFS(int s, boolean[] visited) {
        // set parent vertex for every vertex as -1
        int parent[] = new int[V];
        Arrays.fill(parent, -1);

        Queue<Integer> q = new LinkedList<>();

        // mark the current node as visited and enqueue
        visited[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            // Dequeue a vertex from queue and print it
            int u = q.poll();

            // get all adjacent vertices of the dequeued vertex u.
            // if a adjacent has not been visited, then mark it visited and enqueue it.
            // we also mark parent so that parent is not considered for cycle

            for (int i = 0; i < adj[u].size(); i++) {
                int v = adj[u].get(i);
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                    parent[v] = u;
                } else if (parent[u] != v) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean isCyclicBFS() {
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i] && isCyclicUtilBFS(i, visited)) {
                return true;
            }
        }
        return false;
    }

    public static void test() {
        DetectCycleInUnGraph g1 = new DetectCycleInUnGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        if (g1.isCyclicDFS())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");

        if (g1.isCyclicBFS()) {
            System.out.println("BFS contains");
        } else {
            System.out.println("BFS not contains");
        }
        DetectCycleInUnGraph g2 = new DetectCycleInUnGraph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        if (g2.isCyclicDFS())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contains cycle");

        if (g2.isCyclicBFS()) {
            System.out.println("BFS contains");
        } else {
            System.out.println("BFS not contains");
        }
    }
}

