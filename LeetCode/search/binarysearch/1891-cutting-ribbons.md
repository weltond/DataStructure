## [1891. Cutting Ribbons](https://leetcode.com/problems/cutting-ribbons/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given an integer array ribbons, where ribbons[i] represents the length of the ith ribbon, and an integer k. You may cut any of the ribbons into any number of segments of positive integer lengths, or perform no cuts at all.

For example, if you have a ribbon of length 4, you can:
- Keep the ribbon of length 4,
- Cut it into one ribbon of length 3 and one ribbon of length 1,
- Cut it into two ribbons of length 2,
- Cut it into one ribbon of length 2 and two ribbons of length 1, or
- Cut it into four ribbons of length 1.
Your goal is to obtain k ribbons of all the same positive integer length. You are allowed to throw away any excess ribbon as a result of cutting.

Return the maximum possible positive integer length that you can obtain k ribbons of, or 0 if you cannot obtain k ribbons of the same length.

 

Example 1:

```
Input: ribbons = [9,7,5], k = 3
Output: 5
Explanation:
- Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
- Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
- Keep the third ribbon as it is.
Now you have 3 ribbons of length 5.
```

Example 2:

```
Input: ribbons = [7,5,9], k = 4
Output: 4
Explanation:
- Cut the first ribbon to two ribbons, one of length 4 and one of length 3.
- Cut the second ribbon to two ribbons, one of length 4 and one of length 1.
- Cut the third ribbon to three ribbons, two of length 4 and one of length 1.
Now you have 4 ribbons of length 4.
```

Example 3:

```
Input: ribbons = [5,7,9], k = 22
Output: 0
Explanation: You cannot obtain k ribbons of the same positive integer length.
``` 

**Constraints**:

- 1 <= ribbons.length <= 105
- 1 <= ribbons[i] <= 105
- 1 <= k <= 109

## Answers

### Method 1 - Binary Search - 37ms (71.71%)

Note the difference with [1011.Capacity to ship Packages within D days](https://github.com/weltond/DataStructure/blob/master/LeetCode/search/binarysearch/1011-capacity-to-ship-packages-within-d-days.md). 

Here we use `mid = (right + left + 1) / 2`; `left = mid; right = mid - 1` and return when `need >= k` rather than `need > k`.

```java
class Solution {
    public int maxLength(int[] ribbons, int k) {
        int left = 0, right = (int)1e5 + 1;     // left should start from 0, not 1.
        
        // if calculate max, time would be O(N)
//         int sum = 0;
//         for (int i : ribbons) {
//             right = Math.max(i, right);
//             sum += i;
//         }
        
//         if (sum < k) return 0;
        
        while (left < right) {
            int mid = (left + right + 1) / 2;   // pick the upper bound
            
            if (isThresholdSmallOrEqual(ribbons, k, mid)) {
                // threshold small or equal, we make left = mid 
                left = mid;
            } else {
                // threshold is large, decrease
                right = mid - 1;
            }
        }
        
        // left == right
        return left;
    }
    
    private boolean isThresholdSmallOrEqual(int[] arr, int k, int threshold) {
        int need = 0;
        
        for (int i : arr) {
            need += i / threshold;
            
            // need >= k means threshold is small or equal
            if (need >= k) return true;
        }
        
        // threshold is large
        return false;
    }
}
```
