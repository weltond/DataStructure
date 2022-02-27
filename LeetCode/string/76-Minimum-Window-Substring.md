## [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

```
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
```

Example 2:

```
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
```

Example 3:

```
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
```


**Constraints**:

- m == s.length
- n == t.length
- 1 <= m, n <= 105
- s and t consist of uppercase and lowercase English letters.
 

**Follow up**: Could you find an algorithm that runs in O(m + n) time?

## Answer

### Method 1 - Sliding Window - 8ms (83.93%)
```java
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = build(t);
        
        int start = 0, end = 0, sStart = 0, maxLen = Integer.MAX_VALUE;
        int len = s.length();
        int cnt = 0, total = map.size();

        while (end < len) {
            char c = s.charAt(end++);
            
            if (map.containsKey(c)) {
                int freq = map.get(c);
                if (freq == 1) {
                    cnt++;
                }
                map.put(c, freq - 1);
            }

            while (total == cnt) {
                if (maxLen > end - start) {
                    maxLen = end - start;
                    sStart = start;
                }
                
                char c2 = s.charAt(start++);
                if (map.containsKey(c2)) {
                    int f = map.get(c2);
                    if (f == 0) {
                        cnt--;
                    }
                    
                    map.put(c2, f + 1);
                }
            }
        }
        
        return maxLen == Integer.MAX_VALUE ? "" : s.substring(sStart, sStart + maxLen);
    }
    
    private Map<Character, Integer> build(String t) {
        Map<Character, Integer> map = new HashMap();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        return map;
    }
}
```
## Old Answer

```java
class Solution {
    public String minWindow(String s, String t) {
        int start = 0, realStart = 0, minSize = Integer.MAX_VALUE, idx = 0;

        Map<Character, Integer> map = buildMap(t);
        int remainCnt = map.size(); // remain count is NOT length of t, but size of map!!

        char[] chars = s.toCharArray();

        while (idx < chars.length) {
            char c = chars[idx];

            // found char in t
            if (map.containsKey(c)) {
                if (map.get(c) == 1) {
                    remainCnt--;
                }
                
                map.put(c, map.get(c) - 1);
            }

            // if chars in t has all been found
            while (remainCnt == 0 && start < chars.length) {
                // update min size
                if (idx - start + 1 < minSize) {
                    minSize = idx - start + 1;
                    realStart = start;
                }

                char k = chars[start++];
                if (map.containsKey(k)) {
                    int charRemain = map.get(k);

                    if (charRemain == 0) {
                        remainCnt += 1;
                    }
                    
                    map.put(k, charRemain + 1);
                }
            }

            idx++;
        }

        return minSize == Integer.MAX_VALUE ? "" : s.substring(realStart, realStart + minSize);
    }

    private Map<Character, Integer> buildMap(String t) {
        char[] chars = t.toCharArray();
        int idx = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (idx < chars.length) {
            char c = chars[idx++];
            map.put(c, map.getOrDefault(c, 0) + 1);    
        }

        return map;
    }
}

class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || t.length() == 0 || s.length() == 0) return "";
        
        Map<Character, Integer> map = new HashMap();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        int start = 0, end = 0, lens = s.length(), len = Integer.MAX_VALUE, ts = 0, te = 0, cnt = 0;
        
        while (end < lens) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                int f = map.get(c);
                if (f == 1) cnt++;
                map.put(c, f - 1);
            }
            end++;
            while (cnt == map.size()) {
                char tmp = s.charAt(start);
                if (map.containsKey(tmp)) {
                    int freq = map.get(tmp);
                    if (freq == 0) cnt--;
                    map.put(tmp, freq + 1);
                }
                
                if (end - start < len) {
                    len = end - start;
                    ts = start;
                    te = end;
                }
                start++;
            }
        }
        
        return s.substring(ts, te);
    }
}

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
```
