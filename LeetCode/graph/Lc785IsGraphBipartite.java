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

// 6ms (11.68%)
class Solution {
    public boolean isBipartite(int[][] graph) {
        Map<Integer, List<Integer>> map = new HashMap();
        
        for (int i = 0; i < graph.length; i++) {
            // cannot use map.computeIfAbsent because one element might be empty
            // e.g.: [[4],[],[4],[4],[0,2,3]]
            List<Integer> list = new LinkedList();
            for (int j = 0; j < graph[i].length; j++) {
                list.add(graph[i][j]);
            }
            map.put(i, list);
        }

        int[] colors = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0) {
                if (!dfs(i, map, colors, 1)) return false;
            }
        }

        return true;
    }
    
    private boolean dfs(int start, Map<Integer, List<Integer>> map, int[] colors, int color) {
        if (colors[start] != 0) return color == colors[start];
        
        colors[start] = color;
        
        for (int next : map.get(start)) {
            if (!dfs(next, map, colors, -color)) return false;
        }
        
        return true;
    }
}
