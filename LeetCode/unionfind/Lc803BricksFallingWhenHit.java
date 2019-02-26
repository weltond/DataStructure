// https://leetcode.com/problems/bricks-falling-when-hit/
class Solution {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length;
        int n = grid[0].length;
        
        UnionFind uf = new UnionFind(m * n + 1);    // an extra to store 'top' (0-th) that the first row connected to.
        //1. Mark hits to '2'
        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) {
                grid[hit[0]][hit[1]] = 2;
            }
        }
        
        // 2. union all bricks, including union the bricks in first row to 'top'(0-th) 
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    unionAround(i, j, grid, uf);
                }
            }
        }
        
        // count remaining bricks after hits
        int count = uf.rank[uf.find(0)];    // uf.find(0) = 0
        //System.out.println(uf.find(0) + ", " + count + " ; " + uf.rank[7]);
        //System.out.println("O: " + uf.find(0) + ", " + uf.find(1) + ", " + uf.find(5) + ", " + uf.find(7) + ", " + uf.find(3) + ", " + uf.find(6));
        
        int[] res = new int[hits.length];
        
        // 3.reverse traverse hits[][] to recover the grid. Reverse traverse here is because first hit has already been set to 2. 
        for (int i = hits.length - 1; i >= 0; i--) {
            int[] hit = hits[i];
            
            if (grid[hit[0]][hit[1]] == 2) {
                // find how many bricks connect to top through this brick
                unionAround(hit[0], hit[1], grid, uf);
                grid[hit[0]][hit[1]] = 1;
            }
            
            // originConnectedcount is how many bricks connect to 'top' after recovering the hit one
            int originConnectedcount = uf.rank[uf.find(0)];  // // uf.find(0) = 7

            res[i] = (originConnectedcount - count > 0) ? originConnectedcount - count - 1 : 0;
            
            count = originConnectedcount;
        }
        
        return res;
    }
    
    private void unionAround(int x, int y, int[][] grid, UnionFind uf) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dir = {0, -1, 0, 1, 0};
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i];
            int ny = y + dir[i + 1];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            
            if (grid[nx][ny] == 1) {
                uf.union(x * n + y + 1, nx * n + ny + 1);
                //System.out.println("UNION: " + (x * n + y + 1) + ", " + (nx * n + ny + 1));
            }
        }
        
        // connect with 'top' for the first row
        if (x == 0) {
            uf.union(x * n + y + 1, 0);
        }
        
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    
    public UnionFind(int len) {
        parent = new int[len];
        rank = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        
        return parent[x];
    }
    
    public void union(int x, int y) {
        int xr = find(x);
        int yr = find(y);
        
        if (xr == yr) return;
        
        // we don't do formal union here by calculating each rank
        // we just add x to y
        parent[xr] = yr;
        rank[yr] += rank[xr];
        
        // if (rank[xr] > rank[yr]) parent[xr] = yr;
        // else if (rank[xr] < rank[yr]) parent[yr] = xr;
        // else {
        //     parent[xr] = yr;
        //     rank[yr] += 1;
        //}
    }
}
