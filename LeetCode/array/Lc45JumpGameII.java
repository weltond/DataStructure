// https://leetcode.com/problems/jump-game-ii/

/**
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.
*/

class Solution {
    
    // ============ Method 3: Greedy ==============
    // 1ms (100%)
    public int jump(int[] nums) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        
        return jumps;
    }
    // ============ Method 2: BFS ==============
    // Approach 2: start from head: 1ms (100%)
    public int jump(int[] nums) {
        if (nums.length < 2) return 0;
        
        int curMax = 0, i = 0, nextMax = 0, res = 0;
        
        while (curMax - i + 1 > 0) {    // nodes of current level > 0
            res++;
            
            for (; i <= curMax; i++) {  // traverse current level, and update the max reach of next level
                nextMax = Math.max(nextMax, i + nums[i]);
                if (nextMax >= nums.length - 1) return res;
            }
            
            curMax = nextMax;
        }
        
        return 0;
    }
    
    // Approach 1: start from end. 121ms(25.98%)
    public int jump(int[] nums) {
        int pos = nums.length - 1;
        int res = 0;
        while (pos != 0) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (i + nums[i] >= pos) {
                    pos = i;
                    res++;
                    break;
                }
            }
        }
        
        return res;
    }
    
    // ========== Method 1: DFS ============
    // TLE
//     int ans;
//     public int jump(int[] nums) {
//         ans = Integer.MAX_VALUE;
        
//         dfs(nums, 0, 0, new int[nums.length]);
        
//         return ans;
//     }
    
//     private int dfs(int[] nums, int cur, int steps, int[] memo) {
//         if (cur >= nums.length - 1) {
//             ans = Math.min(ans, steps);
            
//             return ans;
//         }
//         if (memo[cur] != 0) return memo[cur];
        
//         int ret = Integer.MAX_VALUE;
//         for (int i = 1; i <= nums[cur]; i++) {
//             ret = Math.min(ret, dfs(nums, cur + i, steps + 1, memo));
//         } 
        
//         memo[cur] = ret == Integer.MAX_VALUE ? 0 : ret;
        
//         return memo[cur];
//     }
}
