## [44. Wildcard Matching](https://leetcode.com/problems/wildcard-matching/)

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for `'?'` and `'*'`.

`'?'` Matches any single character.
`'*'` Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

- s could be empty and contains only lowercase letters `a-z`.
- p could be empty and contains only lowercase letters `a-z`, and characters like `?` or `*`.

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
p = "*"
Output: true
Explanation: '*' matches any sequence.
```
Example 3:
```
Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
```
Example 4:
```
Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
```
Example 5:
```
Input:
s = "acdcb"
p = "a*c?b"
Output: false
```

## Answer
### Method 2 - DP -
```java

```
### Method 1 - DFS + Memo
#### Approach 4 - :rocket: 3ms (81.87%)
```java
class Solution {
    // ===== DFS =====
    // 3ms (81.87%)
    public boolean isMatch(String s, String p) {
        return dfs(s, p, 0, 0) > 1;
    }
    
    private int dfs(String s, String p, int i, int j) {
        // if matched, return 2
        if (i == s.length() && j == p.length()) return 2;
        // if s at the end but p doesn't match, the rest of index after current j won't match either. return 0.
        if (i == s.length() && p.charAt(j) != '*') return 0;
        // if p at the end, s doesn't match. return 1
        if (j == p.length()) return 1;
        
        if (p.charAt(j) == '*') {
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                return dfs(s, p, i, j + 1);
            }
            
            for (int k = 0, len = s.length(); k <= len - i; k++) {
                int res = dfs(s, p, i + k, j + 1);
                // If res = 0 or 2, based on definition, we just return, no need to furthur investigate the rest of string p.
                // because s has been at the end.
                if (res == 0 || res == 2) return res;
            }
            
        } else if (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
            return dfs(s, p, i + 1, j + 1);
        }
        
        // if current doesn't match, return 1.
        return 1;
    }
}
```
#### Appraoch 3 - :turtle: 43ms (10.03%)
```java
class Solution {
    Boolean[][] memo;
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        memo = new Boolean[s.length() + 1][p.length() + 1];
        return dfs(s, p, 0, 0);
    }
    
    private boolean dfs(String s, String p, int i, int j) {
        if (memo[i][j] != null) return memo[i][j];
        
        if (j == p.length() && i == s.length()) {
            memo[i][j] = true;
            return memo[i][j];
        }
        if (i == s.length() && p.charAt(j) != '*') {
            int plen = p.length();
            for (int k = j; k < plen; k++) memo[i][k] = false;

            return memo[i][j];
        }
        
        if (j == p.length()) {
            memo[i][j] = false;
            return memo[i][j];
        }

        char cp = p.charAt(j);
        boolean ans = false;
        
        // verify cp first because i might be greater than s.length().
        if (cp == '*') {
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                ans = dfs(s, p, i, j + 1);
            }
            
            for (int k = 0, len = s.length(); k <= len - i; k++) {
                if (dfs(s, p, i + k, j + 1)) {
                    ans = true;
                    break;
                }
            }
        } else if (i < s.length() && (cp == s.charAt(i) || cp == '?')) {
            ans = dfs(s, p, i + 1, j + 1);
        }
        
        memo[i][j] = ans;
        
        return ans;
    }
}
```
#### Approach 2 - :turtle: 1002ms (5.04%)
- pruning here is once `s` reaches end, the following `j` values should be the same, either `true` or `false`.
```java
class Solution {
    Boolean[][] memo;
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        memo = new Boolean[s.length() + 1][p.length() + 1];
        return dfs(s, p, 0, 0);
    }
    
    private boolean dfs(String s, String p, int i, int j) {
        if (memo[i][j] != null) return memo[i][j];
        
        if (j == p.length()) {
            memo[i][j] = i == s.length();
            return memo[i][j];
        }
        if (i == s.length()) {
            int plen = p.length();
            boolean flag = false;
            for (int t = j; t < plen; t++) {
                if (p.charAt(t) != '*') {
                    flag = true;
                    // PRUNING!
                    for (int k = j; k < plen; k++) memo[i][k] = false;
                }
            }
            
            if (!flag) 
                // PRUNING!
                for (int t = j; t < plen; t++) 
                    memo[i][t] = true;
            return memo[i][j];
        }

        char cp = p.charAt(j), cs = s.charAt(i);
        boolean ans = false;
        
        if (cp == cs || cp == '?') {
            ans = dfs(s, p, i + 1, j + 1);
        } else if (cp == '*') {
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                ans = dfs(s, p, i, j + 1);
            }
            
            for (int k = 0, len = s.length(); k <= len - i; k++) {
                if (dfs(s, p, i + k, j + 1)) {
                    ans = true;
                    break;
                }
            }
        }
        
        memo[i][j] = ans;
        
        return ans;
    }
}
```
#### Approach 1 - TLE (1714 / 1809 test cases passed.)
TLE:
`"baaabbabbbaabbbbbbabbbaaabbaabbbbbaaaabbbbbabaaaaabbabbaabaaababaabaaabaaaabbabbbaabbbbbaababbbabaaabaabaaabbbaababaaabaaabaaaabbabaabbbabababbbbabbaaababbabbaabbaabbbbabaaabbababbabababbaabaabbaaabbba"`
`"*b*ab*bb***abba*a**ab***b*aaa*a*b****a*b*bb**b**ab*ba**bb*bb*baab****bab*bbb**a*a*aab*b****b**ba**abba"`
```java
class Solution {
    Boolean[][] memo;
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        memo = new Boolean[s.length() + 1][p.length() + 1];
        return dfs(s, p, 0, 0);
    }
    
    private boolean dfs(String s, String p, int i, int j) {
        if (memo[i][j] != null) return memo[i][j];
        
        if (j == p.length()) {
            return i == s.length();
        }
        if (i == s.length()) {
            for(int len = p.length(); j < len; j++) {
                if (p.charAt(j) != '*') return false;
            }
            return true;
        }

        char cp = p.charAt(j), cs = s.charAt(i);
        boolean ans = false;
        
        if (cp == cs || cp == '?') {
            ans = dfs(s, p, i + 1, j + 1);
        } else if (cp == '*') {
            List<Integer> l = new ArrayList();
            int plen = p.length();
            for (int t = i, len = s.length(); t < len; t++) {
                // find next non '*' char
                while (j < plen && p.charAt(j) == '*') j++;
                // add matched index to list
                if (j < plen && (s.charAt(t) == p.charAt(j) || p.charAt(j) == '?')) l.add(t);
            }

            if (j == p.length()) {
                ans = true;
            } else {
                // recurse for every possible index
                for (int n : l) {
                    // if (dfs(s, p, n + 1, j + 2))
                    //     return true;
                    ans = ans || dfs(s, p, n + 1, j + 1);
                }
            }
                
        }
        
        memo[i][j] = ans;
        
        return ans;
    }
}
```
