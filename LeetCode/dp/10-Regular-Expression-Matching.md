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
