// https://leetcode.com/problems/cut-off-trees-for-golf-event/

class Solution {
    /**
    Input: 
    [
     [1,2,3],
     [0,0,4],
     [7,6,5]
    ]
    Output: 6
    */
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) return -1;
        
        List<Node> list = new ArrayList();
        // add tree to list and sort them in ascending order
        for (int x = 0; x < forest.size(); x++) {
            for (int y = 0; y < forest.get(0).size(); y++) {
                int height = forest.get(x).get(y);
                if (height > 1) {
                    list.add(new Node(x, y, height));
                }
            }
        }
        
        Collections.sort(list, (o1, o2) -> o1.height - o2.height); // sort ascending order based on height
        
        int res = 0, sx = 0, sy = 0;
        // move from current position to next tree to cut
        for (int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            int ex = n.x, ey = n.y;
            
            if (ex == sx && ey == sy) continue;   // in case current pos is end pos
            
            int steps = bfs(forest, sx, sy, ex, ey);
            
            if (steps == -1) return -1;
            
            res += steps;
            
            sx = ex;
            sy = ey;
        }
        
        return res;
        
    }
    
    // min steps to go from start pos to end pos based on current map
    private int bfs(List<List<Integer>> forest, int sx, int sy, int ex, int ey) {
        int m = forest.size();
        int n = forest.get(0).size();
       
        Queue<Node> q = new LinkedList();  
        q.offer(new Node(sx, sy, 0));
        
        int[] dir = {-1, 0, 1, 0, -1};
        boolean[][] visited = new boolean[m][n];
        visited[sx][sy] = true;
        
        int steps = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size != 0) {
                Node node = q.poll();
                int x = node.x, y = node.y;

                for (int i = 0; i < 4; i++) {
                    int newx = x + dir[i];
                    int newy = y + dir[i + 1];

                    if (newx >= m || newy >= n || newx < 0 || newy < 0 || forest.get(newx).get(newy) == 0 || visited[newx][newy]) continue;
                    if (newx == ex && newy == ey) return steps;

                    q.offer(new Node(newx, newy, 0));   // TO DO: optimize '0' here to size

                    visited[newx][newy] = true;
                }
                size--;
            }
            
            steps += 1;
        }
        
        // impossible to reach
        return -1;
    }
    
    class Node {
        int x, y, height;
        Node(int x, int y, int height) {
            this.x = x; this.y = y; this.height = height;
        }
    }
}
