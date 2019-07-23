## [10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)

Given an input string (s) and a pattern (p), implement regular expression matching with support for `'.'` and `'*'`.
```
'.' Matches any single character.
'*' Matches zero or more of the preceding element.
```
The matching should cover the entire input string (not partial).

Note:

- `s` could be empty and contains only lowercase letters a-z.
- `p` could be empty and contains only lowercase letters a-z, and characters like `.` or `*`.

Example 1:
```
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```
Example 2:
```
Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
```
Example 3:
```
Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
```
Example 4:
```
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
```
Example 5:
```
Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
```

## Answer
### Method 2 - DP
#### Approach 2 - Bottom-up :rocket: 2ms (96.17%)
```java
class Solution {
    public boolean isMatch(String s, String p) {
        int lens = s.length(), lenp = p.length();
        //dp[i][j] represents if s[i...] matches p[j...]
        boolean[][] dp = new boolean[lens + 1][lenp + 1];
        dp[lens][lenp] = true;
        
        // dp[i][j] = dp[i+1][j+1] if s[i]==p[j] || p[j]='.'
        //          = dp[i][j+2] if p[j+1]='*' (zero match)
        //          = dp[i+1][j] if p[j+1]='*' and s[i]==p[j]||p[j]='.' (1 match)
        
        for (int i = lens; i >= 0; i--) {   // i starts from lens! Because if compare last char of s, we would need to compare i+1th with pattern
            for (int j = lenp - 1; j>= 0; j--) {
                boolean firstMatch = i < lens && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');  // should verify i < lens first!
                
                if (j + 1 < lenp && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || (firstMatch && dp[i + 1][j]);
                } else {
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }
        
        return dp[0][0];
    }
}

```
#### Approach 1 - DFS + Memo - :rocket: 2ms (96.17%)
```java
class Solution {
    // ======= Method 2 : DP ==========
    // 2ms (96.17%)
    Boolean[][] memo;
    public boolean isMatch(String s, String p) {
        memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p);
    }
    
    private boolean dp(int i, int j, String s, String p) {
        if (memo[i][j] != null) return memo[i][j];
        
        boolean ans = false;
        
        if (j == p.length()) {
            ans = i == s.length();
        } else {
            boolean firstMatch = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));
            
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                ans = dp(i, j + 2, s, p) || (firstMatch && dp(i + 1, j, s, p));
            } else {
                ans = firstMatch && dp(i + 1, j + 1, s, p);
            }
        }
        
        memo[i][j] = ans;
        return ans;
    }
}
```
### Method 1 - DFS 
#### Approach 2 :turtle: 58ms (14.34%)
```java
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        
        boolean firstMatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        
        // "" , "c*c*"
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }
}
```
#### Approach 1 :rabbit: 3ms (50.07%)
```java
class Solution {
    // ============ DFS ===========
    // 3ms (50.70%)
    public boolean isMatch(String s, String p) {
        return dfs(s, p, s.length() - 1, p.length() - 1);
    }
    
    private boolean dfs(String s, String p, int s1, int p1) {
        if (s1 == -1) {
            // "" "c*c*"
            for (; p1 >= 1; p1 -= 2) {
                if (p.charAt(p1) != '*') return false;
            }
            return p1 == -1 ;
        }
        if (p1 == -1) {
            return s1 == -1;
        }
        
        boolean ret = false;
        char sc = s.charAt(s1), pc = p.charAt(p1);
        if (sc == pc || pc == '.') {
            return dfs(s, p, s1 - 1, p1 - 1);
        }
        
        if (pc == '*' && p1 != 0) {
            char prev = p.charAt(p1 - 1);
            int len = 0, tmp = s1;
            if (prev == '.') len = s1 + 1;
            else {
                // calc how many mathced char in total
                while (tmp >= 0 && s.charAt(tmp) == prev) {
                    len++;
                    tmp--;
                }
            }
            
            // recurse for every possible matching length
            for (int i = 0; i <= len; i++) {
                ret = ret || dfs(s, p, s1 - i, p1 - 2);
            }
        }
        
        return ret;
    }
}
```
