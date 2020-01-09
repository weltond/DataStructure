## [654. Sparse Matrix Multiplication](https://www.lintcode.com/problem/valid-word-abbreviation/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given two Sparse Matrix A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.


Example1

```
Input: 
[[1,0,0],[-1,0,3]]
[[7,0,0],[0,0,0],[0,0,1]]
Output:
[[7,0,0],[-7,0,3]]
Explanation:
A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
```

Example2

```
Input:
[[1,0],[0,1]]
[[0,1],[1,0]]
Output:
[[0,1],[1,0]]
```

## Answer
### Method 2 

```java
public class Solution {
    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = B[0].length;
        int t = A[0].length;
        int[][] C = new int[n][m];

        List<List<Integer>> col = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            col.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                if (B[i][j] != 0) {
                    col.get(i).add(j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < t; k++) {
                if (A[i][k] == 0) {
                    continue;
                }
                for (int j: col.get(k)) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
```

### Method 1 - :rabbit: 321ms (83.80%)

```java
public class Solution {
    /**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // write your code here
        int m = A.length, n = A[0].length, l = B[0].length;
        int[][] res = new int[m][l];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) continue;
                for (int k = 0; k < l; k++)
                    res[i][k] += A[i][j] * B[j][k];
            }
        }
        
        return res;
    }
}
```
