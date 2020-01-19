// https://leetcode.com/problems/multiply-strings/

/**
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/

class Solution {
    public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        int[] res = new int[len1 + len2];
        
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                char c1 = num1.charAt(i), c2 = num2.charAt(j);
                int v1 = (int)(c1 - '0'), v2 = (int) (c2 - '0');
                
                int total = v1 * v2 + res[i + j + 1];
                
                res[i + j + 1] = total % 10;
                res[i + j] += total / 10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (sb.length() == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
class Solution {
    // Start from right to left, perform multiplication on every pair of digits, and add them together.
    // 4ms (89.98%)
    public String multiply(String nums1, String nums2) {
        int n = nums1.length(), m = nums2.length();
        
        int[] pos = new int[m + n]; // i, j ---> pos[i+j, i+j+1]
        
        // build result 
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int mul = (nums1.charAt(i) - '0') * (nums2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                
                int sum = mul + pos[p2];
                
                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }
        
        // convert from int array to string
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0, len = pos.length; i < len; i++) {
            if (sb.length() == 0 && pos[i] == 0) continue;
            sb.append(pos[i]);
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
