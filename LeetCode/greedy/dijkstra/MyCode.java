package com.weltond.greedy.dijkstra;

import java.util.LinkedList;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/8/2019
 */
public class MyCode {
    private int V; // No. of  vertices

    public MyCode(int v) {
        V = v;
    }

    private int src;

    int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < V; i++) {
            if (!sptSet[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    void printSol(int[] dist, int n, int[] parent) {
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            System.out.println(i + "\t\t" + dist[i] + "\t\t" + printPath(parent, i, sb));
        }
    }

    LinkedList<Integer> q = new LinkedList<>();
    String printPath(int[] parent, int n, StringBuilder sb) {
        q.offerFirst(n);
        if (n == this.src) {
            while (!q.isEmpty()) {
                int x = q.poll();
                sb.append(x).append("-");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        printPath(parent, parent[n], sb);
        return sb.toString();
    }

    void d(int[][] graph, int src) {
        this.src = src;

        int[] parent = new int[V];
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        parent[src] = -1;
        dist[src] = 0;

        for (int cnt = 0; cnt < V; cnt++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            System.out.println("get: " + u);

            for (int i = 0; i < V; i++) {
                if (!sptSet[i] &&
                        graph[u][i] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][i] < dist[i]) {
                    parent[i] = u;
                    dist[i] = dist[u] + graph[u][i];
                }

            }
        }

        printSol(dist, V, parent);
    }

    public static void test() {
        int graph[][] = new int[][]{{0, 2,0,1,0,0,0},
                {0,0,0,3,10,0,0},
                {4,0,0,0,0,5,0},
                {0,0,2,0,2,8,4},
                {0,0,0,0,0,0,6},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0}
        };
        MyCode t = new MyCode(graph.length);
        t.d(graph, 0);

    }
}
