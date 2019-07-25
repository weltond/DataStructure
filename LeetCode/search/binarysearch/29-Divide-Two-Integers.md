## [29. Divide Two Integers](https://leetcode.com/problems/divide-two-integers/)

Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:
```
Input: dividend = 10, divisor = 3
Output: 3
```
Example 2:
```
Input: dividend = 7, divisor = -3
Output: -2
```
Note:

- Both dividend and divisor will be 32-bit signed integers.
- The divisor will never be 0.
- Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: `[−2^31,  2^31 − 1]`. For the purpose of this problem, assume that your function returns `2^31 − 1` when the division result overflows.

## Answer
### Method 2 - Binary Search
```java
// TO DO...
```
### Method 1 - Math - :1ms (100%)
```java
class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;
        
        long m = Math.abs((long)dividend), n = Math.abs((long)divisor), res = 0;
        
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        
        while (m >= n) {
            long t = n, p = 1;
            while (m >= (t << 1)) {
                t <<= 1;
                p <<= 1;
            }
            
            res += p;
            m -= t;
        }
        int ret = (int) res;
        return (sign == 1) ? ret : -ret;
    }
}
```
