class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //if (prerequisites == null || prerequisites.length == 0) return new int[]{0};
        
        List<List<Integer>> arr = build(numCourses, prerequisites);
        
        Queue<Integer> q = new LinkedList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
        }
        
        for(int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }
        
        int cnt = 0;
        int[] res = new int[numCourses];
        while(!q.isEmpty()) {
            int u = q.poll();
            res[cnt++] = u;
            
            for (int node: arr.get(u)) {
                if (--indegree[node] == 0) {
                    q.add(node);
                }
            }
            
            //cnt++;
        }
        if (cnt != numCourses) return new int[]{};
        return res;
    }
    
    public List<List<Integer>> build(int num, int[][] pre) {
        List<List<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            arr.add(new ArrayList());
        }
        
        for (int i = 0; i < pre.length; i++) {
            arr.get(pre[i][1]).add(pre[i][0]);
        }
        
        return arr;
    }
}