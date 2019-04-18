// https://leetcode.com/problems/search-in-rotated-sorted-array/
class Solution {
    // ======= Implement 2 ===========
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) return mid;
            else if (nums[mid] < nums[right]) { // mid's right is sorted
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {    // mid's left is sorted
                if (nums[left] <= target && nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
    
    // ======= Implement 1 ===========
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        
        int left = 0;
        int right = nums.length - 1;
        
        return helper(nums, target, left, right);
    }
    
    public int helper(int[] nums, int target, int left, int right) {
        if (left > right) return -1;
        
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) return mid;
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        
        if (nums[mid] < nums[left]) {
            if (target < nums[right] && target > nums[mid]) {
                return helper(nums, target, mid + 1, right);
            } else {
                return helper(nums, target, left, mid - 1);
            }
        } else {
            if (target > nums[left] && target < nums[mid]) {
                return helper(nums, target, left, mid - 1);
            } else {
                return helper(nums, target, mid + 1, right);
            }
        }
    }
}
