## [87. Scramble String](https://leetcode.com/problems/scramble-string/)

Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = `"great"`:
```
    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
```
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node `"gr"` and swap its two children, it produces a scrambled string `"rgeat"`.
```
    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
```
We say that `"rgeat"` is a scrambled string of `"great"`.

Similarly, if we continue to swap the children of nodes `"eat"` and `"at"`, it produces a scrambled string `"rgtae"`.
```
    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
```
We say that `"rgtae"` is a scrambled string of `"great"`.

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

Example 1:
```
Input: s1 = "great", s2 = "rgeat"
Output: true
```
Example 2:
```
Input: s1 = "abcde", s2 = "caebd"
Output: false
```

## Answer
### Method 2 - DP
The iterative version of the idea is considerably slower than the recursive simply because here we consider all possible states, while the recursive will only compute required states as it founds them. Time complexity of both is, in any case, the same.
```java

```
### Method 1 - DFS - :rabbit: 3ms (53.50%)
```java
class Solution {
    // ========== Method 1: DFS ==========
    // 3ms (53.50%)
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.equals(s2)) return true;
        int len1 = s1.length(), len2 = s2.length();
        if (len1 != len2) return false;
        
        // Without this pruning, it will be TLE!
        int[] arr = new int[128];
        for (int i = 0; i < len1; i++) {
            arr[s1.charAt(i) - 'a']++;
            arr[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) return false;
        }
        
        for (int i = 1; i < len1; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) return true;
            if (isScramble(s1.substring(len1 - i), s2.substring(0, i)) && isScramble(s1.substring(0, len1 - i), s2.substring(i))) return true;
        }
        
        return false;
    }
}
```
