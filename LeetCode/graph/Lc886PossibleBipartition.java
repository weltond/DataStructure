// https://leetcode.com/problems/possible-bipartition/

class Solution {
    LinkedList<Integer>[] adj;
    public boolean possibleBipartition(int N, int[][] dislikes) {
        if (dislikes == null || dislikes.length == 0) return true;
        
        // build map
        adj = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new LinkedList();
        }
        for (int i = 0; i < dislikes.length; i++) {
            // should add for both way
            adj[dislikes[i][0] - 1].add(dislikes[i][1] - 1);
            adj[dislikes[i][1] - 1].add(dislikes[i][0] - 1);
        }
        
        int[] colors = new int[N]; // 0 is unknown, 1 is red, -1 is blue
        
        
        return dfs(colors, N);
        //return bfs(colors, N);
    }
    
    // ============ BFS ==============
    private boolean bfs(int[] colors, int N) {
        Queue<Integer> q = new LinkedList();
        
        for (int i = 0; i < N; i++) {
            if (colors[i] != 0) continue;
            q.offer(i);
            colors[i] = 1;
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : adj[cur]) {
                    if (colors[next] == 0) {
                        colors[next] = -colors[cur];
                        q.offer(next);
                    } else if (colors[next] == colors[cur]) {
                        //System.out.println(cur + ", " + next);
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    // ============ DFS ==============
    private boolean dfs(int[] colors, int N) {
        for (int i = 0; i < N; i++) {
            if (colors[i] == 0 && !dfs(i, 1, colors)) return false;
        }
        return true;
    }
    private boolean dfs(int n, int color, int[] colors) {
        colors[n] = color;
        
        for (int i : adj[n]) {
            // if visited but color is the same as parent 
            if (colors[i] == color) {
                System.out.println(n + ", " + i);
                return false;
            }
            
            // if unvisited, dfs next level
            if (colors[i] == 0 && !dfs(i, -color, colors)) return false;
        }
        
        return true;
    }
    
}
