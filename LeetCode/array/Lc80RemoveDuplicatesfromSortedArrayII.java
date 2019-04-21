// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
/**
Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.
*/
class Solution {
    // 1ms (97.94%)
    public int removeDuplicates(int[] a) {
        if(a.length < 3)  return a.length;

        int end = 1;
        for(int i = 2; i < a.length; i++) {
            if(a[i] != a[end - 1]) {
                end++;
                a[end] = a[i];
            }
        }
        return end + 1;
    }
    // 3ms (80.11%)
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        
        int slow = -1, fast = 0;
        Map<Integer, Integer> map = new HashMap();  // <val, freq>
        
        for (; fast < nums.length; fast++) {
            int freq = map.getOrDefault(nums[fast], 0) + 1;
            map.put(nums[fast], freq);
            if (freq <= 2) {
                nums[++slow] = nums[fast];
            }
        }
        
        return slow + 1;
    }
}
