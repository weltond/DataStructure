// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

// Gitbook template: https://app.gitbook.com/s/1yBzuwxqO90h7a4SnmnK/basic-algorithms/binary-search#154.-find-minimum-in-rotated-sorted-array-ii
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r - 1) {
            // de-dup
            while (l < r && nums[l] == nums[l + 1]) {
                l++;
            }
            while (l < r && nums[r] == nums[r - 1]) {
                r--;
            }

            int m = l + (r - l) / 2;
            if (nums[m] > nums[r]) l = m;
            else r = m;
        }

        return Math.min(nums[l], nums[r]);
    }
}

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
