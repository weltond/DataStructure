## [837. Palindromic Substrings](https://www.lintcode.com/problem/palindromic-substrings/description?_from=ladder&&fromId=130)

Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example1

```
Input: "abc"
Output: 3
Explanation:
3 palindromic strings: "a", "b", "c".
```

Example2

```
Input: "aba"
Output: 4
Explanation:
4 palindromic strings: "a", "b", "a", "aba".
```

Notice:
- The input string length won't exceed 1000

## Answer
### Method 1 - DP - 
#### Approach 1 - 1D array :turtle: 453ms (2.00%)

```java
public class Solution {
    /**
     * @param str: s string
     * @return: return an integer, denote the number of the palindromic substrings
     */
    public int countPalindromicSubstrings(String str) {
        // write your code here
        if (str == null || str.length() == 0) return 0;
        
        int len = str.length();
        int res = 0;
        
        boolean[][] dp = new boolean[len][len];
        
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                dp[i][j] = str.charAt(i) == str.charAt(j) && ((j <= i + 2) || dp[i + 1][j - 1]);
                if (dp[i][j]) res++;
            }
        }
        
        return res;
    }
}
```
