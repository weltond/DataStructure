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
