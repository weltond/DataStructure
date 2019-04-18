// https://leetcode.com/problems/course-schedule-ii/

/**
Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
*/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) return new int[]{0};
        
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        
        // get indegree for each course.
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++; 
        }
        
        Queue<Integer> q = new LinkedList();
        // if course indegree is 0, which means it doesn't rely on any other courses
        // put them into the queue
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }
        
        int cnt = 0;    // used to verify no circle
        int[] res = new int[numCourses];
        while (!q.isEmpty()) {
            int course = q.poll();
            res[cnt] = course;

            for (Integer next : graph.get(course)) {
                if (--indegree[next] == 0)
                    q.offer(next);
            }
            
            cnt++;
        }
        
        return cnt == numCourses ? res : new int[]{};
    }
    
    private List<List<Integer>> buildGraph(int num, int[][] arr) {
        List<List<Integer>> graph = new ArrayList();
        
        for (int i = 0; i < num; i++) {
            graph.add(new ArrayList());
        }
        
        for (int i = 0; i < arr.length; i++) {
            graph.get(arr[i][1]).add(arr[i][0]);
        }
        
        return graph;
    }
}


// ================= To Get Permutation of All Topological Sort ========================
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) return new int[]{0};
        
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        
        // get indegree for each course.
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++; 
        }
        
        Queue<Integer> q = new LinkedList();
        // if course indegree is 0, which means it doesn't rely on any other courses
        // put them into the queue
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        int[] res = new int[numCourses];
        
        List<List<Integer>> ans = new ArrayList();
        boolean isPossible = dfs(indegree, 0, numCourses, graph, new ArrayList(), new boolean[numCourses], ans);
        
        if (!isPossible) return new int[]{};
        
        int cnt = 0;
        for (int i : ans.get(0)) {
            res[cnt++] = i;
        }
        return isPossible ?  res: new int[]{};
    }
    
    private boolean dfs(int[] indegree, int lvl, int numCourses, List<List<Integer>> graph, List<Integer> stack, boolean[] visited, List<List<Integer>> all) {
        if (lvl == numCourses) {
            all.add(new ArrayList(stack));
            return true;
        }
        
        boolean flag = false;
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && indegree[i] == 0) {
                visited[i] = true;
                stack.add(i);
                for (int adj : graph.get(i)) {  
                    indegree[adj]--;
                }
                
                // NOTICE: should verify dfs() FIRST! If verify flag first, once flag is true, it won't execute dfs()
                flag = dfs(indegree, lvl + 1, numCourses, graph, stack, visited, all) || flag;
                
                // backtracking
                visited[i] = false;
                stack.remove(stack.size() - 1);
                for (int adj : graph.get(i)) {
                    indegree[adj]++;
                }
            }
        }

        return flag;
    }
    
    private List<List<Integer>> buildGraph(int num, int[][] arr) {
        List<List<Integer>> graph = new ArrayList();
        
        for (int i = 0; i < num; i++) {
            graph.add(new ArrayList());
        }
        
        for (int i = 0; i < arr.length; i++) {
            graph.get(arr[i][1]).add(arr[i][0]);
        }
        
        return graph;
    }
}
