## [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```

Example 2:

```
Input: "cbbd"
Output: "bb"
```

## Answer
### Method 3 - Manacher

```java
// TO DO...
```

### Method 2 - Expand around center - :rabbit: 30ms (48.42%)

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int len = s.length(), start = 0, end = 0;
        
        for (int i = 0; i < len; i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);
            int maxLen = Math.max(len1, len2);
            
            if (maxLen > end - start) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }
        
        return s.substring(start, end + 1);
    }
    
    public int expand(String s, int left, int right) {
        int l = left, r = right, len = s.length();
        while (l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}
```

### Method 1 - DP - :rabbit: 29ms

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null) return "";
        int len = s.length(), begin = 0, res = 0;
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) ? (j <= i + 2 || dp[i + 1][j - 1]) : false;
                if (dp[i][j] && j - i + 1 > res) {
                    begin = i;
                    res = j - i + 1;
                }
            }
        }
        
        return s.substring(begin, begin + res);
    }
}
```

### Old Post

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
```
