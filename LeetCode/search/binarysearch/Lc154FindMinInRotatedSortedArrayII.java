// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

// ============ Template II ===============
class Solution {
    public int findMin(int[] nums) {
        if (nums == null) return -1;
        
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid + 1;
            else if (nums[mid] < nums[right]) right = mid;
            else right--;
        }
        return nums[left];
    }
}

// ============ Template III ===============
class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        
        int left = 0, right = nums.length - 1;
        
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[left]) {
                if (nums[mid] > nums[right]) {
                    left = mid + 1;
                } else {
                    return nums[left];
                }
            } else if (nums[mid] < nums[left]) {
                if (nums[mid - 1] > nums[mid]) return nums[mid];
                else right = mid - 1;
            } else {
                if (nums[mid] > nums[right]) left = mid + 1;
                else left++;
            }
        }
        
        return nums[left] < nums[right] ? nums[left] : nums[right];
    }
}
