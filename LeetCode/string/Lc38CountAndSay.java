// https://leetcode.com/problems/count-and-say/

/**
1.     1
2.     11
3.     21
4.     1211
5.     111221
*/
class Solution {
    public String countAndSay(int n) {
        if (n <= 0 || n > 30) return null;
        String s = "1";
        for (int i = 2; i <= n; i++) {
            s = get(s);
        }
        
        return s;
    }
    
    private String get(String s) {
        StringBuilder sb = new StringBuilder();
        
        char c = s.charAt(0);
        
        int cnt = 1;
        
        for (int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == c) {
                cnt++;
            } else {
                sb.append(cnt).append(c);
                c = cur;
                cnt = 1;
            }
        }
        
        sb.append(cnt).append(c);
        return sb.toString();
    }
}
