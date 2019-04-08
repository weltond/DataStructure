// https://leetcode.com/problems/next-permutation/

class Solution {
    // 1ms (99.81%)
    public void nextPermutation(int[] nums) {
        if (nums == null) return;
        // 1. find the first element that is smaller than its right from right to left
        int i;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) break;
        }
        
        // if not found, sort in ascending order and return
        if (i == -1) {
            Arrays.sort(nums);
            return;
        }
        
        // 2. find the smallest element that is larger than i-th element
        int tmp = nums[i], smallest = i + 1;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > tmp && nums[j] <= nums[smallest]) {
                smallest = j;
            }
        }
        
        // swap
        int t = nums[i];
        nums[i] = nums[smallest];
        nums[smallest] = t;
        
        // sort the rest from i+1
        Arrays.sort(nums, i + 1, nums.length);
    }
}
