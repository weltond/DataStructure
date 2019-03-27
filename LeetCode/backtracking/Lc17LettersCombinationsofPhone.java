// 

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList();
        if (digits == null || digits.length() == 0) return ans;
        
        Map<Character, String> map = new HashMap();
        map.put('1', "*");
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        
        bt(digits.toCharArray(), 0, map, new StringBuilder(), ans);
        
        return ans;
    }
    
    private void bt(char[] arr, int level, Map<Character, String> map, StringBuilder sb, List<String> res) {
        // reach the end
        if (level == arr.length) {
            res.add(sb.toString());
            return;
        }
 
        String s = map.get(arr[level]);
        // make sure there is a valid input
        while (s == null) {
            s = map.get(arr[++level]);
        }
        int len = s.length();
        
        // iterate all letters 
        for (int i = 0; i < len; i++) {
            sb.append(s.charAt(i));
            bt(arr, level + 1, map, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        
    }
}
