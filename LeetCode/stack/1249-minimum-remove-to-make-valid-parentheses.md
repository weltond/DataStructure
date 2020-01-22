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
### Method 1 - Stack - :turtle: 51ms (13.50%)

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
