// https://leetcode.com/problems/remove-invalid-parentheses/
// Inspired by Huahua
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList();
        if (s == null || s.length() == 0) {
            res.add("");
            return res;
        }
        
        int l = 0, r = 0;
        // count how many invalid parenthesis for left and right we need to remove
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            }
            if (c == ')') {
                if (l == 0) r++;
                else l--;
            }
        }

        dfs(s, 0, l, r, res);
        
        return res;
    }
    
    private void dfs(String s, int start, int l, int r, List<String> res) {
        // Nothing to delete
        if (l == 0 && r == 0) {
            if (isValid(s)) {
                res.add(s);
            }
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            // Only remove the first parenthesis if there are consecutive ones to avoid duplications
            if (start != i && s.charAt(i) == s.charAt(i - 1)) continue;
            
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                StringBuilder sb = new StringBuilder(s);
                
                // we should delete invalid right first and then left.
                if (r > 0 && s.charAt(i) == ')') {
                    String cur = sb.deleteCharAt(i).toString();
                    dfs(cur, i, l, r - 1, res);
                }else if (l > 0 && s.charAt(i) == '(') {
                    String cur = sb.deleteCharAt(i).toString();
                    dfs(cur, i, l - 1, r, res);
                }
            }
        }
    }
    
    // verify whether parenthesis are valid
    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            if (c == ')') count--;
            
            if (count < 0) return false;
        }
        
        return count == 0;
    }
}
