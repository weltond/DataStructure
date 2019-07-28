// https://leetcode.com/problems/maximum-subarray/
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int res = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            cur = (cur >= 0) ? cur + nums[i]: nums[i];
            
            res = Math.max(res, cur);
        }
        
        return res;
    }
}

class Solution {
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int res = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = (dp[i - 1] >= 0) ? dp[i - 1] + nums[i]: nums[i];
            }
            
            res = Math.max(res, dp[i]);
        }
        
        return res;
    }
}

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
