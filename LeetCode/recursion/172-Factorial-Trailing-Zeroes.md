## [172. Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes/)

Given an integer n, return the number of trailing zeroes in n!.

Example 1:
```
Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.
```
Example 2:
```
Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.
```

Note: Your solution should be in logarithmic time complexity.

## Answer
### Method 2 - Recursion - :rocket: 0ms
```java
class Solution {
    // Consider how many 10x => 10 = 2*5 => how many 2x and 5x => how many 5 (number of 2x are larger than number of 5x)
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
```
### Method 1 - Iteration - :rocket: 0ms
```java
class Solution {
    // Consider how many 10x => 10 = 2*5 => how many 2x and 5x => how many 5 (number of 2x are larger than number of 5x)
    public int trailingZeroes(int n) {
        int res = 0;
        
        while (n != 0) {
            res += n / 5;
            n = n / 5;
        }
        
        return res;
    }
}
```
