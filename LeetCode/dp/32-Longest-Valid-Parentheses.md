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
### Method 2 - DP
```java

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
