// https://leetcode.com/problems/redundant-connection/


/**
Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
*/

class Solution {
    // ================ Method 1: Union Find ==============
    // 1ms (94.16%)
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) return new int[]{};
        
        UF uf = new UF(edges.length);
        
        int[] res = new int[2];
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0] - 1, y = edges[i][1] - 1;
            int rx = uf.find(x), ry = uf.find(y);
            
            if (rx == ry) {
                res[0] = x + 1;
                res[1] = y + 1;
            } else {
                uf.union(rx, ry);
            }
        }
        
        return res;
    }
}

class UF {
    int[] parent;
    UF(int size) {
        parent = new int[size];
        
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    public void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx == ry) return;
        
        parent[y] = x;
    } 
}
