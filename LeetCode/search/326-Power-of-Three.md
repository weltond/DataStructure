## [326. Power of Three](https://leetcode.com/problems/power-of-three/)

Given an integer, write a function to determine if it is a power of three.

Example 1:
```
Input: 27
Output: true
```
Example 2:
```
Input: 0
Output: false
```
Example 3:
```
Input: 9
Output: true
```
Example 4:
```
Input: 45
Output: false
```
Follow up:
- Could you do it without using any loop / recursion?

## Answer
### Method 2 - Limit Iteration - :rocket: 10ms (100%)
```java
class Solution {
    // ======== Method 2 ======
    // MAX_VALUE = 2147483647. 3^|log3MAX_VALUE| = 3 ^ (19.65) = 3^19 = 1162261467
    // 10ms (100%)
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;   
    }
}
```
### Method 1 - Naive - :rabbit: 11ms (77.09%)
```java
class Solution {
    // ======== Method 1 ======
    // 11ms (77.09%)
    public boolean isPowerOfThree(int n) {
        if (n == 0) return false;
        
        while (n != 1) {
            int t = n % 3;
            if (t != 0) return false;
            n /= 3;
        }
        
        return true;
    }
}
```
