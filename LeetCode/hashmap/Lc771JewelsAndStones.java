//  https://leetcode.com/problems/jewels-and-stones/

class Solution {
    public int numJewelsInStones(String J, String S) {
        if (J == null || S == null) return 0;
        
        char[] arr = new char[256];
        int cnt = 0;
        for (int i = 0; i < J.length(); i++) {
            char c = J.charAt(i);
            arr[c - 'A'] = 1;
        }
        
        for (int i = 0; i < S.length(); i++) {
            char d = S.charAt(i);
            if (arr[d - 'A'] != 0) {
                cnt++;
            }
        }
        
        return cnt;
    }
}
