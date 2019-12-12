## [427. Construct Quad Tree](https://leetcode.com/problems/construct-quad-tree/)

We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false. The root node represents the whole grid. For each node, it will be subdivided into four children nodes **until the values in the region it represents are all the same**.

Each node has another two boolean attributes : `isLeaf` and `val`. `isLeaf` is true if and only if the node is a leaf node. The `val` attribute for a leaf node contains the value of the region it represents.

Your task is to use a quad tree to represent a given grid. The following example may help you understand the problem better:

Given the 8 x 8 grid below, we want to construct the corresponding quad tree:



It can be divided according to the definition above:



 

The corresponding quad tree should be as following, where each node is represented as a `(isLeaf, val)` pair.

For the non-leaf nodes, `val` can be arbitrary, so it is represented as `*`.



Note:

- N is less than 1000 and guaranteened to be a power of 2.
- If you want to know more about the quad tree, you can refer to its wiki.


## Answer
### Method 1 - DFS - :rocket: 1ms (99.75%)

```java
/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/
class Solution {
    public Node construct(int[][] grid) {
        return dfs(grid, grid.length, 0, 0);
    }
    
    private Node dfs(int[][] grid, int size, int x, int y) {
        if (size == 1) return new Node(grid[x][y] == 1, true, null, null, null, null);
        
        Node root = new Node();
        
        // if whole is same, just return
        if (checkSame(grid, x, y, size, grid[x][y])) {
            root.val = grid[x][y] == 1;
            root.isLeaf = true;
            return root;
        }
        
        boolean isTLSame = checkSame(grid, x, y, size / 2, grid[x][y]);
        boolean isTRSame = checkSame(grid, x, y + size / 2, size / 2, grid[x][y + size / 2]);
        boolean isBLSame = checkSame(grid, x + size / 2, y, size / 2, grid[x + size / 2][y]);
        boolean isBRSame = checkSame(grid, x + size / 2, y + size / 2, size / 2, grid[x + size / 2][y + size / 2]);
        
        Node tl, tr, bl, br;
        
        root.topLeft = getRest(grid, x, y, size, isTLSame);
        root.topRight = getRest(grid, x, y + size / 2, size, isTRSame);
        root.bottomLeft = getRest(grid, x + size / 2, y, size, isBLSame);
        root.bottomRight = getRest(grid, x + size / 2, y + size / 2, size, isBRSame);
        
        return root;
    }
    
    private Node getRest(int[][] grid, int x, int y, int size, boolean check) {
        Node n = null;
        if (check) {
            n = new Node(grid[x][y] == 1, true, null, null, null, null);
        } else {
            n = dfs(grid, size / 2, x, y);
        }
        
        return n;
    }
    
    private boolean checkSame(int[][] grid, int sx, int sy, int size, int val) {
        for (int i = sx; i < size + sx; i++) {
            for (int j = sy; j < size + sy; j++) {
                if (grid[i][j] != val) return false;
            }
        }
        
        return true;
    }
}
```
