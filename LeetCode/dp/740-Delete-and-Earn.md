## [740. Delete and Earn](https://leetcode.com/problems/delete-and-earn/)

Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

You start with 0 points. Return the maximum number of points you can earn by applying such operations.

Example 1:
```
Input: nums = [3, 4, 2]
Output: 6
Explanation: 
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.
```

Example 2:
```
Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation: 
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.
``` 

Note:

- `The length of nums is at most 20000.`
- `Each element nums[i] is an integer in the range [1, 10000].`

## Answer
### Method 1 - DP - :rabbit: 2ms (76.46%)
```java
class Solution {
    // =========== Method 1: DP ===========
    // 2ms (76.46%)
    public int deleteAndEarn(int[] nums) {
        int[] count = new int[10000];
        int maxNum = 1, minNum = 10000;
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
            maxNum = Math.max(maxNum, nums[i]);
            minNum = Math.min(minNum, nums[i]);
        }
        int[] use = new int[10000];
        int[] skip = new int[10000];
        
        int res = 0;
        for (int i = minNum; i <= maxNum; i++) {
            use[i] = (count[i - 1] == 0 ? Math.max(skip[i - 1], use[i - 1]) : skip[i - 1]) + i * count[i];
            skip[i] = Math.max(skip[i - 1], use[i - 1]);  // we can either pick prev or skip prev if we choose to skip current
            
            res = Math.max(res, Math.max(use[i], skip[i]));
        }
        
        return res;
    }
}
```
