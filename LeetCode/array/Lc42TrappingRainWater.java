// https://leetcode.com/problems/trapping-rain-water/

/**
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.
Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
*/

class Solution {
    // ======== Method 2: DP ==========
    // 1ms (99.37%) O(n) O(n)
    public int trap(int[] height) {
       if (height == null || height.length == 0) return 0;
        int[] maxL = new int[height.length];
        int[] maxR = new int[height.length];
        
        maxL[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            maxL[i] = Math.max(height[i], maxL[i - 1]);
        }
        maxR[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxR[i] = Math.max(height[i], maxR[i + 1]);
        }
        
        int ans = 0;
        for (int i = 0; i < height.length - 1; i++) {
            ans += Math.min(maxR[i], maxL[i]) - height[i];
        }
        
        return ans;
    }
    
    // ======== Method 1: Brute Force ==========
    // 59ms(5.53%) O(n2) O(1)
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int maxL = 0, maxR = 0, j = 0;
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            j = i;
            maxL = 0;
            maxR = 0;
            while (j >= 0) {
                maxL = Math.max(maxL, height[j--]);
            }
            j = i;
            while (j < height.length) {
                maxR = Math.max(maxR, height[j++]);
            }
            
            int cur = Math.min(maxL, maxR) - height[i];
            ans += cur;
        }
        
        return ans;
    }
}
