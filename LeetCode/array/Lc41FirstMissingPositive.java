// https://leetcode.com/problems/first-missing-positive/


/**
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.
*/


class Solution {
    // ========= Method 2: O(n) O(1) ===========
    // for any k positive numbers (duplicates allowed), the first missing positive number must be within [1,k+1]
    // 2ms (21.29%)
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        
        int k = quickPartition(nums);

        System.out.println();
        for (int i = 0; i < k; i++) {
            int tmp = Math.abs(nums[i]);
            if (tmp <= k) {
                nums[tmp - 1] = -Math.abs(nums[tmp - 1]);
            }
        }
        
        int res = k + 1;    // init res to be the last one.
        for (int i = 0; i < k; i++) {
            if (nums[i] > 0) {
                res = i + 1;
                break;
            }
        }
        
        return res;
    }
    
    private int quickPartition(int[] nums) {
        int i = 0, j = nums.length - 1;
        
        while (i <= j) {
            while (i <= j && nums[i] > 0) i++;
            while (j >= i && nums[j] <= 0) j--;
            
            if (i <= j) {
                swap(nums, i, j);
            }
        }
        
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    // ========= Method 1: O(n) O(n) ===========
    // 1ms (52.06%)
    // 1. find min, max positive in arr.
    // 2. Add to set.
//     public int firstMissingPositive(int[] nums) {
//         if (nums == null || nums.length == 0) return 1;
        
//         Set<Integer> set = new HashSet();
//         int minNum = Integer.MAX_VALUE, maxNum = Integer.MIN_VALUE, cnt = 0;
//         for (int i = 0; i < nums.length; i++) {
//             if (nums[i] <= 0) continue;
            
//             if (set.add(nums[i])) {
//                 cnt++;  // Avoid duplicate.
//             }
//             if (nums[i] < minNum) {
//                 minNum = nums[i];
//             }
//             if (nums[i] > maxNum) {
//                 maxNum = nums[i];
//             }
//         }
        
//         if (minNum != 1) return 1;
//         if (cnt == maxNum) return maxNum + 1;
        
//         for (int i = 2; i < maxNum; i++) {
//             if (!set.contains(i))  {
//                 return i;
//             }
//         }
        
//         return -1;  // Never executed.
//     }
}
