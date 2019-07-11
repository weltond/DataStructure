## [718. Maximum Length of Repeated Subarray](https://leetcode.com/problems/maximum-length-of-repeated-subarray/)

Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
```
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].
```

Note:

- `1 <= len(A), len(B) <= 1000`
- `0 <= A[i], B[i] < 100`

## Answer
### Method 1 - DP - :rabbit: 47ms (38.19%)
```java
class Solution {
    // ========= Method 1: DP ===========
    // 47ms (38.19%)
    public int findLength(int[] a, int[] b) {
        int[][] dp = new int[a.length + 1][b.length + 1];
        
        int ans = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = b.length - 1; j >= 0; j--) {
                if (a[i] == b[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        
        return ans;
    }
}
```
