package com.weltond.graph.topological;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**Topological:
 *
 * 1. simple DFS with extra stack - Time = O(V + E)
 *
 * @author weltond
 * @project LeetCode
 * @date 2/5/2019
 */
public class TopologicalSort {
    private int V;  // No. of vertices
    private LinkedList<Integer> adj[];  // Adjacent list

    public TopologicalSort(int v) {
        V = v;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    /**
     * ========================= DFS =============================
     * 1. Recursively call func for all its adjacent vertices
     * 2. when there is no adjacent, push it to stack
     * 3. print contents of stack
     *
     * @param v current vertex
     * @param visited visited vertex array
     * @param stack temp stack to store final result
     */
    void topSortUtilDFS(int v, boolean[] visited, LinkedList<Integer> stack) {
        visited[v] = true;
        Integer i;

        Iterator<Integer> it = adj[v].listIterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i]) {
                topSortUtilDFS(i, visited, stack);
            }
        }

        // Here there is no children for current v.
        // push current vertex to stack which stores result
        stack.push(new Integer(v));
    }

    void topSort() {
        LinkedList<Integer> stack = new LinkedList<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topSortUtilDFS(i, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    /**
     * ============= Kahn's Algo (In Degree) ===========
     * 1. Compute in-degree for each of the vertex(use method b here) present in the DAG and initialize the
     * count of visited nodes as 0.
     *    a. Traverse the array of edges and simply increase the counter of the dest node by 1.
     *    b. Traverse the list for every node and then increment the in-degree of all the nodes connected to it by 1
     * 2. Pick all vertices with in-degree as 0 and add them into a queue
     * 3. Remove a vertex from queue
     *    3.1 Increment count of visited node by 1
     *    3.2 Decrease in-degree by 1 for all its neighbor nodes.
     *    3.3 add to queue if in-degree reduces to 0.
     * 4. repeat step 3 until queue empty
     * 5. if count of visited node is not equal to the number of nodes in the graph, there is a cycle.
     */
    void topSortInDegree() {
        // array to store indegrees of all vertices
        int indegree[] = new int[V];

        // traverse adjacency lists to fill indegrees of vertices
        // take O(V+E) time
        for (int i = 0; i < V; i++) {
            LinkedList<Integer> tmp = (LinkedList<Integer>) adj[i];
            for (int node : tmp) {
                indegree[node]++;
            }
        }

        // queue to enqueue all vertices with indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // initialize count of visited vertices
        int cnt = 0;

        // store result
        ArrayList<Integer> topOrder = new ArrayList<>();
        while (!q.isEmpty()) {
            // poll from queue which contains all 0 indegree vertices
            int u = q.poll();
            topOrder.add(u);

            // iterate through all its neighbors
            // of dequeued node u and decrease their indegree by 1
            for (int node : adj[u]) {
                if (--indegree[node] == 0) {
                    q.add(node);
                }
            }

            cnt++;
        }

        // check if there has a cycle
        if (cnt != V) {
            System.out.println("There exists a cycle in the graph");
            return;
        }

        // print
        for (int i : topOrder) {
            System.out.print(i + " ");
        }
    }

    public static void test() {
        TopologicalSort g = new TopologicalSort(3);
//        g.addEdge(5, 2);
//        g.addEdge(5, 0);
//        g.addEdge(4, 0);
//        g.addEdge(4, 1);
//        g.addEdge(2, 3);
//        g.addEdge(3, 1);
        g.addEdge(0, 1);
        g.addEdge(2, 1);
        g.addEdge(2, 0);
        System.out.println("Following is a Topological " +
                "sort of the given graph");
        g.topSort();
        System.out.println("====");
        g.topSortInDegree();
    }
}
