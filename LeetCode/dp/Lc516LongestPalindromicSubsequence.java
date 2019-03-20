// https://leetcode.com/problems/longest-palindromic-subsequence/

class Solution {
    // ========= Method 1: Naive DP ==========
    // 25ms
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int n = s.length();
        // dp[i][j] is the length of the longest subsequence in s[i..j]
        // base case: dp[i][i] = 1, dp[i][i-1] = 0
        int[][] dp = new int[n][n];
        // Induction rule: dp[i][j] = 2 + dp[i + 1][j - 1]              if s[i] == s[j]
        //                          = max(dp[i + 1][j], dp[i][j - 1])   if s[i] != s[j]
        for (int i = n - 1 ; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (j == i) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) ? (2 + dp[i + 1][j - 1]) : Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}
