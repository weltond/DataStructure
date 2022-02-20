## [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

 

Example 1:

```
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
```

Example 2:

```
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
``` 

**Constraints**:

-2 <= nums.length <= 105
- -30 <= nums[i] <= 30
- The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 

**Follow up**: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)

## Answers

### Approach 2 - 🚀 1ms (100%)

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        // e.g. nums = [1,2,3,4]
        int[] result = new int[nums.length];
        // 1. from start to end. store the product of previous items
        for (int i = 0, tmp = 1; i < nums.length; i++) {
            result[i] = tmp;
            tmp *= nums[i];
        }
        // after step 1: res = [1, 1, 2, 6]
        
        // 2. from end to start, store the product of items after current
        for (int i = nums.length - 1, tmp = 1; i >= 0; i--) {
            result[i] *= tmp;
            tmp *= nums[i];
        }
        
        return result;
    }
}
```

### Approach 1

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null) return null;
        
        int[] res = new int[nums.length];
        int[] prev = new int[nums.length];
        prev[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            prev[i] = nums[i - 1] * prev[i - 1];
        }
        
        int[] suf = new int[nums.length];
        suf[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] * nums[i + 1];
        }
        
        for (int i = 0; i < nums.length; i++) {
            res[i] = suf[i] * prev[i];
        }
        
        return res;
    }
}
```
