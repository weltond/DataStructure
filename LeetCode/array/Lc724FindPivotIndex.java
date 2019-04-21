//https://leetcode.com/problems/find-pivot-index/

/**
Input: 
nums = [1, 7, 3, 6, 5, 6]
Output: 3
Explanation: 
The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
Also, 3 is the first index where this occurs.
*/

class Solution {
    // ==== Sol 1: First attempt =====
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
        int[] prefixSum = build(nums);

        for (int i = 1; i < prefixSum.length; i++) {
            if (prefixSum[prefixSum.length - 1] - prefixSum[i] == prefixSum[i - 1]) {
                return i - 1;
            }
        }
        return -1;
    }
    
    private int[] build(int[] nums) {
        int[] arr = new int[nums.length + 1];   // first num is always 0.
        
        for (int i = 1; i <= nums.length; i++) {
            arr[i] = nums[i - 1] + arr[i - 1];    
        }
        
        return arr;
    }
    
    // ==== Sol 2: More Elegant =====
    public int pivotIndex(int[] nums) {
        int sum = 0, leftsum = 0;
        for (int x: nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) return i;
            leftsum += nums[i];
        }
        return -1;
    }
}
