// https://leetcode.com/problems/palindrome-partitioning/
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList();
        if (s == null || s.length() == 0) return res;
        
        char[] arr = s.toCharArray();
        
        // 0 is start index of String s
        bt(arr, 0, new ArrayList<String>(), res);
        
        return res;
    }
    
    private void bt(char[] arr, int start, List<String> list, List<List<String>> res) {
        // base case is our start index equals to level
        // NOT the level
        if (start == arr.length) {
            res.add(new ArrayList(list));
            return;
        }
        
        // verify (0, i) is palindrome or not 
        for (int i = 0; i < arr.length - start; i++) {
            if (!isValid(arr, start, start + i)) {
                continue;
            }
            
            String s = new String(arr, start, i + 1);
            
            list.add(s);
            
            bt(arr, start + i + 1, list, res);
            
            list.remove(list.size() - 1);
        }
    }
    
    private boolean isValid(char[] arr, int start, int end) {
        if (start >= end) {
            return true;
        }
        
        if (arr[start] == arr[end]) {
            return isValid(arr, start + 1, end - 1);
        }
        
        return false;
    }
}
