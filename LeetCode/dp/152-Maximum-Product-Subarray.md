## [152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:
```
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
```
Example 2:
```
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
```
## Answer
### Method 1 - Keep track of min and max - :rocket: 1ms (99.11%)
```java
class Solution {
    // 1ms (99.11%)
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        // minVAl and maxVal stores the max/min product of subarray
        // that ends with the current number
        int minVal = nums[0];
        int maxVal = nums[0];
        int res = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Multiplied by a negative makes big number smaller,
            // small number bigger.
            // So we redefine the extrenums by swapping them
            if (nums[i] < 0) {
                int tmp = minVal;
                minVal = maxVal;
                maxVal = tmp;
            }
            
            // max/min is either the current itself or the max/min by previous number times the current one
            minVal = Math.min(nums[i], nums[i] * minVal);
            maxVal = Math.max(nums[i], nums[i] * maxVal);
            
            res = Math.max(res, maxVal);
        }
        
        return res;
    }
}
```
### WRONG Method

**Wrong** when input is `[-2,3,-4]`
```java
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int curProduct = 1;
        int res = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            if (curProduct <= 0 && nums[i] >= 0) {
                curProduct = nums[i];
            } else {
                curProduct *= nums[i];
            }
            
            res = Math.max(res, curProduct);
        }
        
        return res;
    }
}
```
