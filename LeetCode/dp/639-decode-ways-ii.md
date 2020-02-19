## [639. Decode Ways II](https://leetcode.com/problems/decode-ways-ii/)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

A message containing letters from A-Z is being encoded to numbers using the following mapping way:

```
'A' -> 1
'B' -> 2
...
'Z' -> 26
```

Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod `10^9 + 7`.

Example 1:

```
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
```

Example 2:

```
Input: "1*"
Output: 9 + 9 = 18
```

Note:
- The length of the input string will fit in range `[1, 105]`.
- The input string will only contain the character `'*'` and digits `'0' - '9'`.

## Answer
### Method 1 - DP - :rabbit: 25ms (53.19%)

```java
class Solution {
    long MOD = (long) Math.pow(10, 9) + 7;
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int len = s.length();
        long[] dp = new long[len + 1];
        
        dp[0] = 1;
        
        for (int i = 1; i <= len; i++) {
            char c = s.charAt(i - 1);
            dp[i] = dp[i - 1] * getCntOne(c);
            
            if (i == 1) continue;
            
            char p = s.charAt(i - 2);
            dp[i] += dp[i - 2] * getCntTwo(p, c);
            
            dp[i] %= MOD;
        }
        
        return (int) dp[len];
    }
    
    private int getCntOne(char c) {
        if (c == '0') return 0;
        if (c != '*') return 1;
        
        return 9;
    }
    
    private int getCntTwo(char c2, char c1) {
        // c2 != '*'
        if (c2 == '0' || (c2 >= '3' && c2 <= '9')) return 0;
        if (c2 == '1') return c1 == '*' ? 9 : 1;
        if (c2 == '2') return c1 == '*' ? 6 : c1 <= '6' ? 1 : 0;
        
        // c2 == '*'
        if (c1 != '*') return c1 >= '7' ? 1 : 2;
        
        // both '*'
        return 15;
    }
}
```
