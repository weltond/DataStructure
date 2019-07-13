## [650. 2 Keys Keyboard](https://leetcode.com/problems/2-keys-keyboard/)

Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

1. `Copy All`: You can copy all the characters present on the notepad (partial copy is not allowed).
1. `Paste`: You can paste the characters which are copied last time.
 
Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.

Example 1:
```
Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
``` 

Note:

- The n will be in the range [1, 1000].

## Answer
### Method 2 - DP - :turtle: 34ms (14.08%)
```java
class Solution {
    // ======= Method2 : DP ========
    // 34ms (14.08%)
    public int minSteps(int n) {
        if (n == 1) return 0;
        
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = i - 1; j > 1; j--) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[i / j] + j);
                }
            }
        }
        
        return dp[n];
    }
}
```
### Method 1 - DFS - :rabbit: 1ms (63.41%)
```java
class Solution {
    // ========== Method 1: DFS ===========
    // 1ms (63.41%)
    public int minSteps(int n) {
        if (n == 1) return 0;
        
        int res = n;
        for (int i = n - 1; i > 1; i--) {
            if (n % i == 0) {
                res = Math.min(res, minSteps(n / i) + i);
            }
        }
        
        return res;
    }
}
```
