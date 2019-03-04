//https://leetcode.com/problems/minimum-size-subarray-sum/

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        // return bruteForce(s, nums);
        return twoPointers(s, nums);
    }
    // Method 2: Two Pointers
    // 2ms
    private int twoPointers(int s, int[] nums) {
        if (nums == null) return 0;
        
        int left = 0, sum = 0, res = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            
            while (sum >= s) {
                // left pointer can safely be incremented, since,
                // the min subarray starting with this index with sum >= s 
                // has been achieved.
                res = Math.min(res, i - left + 1);
                sum -= nums[left++];
            }
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    } 
    
    // Method 1: Brute Force
    // 63ms
    private int bruteForce(int s, int[] nums) {
        if (nums == null) return 0;
        
        int sum = 0, res = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            sum = nums[i];
            if (sum >= s) {
                res = Math.min(res, 1);
                break;
            }
            for (int j = i + 1;j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
