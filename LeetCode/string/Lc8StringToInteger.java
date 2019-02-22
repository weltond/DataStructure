// https://leetcode.com/problems/string-to-integer-atoi/
class Solution {
    public int myAtoi(String str) {
        if (str.length() == 0 || str == null) return 0;
        int factor = 1;
        int base = 0;
        int i = 0;
        int n = str.length();
        
        // remove empty chars
        while (i < n && str.charAt(i) == ' ') i++;
        
        // get the sign
        if (i < n && (str.charAt(i) == '-' || str.charAt(i) == '+')) {
            factor = str.charAt(i++) == '+' ? 1 : -1;
        }
        
        // convert to int number
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            // MAX_VALUE = 2147483647
            // MIN_VALUE = -2147483648
            
            // prev * 10 > Integer.MAX_VALUE 
            // or
            // prev = Integer.MAX_VALUE / 10 (prev = 214748364) and verify cur char <= 7 
            if (base > Integer.MAX_VALUE / 10 || 
                (base == Integer.MAX_VALUE / 10 && str.charAt(i) > '7')) {
                return (factor == -1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            base = base * 10 + (str.charAt(i++) - '0');
        }
        
        return base * factor;
        
    }
}
