// https://leetcode.com/problems/most-common-word/

/**
Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
*/

// 3ms (99.75%)
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet();
        for (String s : banned) 
            set.add(s);
        
        paragraph += ".";
        String ret = "";
        int max = 0;
        Map<String, Integer> map = new HashMap();
        StringBuilder sb = new StringBuilder();
        
        for (char c : paragraph.toCharArray()) {
            c = Character.toLowerCase(c);
            if (c < 'a' || c > 'z') {
                if (sb.length() != 0) {
                    String tmp = sb.toString();
                    if (set.contains(tmp)) {
                        sb = new StringBuilder();
                        continue;
                    }
                    
                    int freq = map.getOrDefault(tmp, 0) + 1;
                    map.put(tmp, freq);
                    if (freq > max) {
                        ret = tmp;
                        max = freq;
                    }
                    
                    sb = new StringBuilder();
                }
            } else {
                sb.append(c);
            }
            
        }
        //System.out.println(map);
        return ret;
    }
}

class Solution {
    // 4ms
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) return "";
        
        // NOTICE No.1
        paragraph += ".";   // in order to avoid missing the last word
        
        Set<String> set = new HashSet();
        for (String s : banned) set.add(s);
        Map<String, Integer> map = new HashMap();
        
        String ans = "";
        int maxFreq = 0;
        
        StringBuilder word = new StringBuilder();
        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                word.append(Character.toLowerCase(c));
            } 
            // NOTICE No.2
            else if (word.length() > 0) { // avoid empty string
                String str = word.toString();
                if (set.contains(str)) {
                    // NOTICE No.3
                    word = new StringBuilder();
                    continue;
                }
                
                int freq = map.getOrDefault(str, 0) + 1;
                //System.out.println(str + ", " + freq);
                map.put(str, freq);
                
                if (freq > maxFreq) {
                    maxFreq = freq;
                    ans = str;
                }
                
                word = new StringBuilder();
            }
        }
        
        return ans;
    }
}

class Solution {
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
