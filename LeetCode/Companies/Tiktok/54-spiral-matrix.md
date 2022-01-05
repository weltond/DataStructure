```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;

        int r1 = 0, r2 = m - 1, c1 = 0, c2 = n - 1;

        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) {
                res.add(matrix[r1][c]);
            }

            for (int r = r1 + 1; r <= r2; r++) {
                res.add(matrix[r][c2]);
            }

            if (r1 < r2) {
                for (int c = c2 - 1; c >= c1 + 1; c--) {
                    res.add(matrix[r2][c]);
                }
            }
            
            if (c1 < c2) {
                for (int r = r2; r >= r1 + 1; r--) {
                    res.add(matrix[r][c1]);
                }
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
