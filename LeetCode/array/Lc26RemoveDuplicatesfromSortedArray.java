// https://leetcode.com/problems/remove-duplicates-from-sorted-array/

/**
Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
*/
class Solution {
    // 1ms (99.99%)
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        int fast = 1, slow = 0;
        
        for(; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
        }
        
        return slow + 1;
    }
}
