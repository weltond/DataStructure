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
### Method 2 - Greedy - :turtle: 9ms (43.69%)
```java
class Solution {
    // ========== Method 2: Greedy ============
    // 9ms (43.69%)
    public boolean validPalindrome(String s) {
        if (s == null) return true;
        
        int l = 0, r = s.length() - 1;
        
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPal(s, l + 1, r) || isPal(s, l, r - 1);
            }
            l++;
            r--;
        }
        
        return true;
    }
    
    private boolean isPal(String s, int from, int to) {
        while (from < to) {
            if (s.charAt(from++) != s.charAt(to--)) return false;
        }
        
        return true;
    }
}
```
### Method 1 - DFS - :rocket: 6ms (92.49%)
#### Approach 2

```java
class Solution {
    public boolean validPalindrome(String s) {
        if (s == null) return true;
        
        return dfs(s, 0, s.length() - 1, false);
    }
    private boolean dfs(String s, int left, int right, boolean isUsed) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if (isUsed) return false;
                
                return dfs(s, left + 1, right, !isUsed) || dfs(s, left, right - 1, !isUsed);
            }
            left++;
            right--;
        }
        
        return true;
    }
}
```

#### Approach 1

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
