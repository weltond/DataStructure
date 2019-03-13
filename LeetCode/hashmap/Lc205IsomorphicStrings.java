// https://leetcode.com/problems/isomorphic-strings/

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null) return true;

        char[] arrs = new char[256];
        char[] arrt = new char[256];

        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            
            if (arrs[cs] != arrt[ct]) return false;
            
            arrs[cs] = (char)(i + 1);
            arrt[ct] = (char)(i + 1);
        }
        
        return true;
    }
}
