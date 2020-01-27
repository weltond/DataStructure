// 
## [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

    
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
## Answer
### Method 1 - Two Pointer - :rabbit: 330ms (70%)

```java
public class Solution {
    /**
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        // write your code here
        if (word == null || word.length() == 0) return false;
        
        int i = 0, j = 0;
        int lenw = word.length(), lena = abbr.length();
        
        while (i < lena) {
            char c = abbr.charAt(i);
            if (c > '0' && c <= '9') {     // ignore num start with `0`
                int res = 0;
                while (i < lena && Character.isDigit(abbr.charAt(i))) {
                    res = res * 10 + abbr.charAt(i) - '0';
                    i++;
                }
                j = j + res;
            } else {
                if (j >= lenw || c != word.charAt(j++)) {
                    //System.out.println(i+","+j);
                    return false;
                }
                i++;
            }
        }
        
        return i == lena && j == lenw;
    }
}
```
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
