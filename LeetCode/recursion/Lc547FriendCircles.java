// https://leetcode.com/problems/friend-circles/

/**
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.

Input:
[[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]] 
Output: 1
*/


class Solution {
    // ============= Method 2: Union Find ================
    // 2ms (89.28%)
    public int findCircleNum(int[][] m) {
        if (m == null) return 0;
        
        UF uf = new UF(m.length);
        
        for (int i = 0; i < m.length; i++) {
            for (int j = i; j < m[0].length; j++) {
                if (m[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        
        int res = 0;
        
        for (int i = 0; i < m.length; i++) {
            if (uf.find(i) == i) 
                res++;
        }
        
        return res;
    }
    
    class UF {
        int[] parent;
        int size;
        UF(int size) {
            this.size = size;
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx == ry) return;
            parent[ry] = rx;
        }
    }
    
    // ============= Method 1: DFS ================
    // 1ms (100%)
    public int findCircleNum(int[][] m) {
        if (m == null) return 0;
        
        int cnt = 0;
        boolean[] visited = new boolean[m.length];
        for (int i = 0; i < m.length; i++) {
            if (!visited[i]) {
                dfs(m, i, visited);
                cnt++;
            }
        }
        
        return cnt;
    }
    
    private void dfs(int[][] m, int root, boolean[] visited) {
        if (visited[root]) return;
        
        visited[root] = true;
        
        for (int i = 0; i < m.length; i++) {
            if (i != root && m[root][i] == 1 && !visited[i])
                dfs(m, i, visited);
        } 
    }
}
