class Solution {
    public String minWindow(String s, String t) {
        // corner case
        if (s.length() < t.length()) return "";
        
        // calculate freq in string t
        Map<Character, Integer> map = genDict(t);
        // slow is first index of starting res
        // minVal is the minimum length
        // matchCount is matching count of t in s
        int slow = 0, index = 0, minVal = Integer.MAX_VALUE, matchCount = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            char ch = s.charAt(fast);
            Integer count = map.get(ch);
            // if not in t, ignore
            if (count == null) continue;
            
            // if in t
            map.put(ch, count - 1);
            // if count was 1, which means find the matched char
            if (count == 1) matchCount++;
            
            // if matchCount = ditc size which means all values in dict are 0 now.
            // notice here we use dict size instead of t size
            // because there may be duplicated char in t
            while (matchCount == map.size()) {
                if (minVal > fast - slow + 1) {
                    minVal = fast - slow + 1;
                    index = slow;
                }
                //minVal = Math.min(minVal, fast - slow + 1);
                
                char cur = s.charAt(slow++);
                Integer cnt = map.get(cur);
                if (cnt == null) {
                    //slow++;
                    continue;
                }
                // if cur is in dict, then we should add its value in dict
                // because next step it's gonna be out of our slow-fast range
                map.put(cur, cnt + 1);
                
                // if cnt was 0, which means after this step,
                // slow-fast range won't contain t
                if (cnt == 0) {
                    matchCount--;
                }               
            }
        }
        
        return minVal == Integer.MAX_VALUE ? "" : s.substring(index, index + minVal);
    }
    
    private Map<Character, Integer> genDict(String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map;
    }
}
