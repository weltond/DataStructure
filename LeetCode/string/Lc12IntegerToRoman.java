// https://leetcode.com/problems/integer-to-roman/

/**
Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
*/

public class Solution {
    /**
     * @param n: The integer
     * @return: Roman representation
     */
    public String intToRoman(int n) {
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        int idx = 0;
        
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int times = n / values[idx];
            n -= times * values[idx];
            
            for (; times > 0; times--) {
                sb.append(romans[idx]);
            }
            
            idx++;
        }
        
        return sb.toString();
    }
}

class Solution {
    // ========== Method 2 ===========
    // 3ms (100%)
    public String intToRoman(int num) {
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                result.append(romans[i]);
            }
        }
        
        return result.toString();
    }
    // ============ Method 1 =============
    // 4ms (88.89%)
    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[(num % 10)];
    }
}
