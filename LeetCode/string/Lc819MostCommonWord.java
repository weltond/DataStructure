// https://leetcode.com/problems/most-common-word/

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) return "";
        
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
            } else if (word.length() > 0) { // avoid empty string
                String str = word.toString();
                if (set.contains(str)) {
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
