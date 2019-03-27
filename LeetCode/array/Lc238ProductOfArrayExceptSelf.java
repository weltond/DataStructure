// https://leetcode.com/problems/product-of-array-except-self/

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
