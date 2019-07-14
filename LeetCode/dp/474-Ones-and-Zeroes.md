## [474. Ones and Zeroes](https://leetcode.com/problems/ones-and-zeroes/)

In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m `0`s and n `1`s respectively. On the other hand, there is an array with strings consisting of only `0`s and `1`s.

Now your task is to find the maximum number of strings that you can form with given m `0`s and n `1`s. Each `0` and `1` can be used at most once.

Note:

- The given numbers of `0`s and `1`s will both not exceed `100`
- The size of given string array won't exceed `600`.
 

Example 1:
```
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
``` 

Example 2:
```
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
```

## Answer
### Method 1 - DP - :rabbit: 4ms (83.17%)
 `dp[i][j]` = the max number of strings that can be formed with i 0's and j 1's from the first few strings up to the current string s
 
 **Catch: have to go from bottom right to top left**
 
 Why? If a cell in the `dp[][]` is updated(because s is selected), we should be adding 1 to `dp[i][j]` from the previous iteration (when we were not considering s)
 
 If we go from top left to bottom right, we would be using results from this iteration => overcounting
```java
class Solution {
    // ============ DP ==============
    // 10ms (81.51%)
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null) return 0;
        
        // dp[i][j] is maximum number of strings with i '0' and j '1'
        int[][] dp = new int[m + 1][n + 1];
        
        for (int k = 0; k < strs.length; k++) {
            int[] cnt = count(strs[k]);
            int zeros = cnt[0], ones = cnt[1];
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                    p(dp);
                }   
            }
        }
        
        return dp[m][n];
    }
    
    private int[] count(String s) {
        int[] cnt = new int[2];
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (c == '0') cnt[0]++;
            else cnt[1]++;
        }
        
        return cnt;
    }
    
    private void p(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("======");
    }
    
}
```
