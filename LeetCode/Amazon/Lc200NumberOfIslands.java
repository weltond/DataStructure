class Solution {
    int m = 0, n = 0;
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        n = grid.length;
        m = grid[0].length;
        
        //return dfs(grid);
        return unionFind(grid);
    }    
    
    /*Method 1 : DFS*/
    
    private int dfs (char[][] grid) {
        boolean[][] visited = new boolean[n][m];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfsUtil(grid, visited, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    private void dfsUtil(char[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || visited[i][j] || grid[i][j] == '0') {
            return;
        }
        
        visited[i][j] = true;
        int[] row = new int[]{-1, 0, 1, 0};
        int[] col = new int[]{0, 1, 0, -1};
        
        for (int k = 0; k < 4; k++) {
            dfsUtil(grid, visited, i + row[k], j + col[k]);
        }
    }
    
    /*=============================================*/
    
    /*Method 2: Union Find*/
    private int unionFind(char[][] grid) {
        UnionFind uf = new UnionFind(n * m);
        boolean[][] visited = new boolean[n][m];
        // check for neighbors and unites the indexes if both are 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    unionUtil(grid, i, j, visited, uf);
                }
            }
        }
        
        // array to note down freq of each set
        int[] c = new int[n*m];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    int x = uf.find(i * m + j);
                    // if freq is 0, increse cnt
                    if (c[x] == 0) {
                        cnt++;
                        c[x]++;
                    } else {
                        c[x]++;
                    }
                }
            }
        }
        
        return cnt;
    }
    
    private void unionUtil(char[][] grid, int i, int j, boolean[][] visited, UnionFind uf) {
        visited[i][j] = true;
        
        if (i + 1 < n && grid[i+1][j] == '1') 
            uf.union(i * m + j, (i + 1) * m + j);
        if (i - 1 >= 0 && grid[i-1][j] == '1')
            uf.union(i * m + j, (i - 1) * m + j);
        if (j + 1 < m && grid[i][j + 1] == '1')
            uf.union(i * m + j, i * m + j + 1);
        if (j - 1 >= 0 && grid[i][j - 1] == '1')
            uf.union(i * m + j, i * m + j - 1);
    }
}

class UnionFind {
    int[] rank, parent;
    int n;
    
    public UnionFind(int n) {
        this.n = n;
        rank = new int[n];
        parent = new int[n];
        makeSet();
    }
    
    void makeSet() {
        for (int i = 0; i < this.n; i++) {
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
        int xr = find(x);
        int yr = find(y);
        
        if (xr == yr) return;
        
        if (rank[xr] < rank[yr]) parent[xr] = yr;
        else if (rank[xr] > rank[yr]) parent[yr] = xr;
        else {
            parent[yr] = xr;
            rank[xr] += 1;
        }
    }
}
