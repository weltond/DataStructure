// https://leetcode.com/explore/learn/card/binary-search/126/template-ii/949/

class Solution {
    public int findMin(int[] nums) {
        if (nums == null) return -1;
        
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < nums[right]) right = mid;
            else left = mid + 1;
        }
        
        return nums[left];
    }
}
