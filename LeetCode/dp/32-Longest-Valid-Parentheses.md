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
#### Approach 2 :1ms (100%)
- Space: O(1)
```java
class Solution {
    // ========= Method 3 : Stack ============
    // Approach 2： O(1) space 1ms(100%)
    public int longestValidParentheses(String s) {
        int ans = 0;
        if (s == null || s.length() == 0) return ans;
        
        int len = s.length(), left = 0, right = 0;
        /**
        1. left -> right:
            if right cnt > left cnt, reset right and left cnt to 0.
            if right cnt = left cnt, update result
        2. right -> left:
            if left cnt > right cnt, reset right and left cnt to 0.
            if right cnt = left cnt, update result
        */
        
        // left -> right
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            
            if (right > left) {
                left = right = 0;
            } else if (right == left) {
                ans = Math.max(ans, 2 * left);
            }
        }
        
        // right -> left
        left = right = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;
            
            if (left > right) {
                left = right = 0;
            } else if (right == left) {
                ans = Math.max(ans, 2 * left);
            }
        }
        
        return ans;
    }
}
```
#### Approach 1 :rabbit: 3ms (63.35%)
- Space: O(n)
```java
class Solution {
    // ========= Method 3 : Stack ============
    // Approach 1: O(n) space. 3ms (63.35%)
    public int longestValidParentheses(String s) {
        int ans = 0;
        if (s == null || s.length() == 0) return ans;
        
        Deque<Integer> stack = new LinkedList();    // store index
        
        /* Because of valid property, when we meet ')', 
            we pop stack, and then use current index - stack.peek() to get the length, 
            instead of using (i - stack.pop() + 1) 
            because we will not be sure if the popped one is '(' or ')'.
          
           So we push -1 into the stack as the last invalid index. 
        */ 
        stack.push(-1);
        
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        
        return ans;
    }
}
```
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
