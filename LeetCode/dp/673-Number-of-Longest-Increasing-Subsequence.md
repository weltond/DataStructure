## [673. Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence/)

Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
```
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
```
Example 2:
```
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
```

Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.

## Answer
### Method 1 - DP - :rabbit: 9ms (51.80%)
```java
class Solution {
    // 9ms (51.80%)
    public int minSwap(int[] a, int[] b) {
        int[] swap = new int[a.length];
        int[] keep = new int[a.length];
        Arrays.fill(swap, Integer.MAX_VALUE);
        Arrays.fill(keep, Integer.MAX_VALUE);
        swap[0] = 1;
        keep[0] = 0;
        
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1] && b[i] > b[i - 1]) {
                swap[i] = swap[i - 1] + 1;
                keep[i] = keep[i - 1];
            }
            
            if (a[i] > b[i - 1] && b[i] > a[i - 1]) {
                swap[i] = Math.min(swap[i], keep[i - 1] + 1);
                keep[i] = Math.min(keep[i], swap[i - 1]);
            }
        }
        
        return Math.min(swap[a.length - 1], keep[a.length - 1]);
    }
}
```
