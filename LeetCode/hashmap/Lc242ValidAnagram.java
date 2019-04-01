// https://leetcode.com/problems/valid-anagram/

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
