// https://leetcode.com/problems/add-binary/

class Solution {
    public String addBinary(String a, String b) {
        int lena = a.length() - 1;
        int lenb = b.length() - 1;
        int cnt = 0, carry = 0;
        StringBuilder sb = new StringBuilder();
        
        while (lena >= 0 || lenb >= 0) {
            cnt = 0;
            cnt += (lena >= 0) ? a.charAt(lena) - '0' : 0;
            cnt += (lenb >= 0) ? b.charAt(lenb) - '0' : 0;
            cnt += carry;
            
            carry = cnt / 2;
            cnt = cnt % 2;
            
            sb.append(cnt);
            
            lena--;
            lenb--;
        }
        
        if (carry == 1) sb.append(1);
        
        return sb.reverse().toString();
    }
}
