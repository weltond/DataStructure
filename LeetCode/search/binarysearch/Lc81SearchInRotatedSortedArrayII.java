// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

// Since we will have some duplicate elements in this problem, 
// it is a little tricky because sometimes we cannot decide whether to go to the left side or right side. 
// So for this condition, I have to probe both left and right side simultaneously to decide which side we need to find the number. 
// Only in this condition, the time complexity may be O(n). The rest conditions are always O(log n).

// For example:

// input: 113111111111, Looking for target 3.

class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target || nums[left] == target || nums[right] == target) return true;
            
            if (nums[mid] > nums[right]) {  // left is sorted
                if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[right]) {   // right is sorted
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left++;
            }
        }
        
        return false;
    }
}

class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        
        int left = 0;
        int right = nums.length - 1;
        
        return helper(nums, target, left, right);
    }
    
    public boolean helper(int[] nums, int target, int left, int right) {
        if (left > right) return false;
        
        int mid = left + (right - left) / 2;

        if (nums[mid] == target || nums[left] == target || nums[right] == target) return true;
        
        //If we know for sure left side is sorted or right side is unsorted
        if (nums[mid] < nums[left]) {
            if (target < nums[right] && target > nums[mid]) {
                return helper(nums, target, mid + 1, right);
            } else {
                return helper(nums, target, left, mid - 1);
            }
        }
        //If we know for sure right side is sorted or left side is unsorted
        else if (nums[mid] > nums[left]){
            if (target > nums[left] && target < nums[mid]) {
                return helper(nums, target, left, mid - 1);
            } else {
                return helper(nums, target, mid + 1, right);
            }
        } 
        //If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
        //any of the two sides won't change the result but can help remove duplicate from
        //consideration, here we just use left++ but right-- works too
        else {
            return helper(nums, target, left + 1, right);
        }
    }
}
