// https://leetcode.com/problems/search-in-rotated-sorted-array/
// recommended (Template 3)
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return m;

            // right side of m is sorted
            if (nums[m] < nums[r]) {
                // target lies from m ~ r
                if (nums[r] >= target && target >= nums[m]) {   // equal!!
                    l = m;
                } else {
                    r = m;
                }
            } 
            // left side of l is sorted
            else if (nums[m] > nums[l]) {
                // target lies from l ~ m
                if (nums[l] <= target && target <= nums[m]) { // equal!!
                    r = m;
                } else {
                    l = m;
                }
            }
        }

        return nums[l] == target ? l : nums[r] == target ? r : -1;
    }
}

class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            
            // left is sorted
            if (nums[m] > nums[r]) {
                // target is between l and m
                if (nums[l] <= target && target <= nums[m]) {
                    r = m;
                } else {
                    l = m;
                }
            } 
            // right is sorted
            else {
                // 
                if (nums[m] <= target && target <= nums[r]) {
                    l = m;
                } else {
                    r = m;
                }
            }
        }
        
        return nums[l] == target ? l : (nums[r] == target ? r : -1) ;
    }
}

public class Solution {
    /**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] a, int target) {
        // write your code here
        if (a == null || a.length == 0) return -1;
        
        int l = 0, r = a.length - 1;
        
        while (l <= r) {
            int m = l + (r - l) / 2;
            
            // left sorted
            if (a[m] > a[r]) {
                if (target <= a[m] && a[l] <= target) {
                    return bs(a, target, l, m);
                } else {
                    l = m + 1;
                }
            } else {    // right sorted
                if (target >= a[m] && target <= a[r]) {
                    return bs(a, target, m, r);
                } else {
                    r = m - 1;
                }
            }
        }
        
        return -1;
    }
    
    private int bs(int[] a, int target, int l, int r) {
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (a[m] == target) return m;
            else if (a[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}

class Solution {
    // ======= Implement 2 ===========
    // 0ms
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (target == nums[mid]) return mid;
            if (nums[right] == target) return right;
            if (nums[left] == target) return left;
            
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
