// https://leetcode.com/problems/duplicate-zeros/


/**
Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.

Note that elements beyond the length of the original array are not written.

Do the above modifications to the input array in place, do not return anything from your function.

 

Example 1:

Input: [1,0,2,3,0,4,5,0]
Output: null
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
Example 2:

Input: [1,2,3]
Output: null
Explanation: After calling your function, the input array is modified to: [1,2,3]
 

Note:

1 <= arr.length <= 10000
0 <= arr[i] <= 9
*/

class Solution {
    // ======== Method 2: O(n) O(1) ===========
    // Iterate through the array backwards. 
    // You know whether an integer should be written or not based on how many zeroes are remaining in the array.
    // 1ms (94.61%)
    public void duplicateZeros(int[] arr) {
        int j = 0;
        for (int i = 0; i < arr.length; i++, j++) {
            if (arr[i] == 0)
                j++;
        }
        
        for (int i = arr.length - 1; i >= 0; i--) {
            if (--j < arr.length) {
                arr[j] = arr[i];
            }

            if (arr[i] == 0 && --j < arr.length) {
                arr[j] = 0;
            }
        }
    }
    
    // ======= Method 1: O(n) O(n) =======
    // 1ms (94.61%)
    public void duplicateZeros(int[] arr) {
        int[] tmp = new int[arr.length];
        
        int i = 0, j = 0;
        while (i < arr.length && j < arr.length) {
            tmp[j++] = arr[i];
            if (arr[i++] == 0) {
                j++;
            }
        }
        
        for (i = 0; i < arr.length; i++) {
            arr[i] = tmp[i];
        }
    }
}
