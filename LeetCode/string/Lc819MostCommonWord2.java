// https://leetcode.com/problems/most-common-word/

/**
Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
*/

class Solution {
    // Approach 2
    // 4ms
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) return "";
        
        paragraph += ".";
        Set<String> set = new HashSet();
        for (int i = 0; i < banned.length; i++) {
            set.add(banned[i]);
        }
        
        Map<String, Integer> map = new HashMap();
        
        int maxFreq = 0;
        String ans = "";
        StringBuilder sb = new StringBuilder();
        
        char[] arr = paragraph.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));                
            }  else if (sb.length() > 0) {
                String str = sb.toString();
                
                if (set.contains(str)) {
                    sb = new StringBuilder();
                    continue;
                }
                //System.out.println(str);
                int freq = map.getOrDefault(str, 0) + 1;
                //System.out.println(freq + ", " + maxFreq);
                if (freq > maxFreq) {
                    maxFreq = freq;
                    ans = str;
                }
                
                map.put(str, freq);
                
                sb = new StringBuilder();
            }
        }
        
        return ans;
        
    }
    // ============ Approach 1: My code =============
    // 5ms (96.84%)
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || banned == null) return null;
        
        Map<String, Integer> map = new HashMap();
        Set<String> set = new HashSet();
        
        for (String s : banned) {
            set.add(s);
        }
        int max = 0;
        String ans = null;
        StringBuilder sb = new StringBuilder();
        
        paragraph += ".";
        for (int i = 0, len = paragraph.length(); i < len; i++) {
            char c = paragraph.charAt(i);
            if (c > 'z' || c < 'A') {
                String tmp = sb.toString().toLowerCase();
                
                /**
                "Bob. hIt, baLl"
                ["bob", "hit"]
                tmp could be "" 
                */
                if (set.contains(tmp) || tmp.equals("")) {  
                    sb = new StringBuilder();
                    continue;
                }
                int freq = map.getOrDefault(tmp, 0) + 1;
                if (freq > max) {
                    max = freq;
                    ans = tmp;
                }
                map.put(tmp, freq);
                sb = new StringBuilder();
                //System.out.println(tmp + ", " + freq + ", " + max);
            } else {
                sb.append(c);
            }
        }
        
        return ans;
    }
}
