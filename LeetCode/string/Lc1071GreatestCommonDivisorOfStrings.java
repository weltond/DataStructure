// https://leetcode.com/problems/greatest-common-divisor-of-strings/

/**
For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)

Return the largest string X such that X divides str1 and X divides str2.

 

Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""
*/

class Solution {
    // ============= Method 1: Recursion ==============
    // 1ms (84.75%)
    public String gcdOfStrings(String str1, String str2) {
        return dfs(str1, str2);
    }
    
    private String dfs(String s1, String s2) {
        if (s1.length() == 0) return s2;
        if (s2.length() == 0) return s1;
        
        if (s1.equals(s2)) return s1;
        
        int len1 = s1.length(), len2 = s2.length();
        
        if (len1 > len2) {
            for (int i = 0; i < len2; i++) {
                if (s1.charAt(i) != s2.charAt(i)) return "";
            }
            String tmp = s1.substring(len2);
            
            return dfs(tmp, s2);
        } else if (len1 < len2) {
            return dfs(s2, s1);
        }
        
        return "";  // length same but not equals.
    }
}
