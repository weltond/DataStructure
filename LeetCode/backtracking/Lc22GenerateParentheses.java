// https://leetcode.com/problems/generate-parentheses/

class Solution {
    // ============== DFS ================
    // 1ms (99.83%)
    public List<String> generateParenthesis(int n) {
        if (n < 1) return new ArrayList();
        
        List<String> res = new ArrayList();
        
        // number of pairs, levels, number of left, number of right, each result, total result
        dfs(n, 0, 0, 0, new StringBuilder(), res);
        
        return res;
    }
    
    private void dfs(int n, int lvl, int left, int right, StringBuilder sb, List res) {
        if (lvl == 2 * n) {
            res.add(sb.toString());
            return;
        }
        
        // if number of left parentheses is less than n. left can be added.
        if (left < n) {
            sb.append("(");
            dfs(n, lvl + 1, left + 1, right, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        // if number of right parenthese is less than left, right can be addded.
        if (right < left) {
            sb.append(")");
            dfs(n, lvl + 1, left, right + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
