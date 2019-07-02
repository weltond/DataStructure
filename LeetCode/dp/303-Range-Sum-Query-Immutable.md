## [303. Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/)

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
```
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
```
Note:
- You may assume that the array does not change.
- There are many calls to sumRange function.

## Answer
### Method 1 - Caching(DP)- :rocket: 50ms (99.24%)
```java
class NumArray {
    // =========== Method 1: Caching(DP) ============
    // 50ms (99.24%)
    int[] sum;
    public NumArray(int[] nums) {
        sum = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                sum[i] = nums[0];
                continue;
            }
            sum[i] = sum[i - 1] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        return i < 1 ? sum[j] : sum[j] - sum[i - 1];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
```
