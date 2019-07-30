## [69. Sqrt(x)](https://leetcode.com/problems/sqrtx/)

Implement `int sqrt(int x)`.

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:
```
Input: 4
Output: 2
```
Example 2:
```
Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
```             
## Answer
### Method 1 - Binary Search - :rocket: 1ms (100%)
#### Approach 2
**Mid is Mid+1**
```java

```
#### Approach 1
**Normal Mid**.
```java
class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        
        int l = 1, r = x;
        
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            if (x / mid == mid) return mid;
            else if (x / mid < mid) r = mid - 1;
            else l = mid;
        }
        return x / r >= r ? r : l;
    }
}
```
