## [201. Bitwise AND of Numbers Range](https://leetcode.com/problems/bitwise-and-of-numbers-range/)

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0

## Answer
### Method 1 - Bit - :rocket: 4ms (100%)
```java
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;  // i means we have how many bits are bitwise 0 on the right
        while (m != n) {
            m >>= 1;
            n >>= 1;
            i++;
        }
        
        return m << i;
    }
}
```
