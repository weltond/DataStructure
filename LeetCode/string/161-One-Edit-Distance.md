## [161. One Edit Distance]()

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given two strings S and T, determine if they are both one edit distance apart.

One ediit distance means doing one of these operation:

- insert one character in any position of S
- delete one character in S
- change one character in S to other character

Example
Example 1:

```
Input: s = "aDb", t = "adb" 
Output: true
```

Example 2:

```
Input: s = "ab", t = "ab" 
Output: false
Explanation:
s=t ,so they aren't one edit distance apart
```

## Answer
### Method 1 - Recursion - :turtle: 300ms (11.20%)

```java
public class Solution {
    /**
     * @param s: a string
     * @param t: a string
     * @return: true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        // write your code here
        if (s == null || t == null || s.equals(t)) return false;
        
        int lens = s.length(), lent = t.length();
        
        if (Math.abs(lens - lent) >= 2) return false;
        
        return dfs(s, t) <= 1;
    }
    
    private int dfs(String s, String t) {
        int lens = s.length(), lent = t.length();
        
        if (lens == 0) return lent;
        if (lent == 0) return lens;
        
        char c1 = s.charAt(0), c2 = t.charAt(0);
        int res = Integer.MAX_VALUE;
        if (c1 == c2) {
            res = dfs(s.substring(1), t.substring(1));
        } else {
            int del = dfs(s.substring(1), t) + 1;
            int rep = dfs(s.substring(1), t.substring(1)) + 1;
            int add = dfs(s, t.substring(1)) + 1;
            
            res = Math.min(del, Math.min(rep, add));
        }
        
        return res;
    }
}
```
