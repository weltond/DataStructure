// https://leetcode.com/problems/majority-element/

/**
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
*/

class Solution {
    public int majorityElement(int[] nums) {
        int index = 0, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[index]) {
                count++;
            } else {
                count--;
            }
            
            if (count == 0) {
                index = i;
                count = 1;
            }
        }
        
        return nums[index];
    }
    // ============= Method 1: Naive ==================
    // 10ms (41.26%)
    public int majorityElement(int[] nums) {
        int size = nums.length;
        
        Map<Integer, Integer> map = new HashMap();
        
        for (int i = 0; i < size; i++) {
            int cnt = map.getOrDefault(nums[i], 0) + 1;
            if (cnt > size / 2) {
                return nums[i];
            }
            map.put(nums[i], cnt);
        }
        
        return -1;
    }
}
