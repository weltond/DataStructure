// https://leetcode.com/problems/longest-palindromic-substring/

class Solution {
    // ======== Method 3 : Manacher O(n) =========
    //
    // TO DO...
    
    // ======== Method 2 : DP ==========
    // 36ms
    // dp[i][j] is whether s[i..j] is palidrome or not
    // dp[i][j] = true if s[i] = s[j] && dp[i+1][j-1]
    // base case: dp[i][i] = true; dp[i][i+1] = (s[i] == s[i+1])
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        
        int start = 0, end = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // induction rule
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                // update result
                if (dp[i][j] && j - i >= end - start) {
                    end = j;
                    start = i;
                }
            }
        }
        
        return s.substring(start, end + 1);
    }
    
    // ======== Method 1 : Recursion ==========
    // 
    // almost same as dp. But from top to down.
    // TO DO.......

}
