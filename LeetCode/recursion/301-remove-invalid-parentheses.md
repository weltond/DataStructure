## [301. Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses `(` and `)`.

Example 1:

```
Input: "()())()"
Output: ["()()()", "(())()"]
```

Example 2:

```
Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
```

Example 3:

```
Input: ")("
Output: [""]
```

## Answer
### Method 1 - Recursion - :rabbit: 3ms (75.39%)

```java
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new LinkedList();
        if (s == null || s.length() == 0) {
            res.add("");
            return res;
        }
        // 1. calc how many to be removed.
        int left = 0, right = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') left++;
            if (c == ')') {
                if (left == 0) right++;
                else left--;
            }
        }
        
        // 2. DFS to remove invalid.
        
        dfs(s, 0, left, right, res);
        
        return res;
    }
    
    private void dfs(String s, int lvl, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            if (isValid(s)) {
                res.add(s);
            }
            return;
        }
        
        for (int i = lvl, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (i != lvl && c == s.charAt(i - 1)) continue;
            
            if (c == '(' || c == ')') {
                StringBuilder sb = new StringBuilder(s);
                if (c == ')' && right > 0) {
                    String cur = sb.deleteCharAt(i).toString();
                    dfs(cur, i, left, right - 1, res);
                } else if (c == '(' && left > 0) {
                    String cur = sb.deleteCharAt(i).toString();
                    dfs(cur, i, left - 1, right, res);
                }
            }
        }
    }
    
    private boolean isValid(String s) {
        int cnt = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') cnt++;
            if (c == ')') cnt--;
            
            if (cnt < 0) return false;
        }
        
        return cnt == 0;
    }
}
```
