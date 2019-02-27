//https://leetcode.com/problems/plus-one/

class Solution {
    public int[] plusOne(int[] digits) {
        int fac = 1, cur = 0, size = digits.length;
        for (int i = size - 1; i >= 0; i--) {
            cur = digits[i] + fac;
            digits[i] = cur % 10;
            fac = (cur == 10) ? 1 : 0;
        }
        
        if (fac == 1) {
            int[] res = new int[size + 1];
            res[0] = 1;
            for (int i = 0; i < size; i++) {
                res[i + 1] = digits[i];
            }
            return res;
        }
        
        return digits;
    }
}
