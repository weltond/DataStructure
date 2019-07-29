## [Question](https://leetcode.com/problems/spiral-matrix-ii/)

Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example 1:

```
Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
```

## Answer
**Just like** [54-Spiral-Matrix](https://github.com/weltond/DataStructure/blob/master/LeetCode/array/54-Spiral-Matrix.md) 
### Method 2 - Simulation - :rocket: 0ms
```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int cnt = 1, r = 0, c = 0;
        int dir = 0;
        int[] d1 = {0, 1, 0, -1};
        int[] d2 = {1, 0, -1, 0};
        boolean[][] seen = new boolean[n][n];
        
        for (int i = 0, size = n * n; i < size; i++) {
            arr[r][c] = cnt++;
            seen[r][c] = true;
            
            int nr = r + d1[dir];
            int nc = c + d2[dir];
            
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && !seen[nr][nc]) {
                r = nr;
                c = nc;
            } else {
                dir = (dir + 1) % 4;
                r += d1[dir];
                c += d2[dir];
            }
        }
        
        return arr;
    }
}
```
### Method 1 - Layer By Layer
:rocket: 0ms 
- Time: O(m * n)
- Space: O(m * n)
```java
class Solution {
    // Method 1: Layer by Layer
    // 0ms
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        
        int r1 = 0, r2 = n - 1;
        int c1 = 0, c2 = n - 1;
        int cnt = 1;
        
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) res[r1][c] = cnt++;
            for (int r = r1 + 1; r <= r2; r++) res[r][c2] = cnt++;
            
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) res[r2][c] = cnt++;
                for (int r = r2; r > r1; r--) res[r][c1] = cnt++;
            }
            
            r1++;
            r2--;
            c1++;
            c2--;
        }
        
        return res;
    }
}
```
