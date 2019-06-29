## [85. Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle/)

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:
```
Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
```
## Answer
### Method 1 - Array - :rabbit: 25ms (47.18%)
**Make use of [84. Largest Rectangle in Histogram](https://github.com/weltond/DataStructure/blob/master/LeetCode/array/84-largest-rectangle-in-histogram.md)**
```java
class Solution {
    // ===== Method 1: Make use of LC84 ======
    // 25ms (47.18%)
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int res = 0;

        int[] histogram = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') histogram[j]++;    // get hight for each row (vertical view)
                else histogram[j] = 0;  // don't forget to reset
            }
            
            res = Math.max(res, largestPerRow(histogram));
        }
        
        return res;
    }
    
    private int largestPerRow(int[] h) {
        int res = 0;
        
        for (int i = 0; i < h.length; i++) {
            if (i + 1 < h.length && h[i] <= h[i + 1]) continue;
            
            int minVal = h[i];
            for (int j = i; j >= 0; j--) {
                minVal = Math.min(minVal, h[j]);
                res = Math.max(res, (i - j + 1) * minVal);
            }
        }
        return res;
    }
}
```
