package com.weltond.greedy.dijkstra;

import java.util.*;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/8/2019
 */
public class ShortestPathWithQueue {
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V;
    List<List<Node>>  adj;

    public ShortestPathWithQueue(int v) {
        V = v;
        dist = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    // Function to process all the neighbours of the passed node
    private void process(int u) {
        int edgeDistance = -1;
        //int newDistance = -1;

        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);
            // if current node hasn't already been processed
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                dist[v.node] = Math.min(dist[u] + edgeDistance, dist[v.node]);

                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    public void dijkstra(List<List<Node>> adj, int source) {
        this.adj = adj;
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        pq.add(new Node(source, 0));

        dist[source] = 0;

        while (settled.size() != V) {
            // remove the minimum distance node from the pq
            int u = pq.remove().node;
            System.out.println("get: " + u);
            // adding the node whose distance is finilized
            settled.add(u);

            process(u);
        }
    }

    public static void test() {
        int V = 5;
        int source = 0;

        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        /**
         *          4
         *          |
         *          | 3
         *       5  |    9
         *  3 ----- 0  ------ 1
         *    \     |        /
         *     \    | 6    /
         *     4\   |    /  2
         *       \  2 /
        */
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        ShortestPathWithQueue s = new ShortestPathWithQueue(V);
        s.dijkstra(adj, source);

        System.out.println("Ther shorted path from node: ");
        for (int i = 0; i < s.dist.length; i++) {
            System.out.println(source + " to " + i + " is " + s.dist[i]);
        }
        /**
         * Ther shorted path from node:
         * 0 to 0 is 0
         * 0 to 1 is 8
         * 0 to 2 is 6
         * 0 to 3 is 5
         * 0 to 4 is 3
         */
    }
}

class Node implements Comparator<Node> {
    public int node;
    public int cost;
    public Node() {}

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node o1, Node o2) {
        return o1.cost - o2.cost;
    }
}