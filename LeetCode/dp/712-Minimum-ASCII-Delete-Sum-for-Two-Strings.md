## [712. Minimum ASCII Delete Sum for Two Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)

Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:
```
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
```
Example 2:
```
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
```
Note:

- 0 < s1.length, s2.length <= 1000.
- All elements of each string will have an ASCII value in [97, 122].

## Answer
### Method 1 - DP - :rabbit: 12ms (79.42%)
```java
class Solution {
    // ========== Method 1: DP ===========
    // 12ms (79.42%)
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        
        // dp[i][j] is the answer for input s1[i:] and s2[j:]
        int[][] dp = new int[len1 + 1][len2 + 1];
        
        // base case
        for (int i = len2 - 1; i >= 0; i--) {
            dp[len1][i] = s2.charAt(i) - 'a' + 97 + dp[len1][i + 1];
        }

        for (int i = len1 - 1; i >= 0; i--) {
            dp[i][len2] = s1.charAt(i) - 'a' + 97 + dp[i + 1][len2];
        }
        
        // induction rule:
        // 1) if s1[i] = s2[j], dp[i][j] = dp[i+1][j+1]
        // 2) else, dp[i][j] = min(s1[i]+dp[i+1][j], s2[j]+dp[i][j+1])  // min(delete s1[i], delete s2[j])
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                char c1 = s1.charAt(i), c2 = s2.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    int delS1 = c1 - 'a' + 97 + dp[i + 1][j];
                    int delS2 = c2 - 'a' + 97 + dp[i][j + 1];
                    dp[i][j] = Math.min(delS1, delS2);
                }
            }
        }
        
        return dp[0][0];
        
    }
}
```
