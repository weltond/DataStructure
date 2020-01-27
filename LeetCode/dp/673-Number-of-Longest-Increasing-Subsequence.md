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
### Method 2 - Segment Tree -

```java
// TO DO...
```


### Method 1 - DP - :rabbit: 9ms (51.80%)

**Similar to [549. Binary Tree Longest Consecutive Sequence II]()**

for each element in the array or on in the tree, they all carry three fields :

1) the maximum increasing / decreasing length ends at the current element,
2) its own value ,
3) the total number of maximum length,

and each time when we visit a element, we will use its 2) to update 1) and 3), the only difference is for array we use iteration while for tree we use recursion......
**Also, for substring problem, we usually use only one for loop because for each index, we only care about the relationship between its two neighbors, while for subsequence problem, we use two for loops , because for each index, any other indexes can do something...**


```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null) return 0;
        
        int[] len = new int[nums.length];   // len[i]: max length of LIS from nums[0:i]
        int[] cnt = new int[nums.length];   // cnt[i]: number of LIS from nums[0:i]
        
        // result is the sum of each cnt[i] while its corresponding len[i] is the maximum length
        int maxLen = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            len[i] = 1;
            cnt[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] <= nums[j]) continue;
                
                if (len[i] == len[j] + 1) cnt[i] += cnt[j];
                else if (len[i] < len[j] + 1) {
                    len[i] = len[j] + 1;
                    cnt[i] = cnt[j];
                }
            }
            
            if (maxLen == len[i]) res += cnt[i];
            else if (maxLen < len[i]) {
                maxLen = len[i];
                res = cnt[i];
            }
            
        }
        
        return res;
        
    }
}
```
