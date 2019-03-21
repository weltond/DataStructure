// https://leetcode.com/problems/first-unique-character-in-a-string/

class Solution {
    public int firstUniqChar(String s) {
        if (s == null) return -1;
        
        Map<Character, Integer> map = new HashMap();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, -1);
                continue;
            }
            map.put(c, i);
        }
        
        int res = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            if (e.getValue() == -1) continue;
            res = Math.min(res, e.getValue());
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
