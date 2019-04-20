// https://leetcode.com/problems/sort-colors/

/**
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
*/

class Solution {
    // 0ms
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) return;
        
        int red = 0, blue = nums.length - 1, i = 0; //red's left is red, blue's right is blue. (all excluding its pointer)
        
        while (blue >= i) { //include equal because blue pointer is excluded
            if (nums[i] == 0) {
                swap(nums, i, red);
                red++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, blue);
                blue--;
            }
        }
        
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
