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
### WRONG Method

**Wrong when input is: ** 

`[-2,3,-4]`
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
