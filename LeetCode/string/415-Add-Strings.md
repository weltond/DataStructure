## [415. Add Strings](https://leetcode.com/problems/add-strings/submissions/)

Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

- The length of both num1 and num2 is < 5100.
- Both num1 and num2 contains only digits 0-9.
- Both num1 and num2 does not contain any leading zero.
- You **must not use any built-in BigInteger library** or **convert the inputs to integer** directly.

## Answer
### Method 1 - String - :rocket: 2ms (96.04%)
```java
class Solution {
    public String addStrings(String num1, String num2) {
        int l1 = num1.length() - 1, l2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        
        int add = 0;
        
        while (l1 >= 0 || l2 >= 0) {
            int s1 = 0, s2 = 0;
            if (l1 >= 0) {
                s1 = num1.charAt(l1--) - '0';
            }
            if (l2 >= 0) {
                s2 = num2.charAt(l2--) - '0';
            }
            
            int sum = (s1 + s2 + add) % 10;
            sb.append(sum);
            
            add = (s1 + s2 + add) / 10;
        }
        
        if (add != 0) {
            sb.append(add);
        }
        return sb.reverse().toString();
    }
}
```
