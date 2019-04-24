// 

/**
Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
*/

class Solution {
    // ========= Method 2: Greedy ============
    // 1ms (99.92%)
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = lastPos; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
    // ========= Method 1: DP ============
    // 90ms (34.07%)
    public boolean canJump(int[] nums) {
        if (nums == null) return false;
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i; j < nums.length && j <= i + nums[i]; j++) {
                if (nums[i] >= i - j && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
}
