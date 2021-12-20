// https://leetcode.com/problems/course-schedule/

/**
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that 
you must take course bi first if you want to take course ai.
*/
class Solution {
    private List<List<Integer>> buildGraph(int[][] arr, int numCourses) {
        List<List<Integer>> graph = new ArrayList();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList());
        }
        
        for (int i = 0; i < arr.length; i++) {
            graph.get(arr[i][0]).add(arr[i][1]);
        }
        
        return graph;
    }
    
    // ============== Method 2: Topological Sort (BFS) =============
    // 5 ms ()
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;
        
        List<List<Integer>> graph = buildGraph(prerequisites, numCourses);
        
        // get each node's indegree
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][1]]++;
        }
        
        Queue<Integer> q = new LinkedList();
        
        // put indegree is 0's node into queue as start
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) q.offer(i);
        }
        
        // count how many nodes are removed. if there is any remaining, it means there is a cycle.
        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll(); // this node is garunteed not relied on other courses
            
            // for any neighbor that has 0 indegree, we put them into queue
            for (int next : graph.get(cur)) {
                if (--indegree[next] == 0) {
                    q.offer(next);
                }
            }
            cnt++;
        }
        
        return cnt == numCourses;
    }
    
    // ============== Method 1: DFS ================
    // 105ms (11.38%)
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
//         if (prerequisites == null || prerequisites.length == 0) return true;
        
//         List<List<Integer>> graph = buildGraph(prerequisites, numCourses);
        
//         for (int i = 0; i < numCourses; i++) {
//             if (!dfs(graph, i, new boolean[numCourses], new boolean[numCourses])) {
//                 return false;
//             }
//         }

//         return true;
//     }
    
//     private boolean dfs(List<List<Integer>> graph, int course, boolean[] visited, boolean[] rec) {
//         // rec array represent each course that is visited again if true
//         if (rec[course]) return false;
        
//         visited[course] = true;
//         rec[course] = true;
        
//         List<Integer> children = graph.get(course);
        
//         for (Integer next : children) {
//             // there is any cycle in children
//             if (!dfs(graph, next, visited, rec)) return false;
//         }
        
//         visited[course] = false;
//         rec[course] = false;
        
//         return true;
//     }
}
