## [1428. Leftmost Column with at Least a One](https://leetcode.com/problems/leftmost-column-with-at-least-a-one/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.

You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:

- BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
- BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.

Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.

 

Example 1:

<img width="44" alt="image" src="https://user-images.githubusercontent.com/9000286/154827687-47615ebd-8dff-41f6-aec8-1fc8650ae17d.png">

```
Input: mat = [[0,0],[1,1]]
Output: 0
```

Example 2:

<img width="43" alt="image" src="https://user-images.githubusercontent.com/9000286/154827691-7cc4a6f4-c66a-4137-8959-47621f3d8de1.png">

```
Input: mat = [[0,0],[0,1]]
Output: 1
```

Example 3:

```
Input: mat = [[0,0],[0,0]]
Output: -1
``` 

Constraints:

- rows == mat.length
- cols == mat[i].length
- 1 <= rows, cols <= 100
- mat[i][j] is either 0 or 1.
- mat[i] is sorted in non-decreasing order.

## Answers
### Method 1 - Binary Search - 🚀 0ms

```java
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> ds = binaryMatrix.dimensions();
        int m = ds.get(0), n = ds.get(1);
        
        int res = n;
        
        for (int i = 0; i < m; i++) {
            int colFirstOne = findFirstOne(binaryMatrix, i, 0, n - 1);
            
            // only update when the row has 1
            if (colFirstOne != -1) {
                res = Math.min(res, colFirstOne);
            }
        }
        
        return res == n ? -1 : res;
    }
    
    private int findFirstOne(BinaryMatrix bm, int row, int l, int r) {
        while (l < r - 1) {
            int m = (l + r) / 2;
            
            if (bm.get(row, m) == 0) {
                l = m + 1;  // usually it should be l = m.
            } else {
                r = m;
            }
        }

        int valL = bm.get(row, l), valR = bm.get(row, r);
        
        // check valR in case there is no 1 in the row
        return valR == 0 ? -1 : valL == 1 ? l : r;
    }
}
```
