package BFS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Lc207CourseSchedule {

    ArrayList<Integer> graph[];

    public static void main(String[] args) {
        int num = 4;
        int[][] pre = {{1,2}, {0, 1}, {1,3}, {2, 3}, {3,0}};
        // under this situation, the recursion goes line in: 1->2->3->0->1
        // if {1,3} is before {1,2}, then recursion goes line is: 1->3->0->1

        System.out.println(new Lc207CourseSchedule().canFinish(num, pre));

    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        buildGraph(numCourses, prerequisites);

        // DFS
        /*
        boolean[] visited = new boolean[numCourses];

        for(int i=0; i<numCourses; i++){
            if(!dfs(graph,visited,i))
                return false;
        }

        return true;
        */

        // BFS
        return bfs(graph, prerequisites, 4);
    }

    public void buildGraph(int numCourses, int[][] pre) {
        for(int i = 0; i < pre.length; i++) {
            graph[pre[i][0]].add(pre[i][1]);
            //System.out.println(pre[i][0] + ":" + graph[pre[i][0]]);
        }
    }

    public boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
        if(visited[course]) return false;

        visited[course] = true;

        Iterator<Integer> i = graph[course].listIterator();
        while(i.hasNext()) {
            int n = i.next();

            if (!dfs(graph, visited, n)) {
                System.out.println("Stopped at: " + n);
                return false;
            }
        }

        visited[course] = false;

        return true;
    }

    public boolean bfs(ArrayList[] graph, int[][] prerequisites, int numCourses) {
        //boolean visited[] = new boolean[graph.length];
        int[] degree = new int[numCourses];

        for(int i=0; i<prerequisites.length;i++){
            degree[prerequisites[i][1]]++;
            //graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it.
        //visited[0] = true;
        queue.add(0);

        while (queue.size() != 0) {
            // Dequeue from queue
            int course = queue.poll();
            degree[course] -= 1;
            if (degree[course] < 0) return false;
            // Get all adjacent vertices of the dequeued item.
            // if a adjacent has not been visited, then mark it visited and enqueue it.
            Iterator<Integer> i = graph[course].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                queue.add(n);
            }
        }

        return true;
    }
}
