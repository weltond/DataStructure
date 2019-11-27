## [680. Valid Palindrome II](https://leetcode.com/problems/valid-palindrome-ii/)

Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
```
Input: "aba"
Output: True
```
Example 2:
```
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
```
Note:
- The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

## Answer
### Method 1 - DFS - :rocket: 6ms (92.49%)
```java
class Solution {
    // ========== Method 1: DFS ============
    // 6ms (92.49%)
    public boolean validPalindrome(String s) {
        if (s == null) return true;
        
        return dfs(s.toCharArray(), 0, s.length() - 1, false);
    }
    
    private boolean dfs(char[] a, int i, int j, boolean isDelete) {
        if (i >= j) return true;
        
        
        boolean res = false;
        if (a[i] != a[j] && !isDelete) {
            isDelete = true;
            res = dfs(a, i + 1, j, isDelete) || dfs(a, i, j - 1, isDelete);
        } else if (a[i] == a[j]) {
            res = dfs(a, i + 1, j - 1, isDelete);
        }
        
        return res;
    }
}
```
