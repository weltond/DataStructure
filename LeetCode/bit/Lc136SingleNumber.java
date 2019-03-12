// https://leetcode.com/problems/single-number/

/**
	a ^ a = 0
	a ^ 0 = a
	a ^ a ^ b = b
*/
class Solution {
    // 0ms
    public int singleNumber(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum ^= nums[i];
        }
        
        return sum;
    }
}