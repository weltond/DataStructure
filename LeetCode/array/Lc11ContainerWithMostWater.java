// https://leetcode.com/problems/container-with-most-water/

/**
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.
The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
*/

class Solution {
    // ========= Method 2 O(n) ==========
    // area formed between the lines will always be limited by the height of the shorter line
    // 2ms (94.91%)
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        int i = 0, j = height.length - 1;
        int res = 0;
        while (i < j) {
            res = Math.max(res, (j - i) * (height[i] <= height[j] ? height[i++] : height[j--]));
        }
        
        return res;
    }
    // ========== Method 1: Brute Force ===========
    // 218ms (8.38%)
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        int i = 0, j = height.length - 1;
        int res = 0;
        for (i = 0; i < height.length; i++) {
            j = height.length - 1;
           while (i < j) {
                res = Math.max(res, (j - i) * Math.min(height[i], height[j--]));
            } 
        }
        
        
        return res;
    }
}
