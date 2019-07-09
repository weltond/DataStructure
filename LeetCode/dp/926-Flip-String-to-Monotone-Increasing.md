## [926. Flip String to Monotone Increasing](https://leetcode.com/problems/flip-string-to-monotone-increasing/)

A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)

We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.

Return the minimum number of flips to make S monotone increasing.

Example 1:
```
Input: "00110"
Output: 1
Explanation: We flip the last digit to get 00111.
```
Example 2:
```
Input: "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.
```
Example 3:
```
Input: "00011000"
Output: 2
Explanation: We flip to get 00000000.
```

Note:

- `1 <= S.length <= 20000`. **It means O(n^2) would NOT work.**
- S only consists of '0' and '1' characters.

## Answer
### Method 2 - Prefix Sum - :turtle: 8ms (30.17%)
```java
class Solution {
    // =========== Method 2: Prefix Sum ==========
    // 8ms (30.17%)
    public int minFlipsMonoIncr(String s) {
        int len = s.length();
        int[] prefix = new int[len + 1];    // calculate how many 1s
        for (int i = 0; i < len; i++) {
            prefix[i + 1] = prefix[i] + (s.charAt(i) == '1' ? 1 : 0);
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= len; i++) {
            int firstHalfOneToZero = prefix[i];
            int secondHalfZeroToOne = (len - i) - (prefix[len] - prefix[i]);
            ans = Math.min(ans, firstHalfOneToZero + secondHalfZeroToOne);
        }
        
        return ans;
    }
}
```
### Method 1 - DP - :turtle: 8ms (30.17%)
```java
class Solution {
    // ========== Method 1: DP =============
    // 8ms (30.17%)
    public int minFlipsMonoIncr(String s) {
        int len = s.length();
        
        int[] one = new int[len + 1];
        int[] zero = new int[len + 1];
        
        for (int i = 1; i <= len; i++) {
            char c = s.charAt(i - 1);
            if (c == '1') {
                one[i] = Math.min(one[i - 1], zero[i - 1]);
                zero[i] = zero[i - 1] + 1;
            } else {
                zero[i] = zero[i - 1];
                one[i] = Math.min(one[i - 1], zero[i - 1]) + 1;
            }
        }
        
        return Math.min(zero[len], one[len]);
    }
}
```
