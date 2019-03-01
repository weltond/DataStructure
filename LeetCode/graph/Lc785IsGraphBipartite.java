// https://leetcode.com/problems/is-graph-bipartite/

class Solution {
    int[][] graph;
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) return true;
        
        this.graph = graph;
        
        // no need to build graph since this is already a graph
        int[] colors = new int[graph.length];
        
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0 && !dfs(i, 1, colors)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean dfs(int i, int color, int[] colors) {
        if (colors[i] != 0) {
            return colors[i] == color;
        }
        
        colors[i] = color;
        
        // check i's neighbor
        for (int j = 0; j < graph[i].length; j++) {
            int next = graph[i][j];
            if (!dfs(next, -color, colors)) {
                return false;
            }
        }
        
        return true;
    }
}
