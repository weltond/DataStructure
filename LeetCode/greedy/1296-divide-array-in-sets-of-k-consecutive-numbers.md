## [1296. Divide Array in Sets of K Consecutive Numbers](https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/)

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
Return True if its possible otherwise return False.


Example 1:

```
Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
```

Example 2:

```
Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
Output: true
Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
```

Example 3:

```
Input: nums = [3,3,2,2,1,1], k = 3
Output: true
```

Example 4:

```
Input: nums = [1,2,3,4], k = 3
Output: false
Explanation: Each array should be divided in subarrays of size 3.
``` 

Constraints:

- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 10^9`
- `1 <= k <= nums.length`

## Answer
### Method 1 - Sort - :turtle: 886ms (5.07%)

```java
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) return false;
        
        Arrays.sort(nums);
        
        int last = nums[0] - 1, i = 0, t = k;
        boolean met = false;
        
        while (i < nums.length) {
            // reach k elements
            if (t == 0) {
                i = 0;
                t = k;
                met = false;
                last = 0;
                continue;
            }
            
            if (nums[i] == 0 || nums[i] == last) {
                i++;
                continue;
            }

            if (met && nums[i] != last + 1) {
                return false;
            }
            
            // get the new start
            if (!met) {
                met = true;
            }
            
            last = nums[i];
            nums[i++] = 0;
            t--;
        }
        
        return true;
    }
}
```
