## [204. Count Primes](https://leetcode.com/problems/count-primes/)

Count the number of prime numbers less than a non-negative number, n.

Example:
```
Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
```
## Answer
### Method 1 - Math - :rabbit: 12ms (69.68%)
```java
class Solution {
    // 12ms (69.98%)
    public int countPrimes(int n) {
        int res = 0;
        boolean[] notPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (notPrime[i]) continue;
            res++;
            
            for (int j = 2; j * i < n; j++) {
                notPrime[j * i] = true;
            }
        }
        
        return res;
    }
}
```
