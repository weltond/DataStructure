## [Question](https://leetcode.com/problems/triangle/)

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
```
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
```
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

- Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

## Answer
### Method 1 - DFS + Memoization
:rocket: 1ms (99.84%)
- Time:  O(n^2)
- Space: O(n^2)
```java
class Solution {
    // ======== Method 1: DFS =========
    // 1ms
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        
        int row = triangle.size();
        int[][] memo = new int[row][row];
        
        return dfs(triangle, 0, 0, row, memo);
    }
    
    private int dfs(List<List<Integer>> t, int x, int y, int row, int[][] memo) {
        if (x >= row || y >= row || x < 0 || y < 0) return Integer.MAX_VALUE;
        else if (x == row - 1) {
            memo[x][y] = t.get(x).get(y);    
            return memo[x][y];
        }

        if (memo[x][y] != 0) return memo[x][y];

        int mid = dfs(t, x + 1, y, row, memo);
        int right = dfs(t, x + 1, y + 1, row, memo);
        
        int ret = t.get(x).get(y) + Math.min(right, mid);
        memo[x][y] = ret;
        
        return ret;
    }
}
```
