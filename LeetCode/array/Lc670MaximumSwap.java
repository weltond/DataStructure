// https://leetcode.com/problems/maximum-swap/

/**
Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
*/
class Solution {
    // ============= Method 1: Greedy ============
    // 0ms O(n)
    /**
    Intuition:

    At each digit of the input number in order, if there is a larger digit that occurs later, we know that the best swap must occur with the digit we are currently considering.
    */
    public int maximumSwap(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        
        for (int i = 0; i < arr.length; i++) {
            last[arr[i] - '0'] = i;    // the index i of the last occurrence of digit d if exists
        }
        
        // scan from left to right
        for (int i = 0; i < arr.length; i++) {
            // if a larger digit in the future, swap it with the largest such digit.
            for (int d = 9; d > arr[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = arr[i];
                    arr[i] = arr[last[d]];
                    arr[last[d]] = tmp;
                    return Integer.valueOf(new String(arr));
                }
            }
        }
        
        return num;
    }
}
