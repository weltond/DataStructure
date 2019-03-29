// https://leetcode.com/problems/3sum-closest/

class Solution {
    // ============ Two Pointers ============
    // 5ms (98.86%)
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        
        int res = nums[0] + nums[1] + nums[2];  // Integer.MAX_VALUE will cause overflow
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            int sum = target - nums[i];
            while (left < right) {
                if (nums[left] + nums[right] == sum) return target;
                else {
                    if (Math.abs(nums[left] + nums[right] - sum) < Math.abs(target - res)) {
                        res = nums[left] + nums[right] + nums[i];
                    }
                    if (nums[left] + nums[right] < sum) left++;
                    else right--;
                }
            }
        }
        
        return res;
    }
}
