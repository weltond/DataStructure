// https://leetcode.com/problems/word-subsets/

/**
Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
Output: ["google","leetcode"]
*/
class Solution {
    // 15ms (98.25%)
    public List<String> wordSubsets(String[] a, String[] b) {
        if (b == null || a == null) return new ArrayList();
        
        // combine b into a single word. For e.g. ["e", "oo"] -> ["eoo"]
        int[] bcombine = new int[26];
        for (String str : b) {
            int[] tmp = new int[26];
            for (char c : str.toCharArray()) {
                tmp[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                bcombine[i] = Math.max(bcombine[i], tmp[i]);
            }
        }
        
        List<String> res = new ArrayList();
        for (String s : a) {
            int[] tmp = new int[26];
            boolean match = true;
            // calculate occurence of each character in a
            for (char c : s.toCharArray()) {
                tmp[c - 'a']++;
            }
            // if not sufficient occurence of each character in bcombine, it means not match
            for (int i = 0; i < 26; i++) {
                if (tmp[i] < bcombine[i]) {
                    match = false;
                    break;
                }
            }
            
            if (match) res.add(s);
        }
        
        return res;
    }
}
