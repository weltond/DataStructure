## [91. Decode Ways](https://leetcode.com/problems/decode-ways/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

A message containing letters from A-Z can be encoded into numbers using the following mapping:

```
'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
```

To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

- "AAJF" with the grouping (1 1 10 6)
- "KJF" with the grouping (11 10 6)

Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

Given a string s containing only digits, return the number of ways to decode it.

The test cases are generated so that the answer fits in a 32-bit integer.

 

Example 1:

```
Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
```

Example 2:

```
Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
```

Example 3:

```
Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
``` 

Constraints:

- 1 <= s.length <= 100
- s contains only digits and may contain leading zero(s).

## Answers
### Method 2 - DP
Space: O(1), Time: O(N)

```java
class Solution {
    public int numDecodings(String s) {  
        if (s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int twoBack = 1;
        int oneBack = 1;
        for (int i = 1; i < n; i++) {
            int current = 0;
            if (s.charAt(i) != '0') {
                current = oneBack;
            }
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += twoBack;
            }
           
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }
}
```

Space :O(N), Time: O(N)
```java
class Solution {

    public int numDecodings(String s) {
        // DP array to store the subproblem results
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        
        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for(int i = 2; i < dp.length; i++) {
            // Check if successful single digit decode is possible.
            if (s.charAt(i - 1) != '0') {
               dp[i] = dp[i - 1];  
            }
            
            // Check if successful two digit decode is possible.
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[s.length()];
    }
}
```
### Method 1 - DFS - 2ms (67.92%)
<img width="462" alt="image" src="https://user-images.githubusercontent.com/9000286/156667412-f5cbd054-2943-4056-a5bb-f66fe457a9d6.png">

```java
class Solution {
    int len;
    Map<Integer, Integer> map = new HashMap();
    public int numDecodings(String s) {
        len = s.length();
        
        return dfs(s, 0);
    }
    
    private int dfs(String s, int idx) {
        if (idx > len) return 0;
        
        if (idx == len) return 1;
        
        if (map.containsKey(idx)) return map.get(idx);
        
        int res = 0;
        
        if (isValid(s, idx, true)) {
            res += dfs(s, idx + 1);
        }
        
        if (idx + 1 < s.length() && isValid(s, idx, false)) {
            res += dfs(s, idx + 2);
        }
        
        map.put(idx, res);
        
        return res;
    }
    
    private boolean isValid(String s, int idx, boolean one) {
        char c1 = s.charAt(idx);
        if (one) {
            return c1 != '0';
        }
        
        char c2 = s.charAt(idx + 1);
        
        return c1 == '1' || (c1 == '2' && 0 <= c2 && c2 <= '6');
    }
}
```
