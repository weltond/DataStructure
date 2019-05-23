// https://leetcode.com/problems/convert-to-base-2/

/**
Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).

The returned string must have no leading zeroes, unless the string is "0".

 

Example 1:

Input: 2
Output: "110"
Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
Example 2:

Input: 3
Output: "111"
Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
Example 3:

Input: 4
Output: "100"
Explantion: (-2) ^ 2 = 4
*/

class Solution {
    // ======= Method 1: Iteration ==========
    // 1ms (96.79%)
    public String baseNeg2(int n) {
        StringBuilder res = new StringBuilder();
        while (n != 0) {
            res.append(n & 1);
            
            n = -(n >> 1);
        }
        
        return res.length() == 0 ? "0" : res.reverse().toString();
    }
    
    // ======= Method 2: Recursion ==========
    // 1ms (96.79%)
    public String baseNeg2(int n) {
        if (n == 0 || n == 1) return Integer.toString(n);
        
        StringBuilder res = new StringBuilder();
        
        return baseNeg2(-(n >> 1)) + res.append(n & 1).toString();
    }
}
