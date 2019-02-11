package com.weltond.graph.topological;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** In DAG, we can have vertices which are unrelated to each other because of which we can order them in many ways.
 *
 *  If some relateive weight is also available between the vertices, which is to minimize then we need to
 *     take care of relative ordering as well as their relative weight.
 *
 *  Steps:
 *  1. Initialize all vertices as unvisited.
 *  2. Now choose vertex which is unvisited and has zero indegree and decrease indegree of all those vertices
 *     by 1 (corresponding to removing edges). Now add this vertex to result and call the recursive function
 *     again and backtrack.
 *  3. After returning from function, reset values of visited, result and indegree fro enumeration of other possibles.
 *
 * @author weltond
 * @project LeetCode
 * @date 2/7/2019
 */
public class PrintAllTopologicalSorts {
    int V;  // No. of vertices
    List<Integer>[] adjListArray;

    public PrintAllTopologicalSorts(int V) {
        this.V = V;

        adjListArray = new LinkedList[V];

        for (int i = 0; i < V; i++) {
            adjListArray[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        this.adjListArray[src].add(dest);
    }

    public void allUtils(boolean[] visited, int[] indegree, ArrayList<Integer> stack) {
        // To indicate whether all topological are found or not
        boolean flag = false;

        for (int i = 0; i < V; i++) {
            // if in degree is 0 and not yet visited, then only choose that vertex
            if (!visited[i] && indegree[i] == 0) {
                // including in result
                visited[i] = true;
                stack.add(i);
                for (int adj : adjListArray[i]) {
                    indegree[adj]--;
                }
                allUtils(visited, indegree, stack);

                // resetting visited, res and indegree for backtracking
                visited[i] = false;
                stack.remove(stack.size() - 1);
                for (int adj : adjListArray[i]) {
                    indegree[adj]++;
                }

                flag = true;
            }
        }

        // we reach here if all vertices are visited.
        // so we print the solution here
        if (!flag) {
            stack.forEach(i -> System.out.print(i + " "));
            System.out.println();
        }
    }

    public void allUtils2(boolean[] visited, int[] indegree, ArrayList<Integer> stack, int lvl) {
        // we reach here if all vertices are visited.
        // so we print the solution here
        if (lvl == V) {
            stack.forEach(i -> System.out.print(i + " "));
            System.out.println();
        }

        for (int i = 0; i < V; i++) {
            // if in degree is 0 and not yet visited, then only choose that vertex
            if (!visited[i] && indegree[i] == 0) {
                // including in result
                visited[i] = true;
                stack.add(i);
                for (int adj : adjListArray[i]) {
                    indegree[adj]--;
                }
                allUtils2(visited, indegree, stack, lvl + 1);

                // resetting visited, res and indegree for backtracking
                visited[i] = false;
                stack.remove(stack.size() - 1);
                for (int adj : adjListArray[i]) {
                    indegree[adj]++;
                }

            }
        }


    }

    public void allSort() {
        boolean[] visited = new boolean[V];
        int[] indegree = new int[V];

        for (int i = 0; i < V; i++) {
            for (int var : adjListArray[i]) {
                indegree[var]++;
            }
        }

        ArrayList<Integer> stack = new ArrayList<>();
        //allUtils(visited, indegree, stack);
        allUtils2(visited, indegree, stack, 0);
    }

    public static void test() {
        PrintAllTopologicalSorts graph = new PrintAllTopologicalSorts(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.println("All Topological sorts");
        graph.allSort();
        /**
         * All Topological sorts
         * 4 5 0 2 3 1
         * 4 5 2 0 3 1
         * 4 5 2 3 0 1
         * 4 5 2 3 1 0
         * 5 2 3 4 0 1
         * 5 2 3 4 1 0
         * 5 2 4 0 3 1
         * 5 2 4 3 0 1
         * 5 2 4 3 1 0
         * 5 4 0 2 3 1
         * 5 4 2 0 3 1
         * 5 4 2 3 0 1
         * 5 4 2 3 1 0
         */
    }
}
