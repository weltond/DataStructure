//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r - 1) {
            int m = l + (r - l) / 2;

            // r is the target
            if (nums[m] > nums[r]) l = m;
            else r = m;
        }
        
        // l + 1 == r
        return Math.min(nums[l], nums[r]);
    }
}

class Solution {
    public int findMin(int[] nums) {
        if (nums == null) return -1;
        
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < nums[right]) right = mid;
            else left = mid + 1;
        }
        
        return nums[left];
    }
}
