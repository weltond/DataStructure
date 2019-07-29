//https://leetcode.com/problems/plus-one/
class Solution {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) return digits;
        
        int f = 0;
        
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = (i == digits.length - 1 ? 1 : 0) + digits[i] + f;
            
            digits[i] = sum % 10;
            f = sum / 10;
            if (sum < 10) {
                return digits;
            }
            
        }
        
        int[] arr = new int[digits.length + 1];
        arr[0] = f;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = digits[i - 1];
        }
        
        return arr;
    }
}

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
