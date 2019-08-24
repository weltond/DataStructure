// https://leetcode.com/problems/isomorphic-strings/

class Solution {
    // 3ms
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


class Solution {
    // 16ms (21.04%)
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap();
        Map<Character, Character> map2 = new HashMap();
        for (int i = 0, len = s.length(); i < len; i++) {
            char a = s.charAt(i), b = t.charAt(i);
            if (!map.containsKey(a) && !map2.containsKey(b)) {
                map2.put(b, a);
                map.put(a, b);
            }
            else if ((map.containsKey(a) && map.get(a) != b) || (map2.containsKey(b) && map2.get(b) != a)) return false;
        }
        
        return true;
    }
}

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null) return true;
        
        
        Map<Character, Character> map = new HashMap();

        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            
            if (!map.containsKey(cs)) {
                if (map.containsValue(ct)) return false;
                map.put(cs, ct);
            } else {
                if (!map.get(cs).equals(ct)) return false;
            }

        }
        
        return true;
    }
}
