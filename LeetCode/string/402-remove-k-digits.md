## [402. Remove K Digits](https://leetcode.com/problems/remove-k-digits/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
- The length of num is less than 10002 and will be ≥ k.
- The given num does not contain any leading zero.

Example 1:

```
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
```

Example 2:

```
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
```

Example 3:

```
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
```

## Answer
### Method 1 - String - :rocket: 3ms (93.94%)

- Basic idea is to remove first decreasing number from start.
- Edge case:
  - `["1",1]`; `["112",1]`; `["102", 3]`.

```java
class Solution {
    public String removeKdigits(String num, int k) {
        if (num == null) return "0";
        
        StringBuilder sb = new StringBuilder(num);
        
        while (k-- > 0) {
            if (sb.charAt(0) == '0') sb.deleteCharAt(0);
            
            // every new loop sb is new
            for (int i = 0, len = sb.length(); i < len; i++) {
                // i == len - 1 to remove the last char if prev not found
                if (i == len - 1 || sb.charAt(i) - '0' > sb.charAt(i + 1) - '0') {
                    sb.deleteCharAt(i);
                    break;
                }
            }
        }
        
        int idx = 0, len = sb.length();
        // remove leading 0
        while (idx < len && sb.charAt(idx) == '0') {
            idx++;
        }
        
        String s = new String(sb).substring(idx);
        
        return s.length() == 0 ? "0" : s;
    }
}
```
