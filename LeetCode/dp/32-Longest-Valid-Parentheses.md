## [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/)

Given a string containing just the characters `'('` and `')'`, find the length of the longest valid (well-formed) parentheses substring.

Example 1:
```
Input: "(()"
Output: 2

Explanation: The longest valid parentheses substring is "()"
```
Example 2:
```
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
```

## Answer
### Method 3 - Stack

### Method 2 - DP - :rocket: 1ms (100%)
```java
class Solution {
    // ========= Method 2: DP ============
    // 1ms (100%)
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int len = s.length();
        // dp[i] represents the length of longest valid substring ending at i-th index.
        int[] dp = new int[len];
        
        // Two conditions:
        // 1. Before ')' is '('. dp[i]=dp[i-2]+2
        // 2. Before ')' is ')':
        //   1) we need to make sure that index i-dp[i-1]-1 is '(' to make sure '...((..))'
        //   2) if valid, then dp[i]=dp[i-1] + dp[i-dp[i-1]-2] + 2
        int ret = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == '(') continue;
            if (s.charAt(i - 1) == '(') {
                dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);
            } else if ((i - dp[i - 1] - 1) >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = 2 + dp[i - 1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
            }
            
            ret = Math.max(ret, dp[i]);
        }
        
        return ret;
    }
}
```
### Method 1 - Brute force - TLE (227 / 230 test cases passed.)
```java
class Solution {
    // ===== Method 1 : Brute Force =====
    // TLE (227 / 230 test cases passed.)
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int ret = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i + 2; j <= len; j += 2) {
                if (isValid(s.substring(i, j))) {
                    ret = Math.max(ret, j - i);
                }
            }
        }
        
        return ret;
    }
    
    private boolean isValid(String s) {
        Deque<Character> stack = new LinkedList();
        
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(c);
            else if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
            else return false;
        }
        
        return stack.isEmpty();
    }
}
```
