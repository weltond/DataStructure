// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

/**
Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        
        int[] res = new int[2];
        
        int left = 0, right = nums.length - 1;
        
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) right = mid;
            else if (nums[mid] > target) right =  mid - 1;
            else left = mid + 1;
        }
        
        int start = nums[left] == target ? left : nums[right] == target ? right : -1;
        int end = -1;
        for (end = start; end < nums.length && end >= 0; end++) {
            if (nums[end] != target) {
                break;
            }
        }
        
        return new int[]{start, end >= 0 ? --end : -1};
    }
}
