## [512. Decode Ways](https://www.lintcode.com/problem/decode-ways/description?_from=ladder&&fromId=14)

A message containing letters from A-Z is being encoded to numbers using the following mapping:

```
'A' -> 1
'B' -> 2
...
'Z' -> 26
```

Given an encoded message containing digits, determine the total number of ways to decode it.

Example 1:

```
Input: "12"
Output: 2
Explanation: It could be decoded as AB (1 2) or L (12).
```

Example 2:

```
Input: "10"
Output: 1
```

Notice
- 我们不能解码空串，因此若消息为空，你应该返回0。

## Answer
### Method 1 - Recursion -

#### Approach 1 :turtle: 252ms (8.60%)

```java
public class Solution {
    /**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
     int[] memo;
    public int numDecodings(String s) {
        // write your code here
        if (s == null || s.length() == 0) return 0;
        
        memo = new int[s.length()];
        return dfs(s, 0);
    }
    
    private int dfs(String s, int lvl) {
        if (lvl == s.length()) return 1;
        
        if (memo[lvl] != 0) return memo[lvl];
        
        int res = 0;
        if (oneValid(s, lvl)) {
            res += dfs(s, lvl + 1);
        }
        
        if (twoValid(s, lvl)) {
            res += dfs(s, lvl + 2);
        }
        memo[lvl] = res;
        return res;
    }
    
    private boolean oneValid(String s, int i) {
        char c = s.charAt(i);
        if (c > '0' && c <= '9') return true;
        return false;
    }
    
    private boolean twoValid(String s, int i) {
        if (i == s.length() - 1) return false;
        
        char c1 = s.charAt(i), c2 = s.charAt(i + 1);
        
        if (c1 == '1' || (c1 == '2' && (c2 >= '0' && c2 <= '6'))) return true;
    
        return false;
    }
}
```
