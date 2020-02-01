// https://leetcode.com/problems/search-insert-position/

/**
Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
*/
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        
        int l = 0, r = nums.length - 1;
        
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return m;
            else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        
        return nums[l] < target ? l + 1: l;
    }
}
class Solution {
    // 0ms
    public int searchInsert(int[] nums, int target) {
        if (nums == null) return 0;
        
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return (nums[left] >= target ? left : left + 1);
    }
}
