// https://leetcode.com/problems/valid-anagram/
// O(1) space
public class Solution {
    /**
     * @param s: The first string
     * @param t: The second string
     * @return: true or false
     */
    public boolean anagram(String s, String t) {
        int cor = 0;
        // System.out.println(97 ^ 98); //3
        // System.out.println(121 ^ 122);   //3
        int hash = 0;
        for (char c : s.toCharArray()) {
            cor ^= c;
            hash += c * c % 26;
        }
        
        for (char c : t.toCharArray()) {
            cor ^= c;
            hash -= c * c % 26;
        }
        
        return cor == 0 && hash == 0;
    }
}

class Solution {
    // 4ms (71.32%)
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        int[] arr = new int[26];
        
        int len = s.length();
        for (int i = 0; i < len; i++) {
            arr[s.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < len; i++) {
            if (--arr[t.charAt(i) - 'a'] < 0) return false;
        }
        
        return true;
    }
}
