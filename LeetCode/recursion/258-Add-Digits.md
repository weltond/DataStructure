## [258. Add Digits](https://leetcode.com/problems/add-digits/)

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

Example:
```
Input: 38
Output: 2 
Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2. 
             Since 2 has only one digit, return it.
```
Follow up:
- Could you do it without any loop/recursion in O(1) runtime?

## Answer
### Method 1 - :rabbit: 1ms (99.41%)
```java
class Solution {
    // 1ms (99.41%)
    public int addDigits(int num) {
        while (num >= 10) {
            int tmp = num;
            num = 0;
            
            while (tmp != 0) {
                num += tmp % 10;
                tmp /= 10;
            }
        }
        
        return num;
    }
}
```
