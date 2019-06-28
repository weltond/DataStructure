## [461. Hamming Distance](https://leetcode.com/problems/hamming-distance/)

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:
```
Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
```
The above arrows point to positions where the corresponding bits are different.

## Answer
### Method 3 - :rocket: 0ms (100%)
```java
class Solution {
    public int hammingDistance(int x, int y) {
        int diff = x ^ y;   // xor for the difference of x and y. same = 0, diff = 1
        return countOne(diff);
    }
    
    private int countOne(int diff) {
        int cnt = 0;
        
        while (diff != 0) {
            diff = diff & (diff - 1);
            cnt++;
        }
        
        return cnt;
    }
    
    private int countOneV2(int diff) {
        int cnt = 0;
        
        while (diff != 0) {
            cnt += diff & 1;
            diff >>= 1;
        }
        
        return cnt;
    }
}
```
### Method 2 - :rocket: 0ms (100%)
```java
class Solution {
    // 0ms
    public int hammingDistance(int x, int y) {
        int res = 0;
        
        while (x > 0 || y > 0) {
            if (x % 2 != y % 2) res++;
            
            x = x / 2;
            y = y / 2;
        }
        
        return res;
    }
}
```
### Method 1 - :rocket: 0ms (100%)
```java
class Solution {
    // ======= Method 1: Bit =======
    // 0ms
    public int hammingDistance(int x, int y) {
        int res = 0;
        
        for (int i = 0; i < 32; i++) {
            int l1 = x & 1; // get last bit of x
            int l2 = y & 1; // get last bit of y
            if (l1 != l2) res++;    // compare
            x = x >> 1; // shift x right by 1 bit
            y = y >> 1; // shift y right by 1 bit
        }
        
        return res;
    }
}
```
