## [523. Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/)

Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
```
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
```
Example 2:
```
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
```

Note:

- The length of the array won't exceed 10,000.
- You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

## Answer
### Method 2 - Math - :rocket: 2ms (99.78%)
- Time: O(n)
- Running sum from first element to index i : sum_i. If we mod k, it will be some format like : `sum_i = k * x + modk_1`
- Running sum from first element to index j : sum_j. If we mod k, it will be some format like : `sum_j = k * y + modk_2`

If they have the same mod, which is `modk_1 == modk_2`, subtracting these two running sum we get the difference `sum_i - sum_j = (x - y) * k = constant * k`, and the difference is the sum of elements between index i and j, and the value is a multiple of k.

```java
class Solution {
    // 2ms (99.78%)
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        if (nums == null) return false;
        
        Map<Integer, Integer> map = new HashMap();  // <mod, index>
        map.put(0, -1); // to cover [0,0] 0
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) sum %= k;
            
            Integer prev = map.get(sum);
            if (prev != null) {
                if (i - prev > 1) return true;
            } else {
                map.put(sum, i);
            }
        }
        
        return false;
    }
}
```
### Method 1 - DP - :turtle: 35ms (6.83)
```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null) return false;
        
        int[] dp = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            dp[i] = nums[i - 1] + dp[i - 1];
        }
        for (int i = 0; i <= nums.length; i++) {
            for (int j = i + 2; j <= nums.length; j++) {
                int diff = dp[j] - dp[i];
                if (k == 0 && diff == 0) return true;
                else if (k != 0 && diff % k == 0) return true;
            }
        }
        
        return false;
    }
}
```
