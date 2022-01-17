## [498. Diagonal Traverse](https://leetcode.com/problems/diagonal-traverse/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

![image](https://user-images.githubusercontent.com/9000286/149716911-bc7a8f04-2c76-4d14-bf6d-b7a9d6eccf99.png)
 

Example 1:

```
Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
```

Example 2:
```
Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
``` 

**Constraints**:

- m == mat.length
- n == mat[i].length
- 1 <= m, n <= 104
- 1 <= m * n <= 104
- -105 <= mat[i][j] <= 105

## Answers
### Method 1 - Simulation - 3ms (69.06%) 🐰

Note the order when `col` or `row` exceeds bound.

```java
class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        
        int[] res = new int[n * m];
        int[][] dir = {{-1, 1}, {1, -1}};   // dir[0] is upper-right, dir[1] is bot-left
        
        int row = 0, col = 0, idx = 0;
        
        for (int i = 0; i < n * m; i++) {
            res[i] = mat[row][col];
            
            row += dir[idx][0];
            col += dir[idx][1];
            
            // continue if row and col is not out of bound
            if (row >= 0 && col >= 0 && row < n && col < m) continue;
            
            // change direction since row or col is out of bound
            idx = 1 - idx;
            
            /*Note: the order matters*/
            /*  If exceeds up-right corner (0, 2) -> (-1, 3), 
                we don't want to set row to 0 first, otherwise, 
                next row will be set to row += 2 => 2, while it should be 1
                
                So `right` first, `top` follow. 
            */
            // right
            if (col >= m) {
                col = m - 1;
                row += 2;
            }
            
            // top
            if (row < 0) {
                row = 0;
            }
            
            /*Note: the order of below  matters too*/
            /*  if [[1,2],[3,4]], and exceeds bot-left corner (1, 1) -> (2, -1).
                we don't want to set col to 0 first, otherwise, 
                next col will be set to col += 2 => 2, while it should be 1.
            */
            // bot
            if (row >= n) {
                row = n - 1;
                col += 2;
            }
            
            // left
            if (col < 0) {
                col = 0;
            }
            
        }
        
        return res;
    }
}
```
