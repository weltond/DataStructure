## [1027. Longest Arithmetic Sequence](https://leetcode.com/problems/longest-arithmetic-sequence/)

Given an array A of integers, return the length of the longest arithmetic subsequence in A.

Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).

Example 1:
```
Input: [3,6,9,12]
Output: 4
Explanation: 
The whole array is an arithmetic sequence with steps of length = 3.
```
Example 2:
```
Input: [9,4,7,2,10]
Output: 3
Explanation: 
The longest arithmetic subsequence is [4,7,10].
```
Example 3:
```
Input: [20,1,15,3,10,5,8]
Output: 4
Explanation: 
The longest arithmetic subsequence is [20,15,10,5].
``` 

Note:

- `2 <= A.length <= 2000`
- `0 <= A[i] <= 10000`
## Answer
### Method 1 - DP

- Need to maintain max current `dp[i][diff]`. Say `[6,3,6,9,12]`. Because we iterate `j` from current `i-1` to `0`.

#### Approach 2 :rabbit: 363ms (50.82%)
```java
class Solution {
    // =========== Method 1: DP ============
    // 363ms (50.82%)
    public int longestArithSeqLength(int[] a) {
        if (a == null) return 0;
        
        HashMap<Integer, Integer>[] dp = new HashMap[a.length];
        dp[0] = new HashMap();
        int res = 1;
        for (int i = 1; i < a.length; i++) {
            dp[i] = new HashMap();
            for (int j = i - 1; j >= 0; j--) {
                int diff = a[i] - a[j];
                int prev = dp[j].getOrDefault(diff, 0) + 1;
                int cur = Math.max(dp[i].getOrDefault(diff, 0), prev);
                
                dp[i].put(diff, cur);
                
                res = Math.max(res, cur);
            }
        }

        return res + 1;
    }
}
```
#### Approach 1 :rocket: 77ms (91.66%)
```java
class Solution {
    // =========== Method 1: DP ============
    // 77ms (91.66%)
    public int longestArithSeqLength(int[] a) {
        if (a == null) return 0;
        int[][] dp = new int[a.length][20001];
        
        int res = 1;
        for (int i = 1; i < a.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int diff = a[i] - a[j] + 10000;
                dp[i][diff] = Math.max(dp[i][diff], dp[j][diff] + 1);   // DON'T forget to compare
                
                res = Math.max(res, dp[i][diff]);
            }
        }

        return res + 1;
    }
}
```
