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
### Method 3 - DP -
```java

```
### Method 2 - DFS + Memo -
```java

```
### Method 1 - 
```java

```
### Method 0 - DFS 
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
