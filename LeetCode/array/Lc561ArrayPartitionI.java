//https://leetcode.com/problems/array-partition-i/

class Solution {
    public int arrayPairSum(int[] nums) {
        // range is [-10000, 10000]
        int[] arr = new int[20001];
        
        for (int i = 0; i < nums.length; i++) {
            // make arr valid
            arr[nums[i] + 10000]++;
        }
        
        boolean odd = true;
        int sum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] > 0) {
                if (odd) {
                    sum += i - 10000;
                }
                
                odd = !odd;
                arr[i]--;
            }
        }
        
        return sum;
    }
}
