## [30. Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)

You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:
```
Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
```
Example 2:
```
Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
```
## Answer
### Method 1 - Sliding Window - :rabbit: 8ms (93.48%)
```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList();
        int lens = s.length();
        if (lens == 0 || words.length == 0 || lens < words[0].length() * words.length) return res;
        
        int lenm = words.length;
        int w1 = words[0].length();
        
        Map<String, Integer> map = new HashMap(), curMap = new HashMap();
        
        for (String x : words) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        
        String str = null, tmp = null;

        for (int i = 0; i < w1; i++) {
            int cnt = 0;
            int start = i;
            for (int r = i; r + w1 <= lens; r += w1) {
                str = s.substring(r, r + w1);
                
                if (map.containsKey(str)) {
                    curMap.put(str, curMap.getOrDefault(str, 0) +1);
                    if (curMap.get(str) <= map.get(str)) cnt++;
                    
                    while (curMap.get(str) > map.get(str)){
                        tmp = s.substring(start, start + w1);                        
                        start += w1;
                        curMap.put(tmp, curMap.get(tmp) - 1);

                        if (curMap.get(tmp) < map.get(tmp)) cnt--;
                    }
                    
                    if (cnt == lenm) {
                        res.add(start);
                        tmp = s.substring(start, start + w1);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        start += w1;
                        cnt--;
                    }
                } else {
                    curMap.clear();
                    cnt = 0;
                    start = r + w1;
                }
            }
            curMap.clear();
        }
        
        return res;
    }
}
```
