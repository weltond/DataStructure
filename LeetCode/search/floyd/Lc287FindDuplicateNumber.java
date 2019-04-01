// https://leetcode.com/problems/find-the-duplicate-number/
/**
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/
class Solution {
	// ========== Method 1 ===========
    // Floyd's Tortoise and Hare (Cycle Detection)
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        
        // Like LinkedList slow and fast pointer
        // slow move 1 step, fast move 2 steps
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        // once detect a cycle
        // reset the slow
        // slow and fast each move 1 step
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
	
	// ========== Method 2 ===========
	// Binary Search
	public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;    // range from 1 - n
        // do binary search for 1 - n
        // think of low, high and mid as values, not index!
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) ++cnt;    // cnt numbers that value < mid
            }
            if (cnt <= mid) low = mid + 1;  // if cnt <= mid, it means there is no duplicate on mid's left side
            else high = mid - 1;    // if cnt > mid, it means there exist duplicate value on mid's right side
        }
        return low;
    }
	
}
