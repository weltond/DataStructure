// https://leetcode.com/problems/palindromic-substrings/

/**
Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
*/
class Solution {
    
    // ========= Method 3: Manacher =========
    // TO DO...
    
    // ========= Method 2: Expand Around Center space O(1) =========
    // TO DO...
    
    // ========= Method 1: DP ============
    // 9ms
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int size = s.length();
        boolean[][] dp = new boolean[size][size];
        int cnt = 0;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                
                if (dp[i][j]) cnt++;
            }
        }
        
        return cnt;
    }
}
