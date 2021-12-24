// https://leetcode.com/problems/bricks-falling-when-hit/

// 23ms (51.27%)
class Solution {
    int[] dir = {0, 1, 0, -1, 0};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length, n = grid[0].length;
        UF uf = new UF(m * n + 1);
        int dummy = m * n;

        // 1. we hit grid first to get final status
        for (int[] hit : hits) {
            int x = hit[0], y = hit[1];
            if (grid[x][y] == 1) {
                grid[x][y] = 2;
            }
        }

        // 2. union grid. top row is connected to the dummy
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    internalUnion(grid, i, j, uf, dummy);
                }
            }
        }

        // for (int i = 0; i < m * n + 1; i++) {
        //     System.out.print(uf.find(i) + " ");
        // }

        //System.out.println();
        // 3. count remaining bricks for final status
        int countFinal = uf.weight[uf.find(dummy)];

        // 4. reverse traverse hits to recover the grid.
        int[] res = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];

            // find how many bricks were connect to top through this brick before hitting
            if (grid[x][y] == 2) {
                internalUnion(grid, x, y, uf, dummy);
                grid[x][y] = 1; // reset 
            }

            // for (int o = 0; o < m * n + 1; o++) {
            //     System.out.print(uf.find(o) + " ");
            // }

            int countBeforeHittingCurrent = uf.weight[uf.find(dummy)];
            res[i] = Math.max(0, countBeforeHittingCurrent - countFinal - 1);

            countFinal = countBeforeHittingCurrent;
        }

        return res;
    }

    private void internalUnion(int[][] grid, int x, int y, UF uf, int dummy) {
        int m = grid.length, n = grid[0].length;
        for (int k = 0; k < 4; k++) {
            int nx = x + dir[k], ny = y + dir[k + 1];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] != 1) continue;

            uf.union(x * n + y, nx * n + ny);
        }

        // connect with dummy for first row
        // IMPORTANT: must do this for step 4, in case top row brick is removed
        if (x == 0) {
            uf.union(dummy, y);
        }
    }
}

class UF {
    // 记录连通分量
    private int count;
    
    // 节点X的节点是parent[x]
    public int[] parent;
    
    // 记录树的权重
    public int[] weight;
    
    public UF(int n) {
        // 一开始互不连通
        this.count = n;
        weight = new int[n];
        
        // 父节点指针初始指向自己
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    /* 判断 p 和 q 是否互相连通 */
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        // 处于同一棵树上的节点，相互连通
        return rootP == rootQ;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        
        if (rootP == rootQ) return;
        
        // union two trees
        // smaller is connected under larger
        if (weight[rootP] >= weight[rootQ]) {
            parent[rootQ] = rootP;
            weight[rootP] += weight[rootQ];
        } else {
            parent[rootP] = rootQ;
            weight[rootQ] += weight[rootP];
        }
        
        count--;    //两个分量合二为一
    }
    
    // find x's root node
    public int find(int x) {
        while (parent[x] != x) {
            // 进行路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        
        return x;
    }
    
    public int count() {
        return this.count;
    }
}

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
