// https://leetcode.com/problems/maximum-subarray/

class Solution {
    // ======== Approach 2: DP =========
    // 6ms
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int sum = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            res = Math.max(sum, res);
        }
        
        return res;
    }
    
    // ======== Approach 1 ============
    // 6ms
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int sum = 0, res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum < 0) {
                sum = 0;
                res = Math.max(res, nums[i]);
            } else {
                res = Math.max(res, sum);
            }
        }
        
        return res;
    }
}
