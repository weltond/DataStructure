//  https://leetcode.com/problems/remove-element/

/**
Given nums = [0,1,2,2,3,0,4,2], val = 2,

Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.

Note that the order of those five elements can be arbitrary.

It doesn't matter what values are set beyond the returned length.
*/
class Solution {
    // 0ms
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            while (left < right && nums[right] == val) right--;
            while (left < right && nums[left] != val) left++;
            
            if (left < right) {
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;

                right--;
                left++;
            }

        }
        
        return nums[right] == val ? right : right + 1;
    }
}
