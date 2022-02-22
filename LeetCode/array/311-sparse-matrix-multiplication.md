## [311. Sparse Matrix Multiplication](https://leetcode.com/problems/sparse-matrix-multiplication/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. You may assume that multiplication is always possible.

 

Example 1:

<img width="353" alt="image" src="https://user-images.githubusercontent.com/9000286/155051642-7e023257-e7a3-45f9-bc21-a8ade983305e.png">

```
Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
Output: [[7,0,0],[-7,0,3]]
```

Example 2:

```
Input: mat1 = [[0]], mat2 = [[0]]
Output: [[0]]
``` 

Constraints:

- m == mat1.length
- k == mat1[i].length == mat2.length
- n == mat2[i].length
- 1 <= m, n, k <= 100
- -100 <= mat1[i][j], mat2[i][j] <= 100

## Answers

### Method 1 - Sparse Matrix - 🚀 0ms
Time: O(mnk), Space: O(mk + kn)

<img width="398" alt="image" src="https://user-images.githubusercontent.com/9000286/155051787-25f95c50-01af-4cab-87f2-cbbd679bba8e.png">

```java
class Solution {
    /*
    Any element with index (row1, col1) of mat1 is multiplied with all the elements of col1 row of mat2
    */
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int k = mat1[0].length;
        int n = mat2[0].length;
        
        // Store the non-zero values of each matrix.
        List<List<int[]>> a = compress(mat1), b = compress(mat2);
        
        int[][] ans = new int[m][n];
        
        for (int r1 = 0; r1 < m; r1++) {
            for (int[] pair1 : a.get(r1)) {
                int val1 = pair1[0], c1 = pair1[1];
                
                // Multiply and add all non-zero elements of mat2
                // where the row is equal to col of current element of mat1.
                for (int[] pair2 : b.get(c1)) {
                    int val2 = pair2[0], c2 = pair2[1];
                    
                    ans[r1][c2] += val1 * val2;
                }
            }
        }
        
        return ans;
    }
    
    // store non-zero elements
    // <rows, (value, column)>
    private List<List<int[]>> compress(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        List<List<int[]>> res = new ArrayList();
        
        
        for (int r = 0; r < rows; r++) {
            List<int[]> row = new ArrayList();
            
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] != 0) {
                    row.add(new int[]{mat[r][c], c});
                }
            }
            
            res.add(row);
        }
        
        return res;
    }
}
```
