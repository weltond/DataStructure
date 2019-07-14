## [97. Interleaving String](https://leetcode.com/problems/interleaving-string/)

Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:
```
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
```
Example 2:
```
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
```

## Answer
### Method 2 - DFS + Memo - :rocket: 0ms
```java
class Solution {
    // =========== Method 2 : DFS + Memo ============
    // 0ms 
    int[][] memo;
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len3 != len1 + len2) return false;
        
        memo = new int[len1][len2];

        return dfs(s1, s2, s3, 0, 0, 0);
    }
    
    private boolean dfs(String s1, String s2, String s3, int l1, int l2, int l3) {
        if (l1 == s1.length()) {
            return s2.substring(l2).equals(s3.substring(l3));
        }
        if (l2 == s2.length()) {
            return s1.substring(l1).equals(s3.substring(l3));
        }
        
        if (memo[l1][l2] != 0) {
            return memo[l1][l2] == 1 ? true : false;
        }
        boolean res = false;
        if (s1.charAt(l1) == s3.charAt(l3) && dfs(s1, s2, s3, l1 + 1, l2, l3 + 1) || 
           s2.charAt(l2) == s3.charAt(l3) && dfs(s1, s2, s3, l1, l2 + 1, l3 + 1)) {
            res = true;
        }
        
        memo[l1][l2] = res ? 1 : 2;
        
        return res;
    }
}
```
### Method 1 - DP
**dp[i][j] represents is s1[0...i] and s2[0...j] can be formed to s3[0...i+j].
#### Approach 2 :turtle: 5ms (37.18%)
```java
class Solution {
    // ======== Method 1: DP =========
    // Approach 2: 5ms (37.18%)
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len3 != len1 + len2) return false;

        boolean[] dp = new boolean[len2 + 1];
        
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) dp[j] = true;
                else if (i == 0) {
                    dp[j] = (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[j - 1]);
                } else if (j == 0) {
                    dp[j] = (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[j]);
                } else {
                    char c = s3.charAt(i + j - 1);
                    boolean isS1Match = s1.charAt(i - 1) == c;
                    boolean isS2Match = s2.charAt(j - 1) == c;
                    dp[j] = (isS1Match && dp[j]) || (isS2Match && dp[j - 1]);
                }
            }
        }
        
        return dp[len2];
        
    }
}
```
#### Approach 1 :turtle: 6ms (25.05%)
```java
class Solution {
    // ======== Method 1: DP =========
    // Approach 1: 6ms (25.05%)
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len3 != len1 + len2) return false;
        
        // dp[i][j] represents is s1[0...i] and s2[0...j] can be formed to s3.
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) dp[i][j] = true;
                else if (i == 0) {
                    dp[i][j] = (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]);
                } else if (j == 0) {
                    dp[i][j] = (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]);
                } else {
                    char c = s3.charAt(i + j - 1);
                    boolean isS1Match = s1.charAt(i - 1) == c;
                    boolean isS2Match = s2.charAt(j - 1) == c;
                    dp[i][j] = (isS1Match && dp[i - 1][j]) || (isS2Match && dp[i][j - 1]);
                }
            }
        }
        
        return dp[len1][len2];
        
    }
}
```
