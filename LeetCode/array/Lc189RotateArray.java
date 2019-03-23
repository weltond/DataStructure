// https://leetcode.com/problems/rotate-array/

class Solution {
    
    // ========== Method 2: Iteration ===============
    // 0ms O(1) space
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        // nums = [1,2,3,4,5]; k = 2
        k = k % nums.length;
        // 1. nums = [5,4,3,2,1]
        swap(nums, 0, nums.length - 1);
        // 2. nums = [4,5,3,2,1]
        swap(nums, 0, k - 1);
        // 3. nums = [4,5,1,2,3]
        swap(nums, k, nums.length - 1);
    }
    
    private void swap(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = tmp;
        }
    }
    
    // =========== Method 1: Recursion ==============
    // 0ms O(1) space
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return;
        
        int size = nums.length;
        k = k % size;
        recursion(nums, k, 0, size - 1);
    }
    
    private void recursion(int[] nums, int k, int left, int right) {
        k = k % (right - left + 1);
        
        if (k == 0) return;
        
        if (left >= right) return;
        
        for (int i = 0; i < k; i++) {
            swap(nums, left + i, right - k + i + 1);
        }
        
        recursion(nums, k, left + k, right);
    }
    
    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
    
}
