## [1249. Minimum Remove to Make Valid Parentheses](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a string s of `'('` , `')'` and lowercase English characters. 

Your task is to remove the minimum number of parentheses ( `'('` or `')'`, in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

- It is the empty string, contains only lowercase characters, or
- It can be written as AB (A concatenated with B), where A and B are valid strings, or
- It can be written as (A), where A is a valid string.
 

Example 1:

```
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
```

Example 2:

```
Input: s = "a)b(c)d"
Output: "ab(c)d"
```

Example 3:

```
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
```

Example 4:

```
Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"
```

Constraints:

- `1 <= s.length <= 10^5`
- `s[i]` is one of  `'('` , `')'` and lowercase English letters.
## Answer
### Method 2 - Two Pass 

A key observation you might have made from the previous algorithm is that for all invalid ")", we know immediately that they are invalid (they are the ones we were putting in the set). It is the "(" that we don't know about until the end (as they are what was left on the stack at the end). We could be building up a string builder in that first loop that has all of the invalid ")" removed. This would be half the problem solved in the first loop, in O(n)O(n) time.
#### Approach 2 - Shortened
Time: O(N), Space: O(N)

```java
class Solution {

    public String minRemoveToMakeValid(String s) {

        // Pass 1: Remove all invalid ")"
        StringBuilder sb = new StringBuilder();
        int openSeen = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openSeen++;
                balance++;
            } if (c == ')') {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }

        // Pass 2: Remove the rightmost "("
        StringBuilder result = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                openToKeep--;
                if (openToKeep < 0) continue;
            }
            result.append(c);
        }

        return result.toString();
    }
}
```
#### Approach 1 - StringBuilder
Time: O(N), Space: O(N)

```java
class Solution {

    private StringBuilder removeInvalidClosing(CharSequence string, char open, char close) {
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == open) {
                balance++;
            } if (c == close) {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }  
        return sb;
    }

    public String minRemoveToMakeValid(String s) {
        StringBuilder result = removeInvalidClosing(s, '(', ')');
        result = removeInvalidClosing(result.reverse(), ')', '(');
        return result.reverse().toString();
    }
}
```
### Method 1 - Stack - :rocket: 10ms (97.33%)
Time: O(4N), Space: O(N)

- Use stack to store the index. If `(`, push positive index, if `)`, push negative index if stack's top is not positive (`(`).

```java
class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s == null) return "";
        
        Deque<Integer> stack = new LinkedList();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i + 1);
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() > 0) {
                    stack.pop();
                } else {
                    stack.push(-(i + 1));
                }
            }
         }
        
        while (!stack.isEmpty()) {
            sb.deleteCharAt(Math.abs(stack.pop()) - 1);
        }    
        
        return sb.toString();
    }
}
```

### Wrong 

#### 2 TLE

Like [301. Remove Invalid Parenthese](https://github.com/weltond/DataStructure/blob/master/LeetCode/recursion/301-remove-invalid-parentheses.md). It causes TLE.

```java
class Solution {
    String res;
    public String minRemoveToMakeValid(String s) {
        char[] arr = s.toCharArray();
        
        int left = 0, right = 0;
        for (char c : arr) {
            if (c == '(') {
                left++;
            }
            
            if (c == ')') {
                if (left == 0) right++;
                else left--;
            }
        }
        
        dfs(s, 0, left, right);
        
        return res;
    }
    
    private boolean dfs(String s, int idx, int left, int right) {
        if (left == 0 && right == 0) {
            
            if (isValid(s)) {
                res = s;
                
                return true;
            }
            
            return false;
        }
        
        for (int i = idx, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            
            if (c == '(' || c == ')') {
                StringBuilder sb = new StringBuilder(s);
                if (c == '(' && left > 0) {
                    sb.deleteCharAt(i);
                    if (dfs(sb.toString(), i, left - 1, right)) {
                        return true;
                    }
                } else if (c == ')' && right > 0) {
                    sb.deleteCharAt(i);
                    if (dfs(sb.toString(), i, left, right - 1)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean isValid(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') cnt++;
            if (c == ')') {
                if (cnt <= 0) return false;
                cnt--;
            }
        }
        
        return cnt == 0;
    }
}
```

#### 1 - Wrong res
Wrong when input s is `"())()((("`.

Output is `"))(("` while it should be `"()()"`

```java
class Solution {
    public String minRemoveToMakeValid(String s) {
        char[] arr = s.toCharArray();
        
        int left = 0, right = 0;
        for (char c : arr) {
            if (c == '(') {
                left++;
            }
            
            if (c == ')') {
                if (left == 0) right++;
                else left--;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (char c : arr) {
            if (c == '(' && left > 0) {
                left--; // skip (
            } else if (c == ')' && right > 0) {
                right--;    // skip )
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}
```
