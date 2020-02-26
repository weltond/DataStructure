## [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a string, find the length of the longest substring without repeating characters.

Example 1:

```
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
```

Example 2:

```
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```

Example 3:

```
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

## Answer
### Method 2 - HashMap with Two pointer

- `start` or `left` pointer means the starting index (exclusive) of longest substring

#### Approach 2 - Array - :rocket: 3ms (93.89%)

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int len = s.length(), start = -1;
        int[] arr = new int[128];
        Arrays.fill(arr, -1);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            start = Math.max(start, arr[c]);
            res = Math.max(res, i - start);
            arr[c] = i ;
        }
        
        return res;
    }
}
```

#### Approach 1 - HashMap - :rocket: 5ms (88.02%)
```java
public class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        if (s == null || s.length() == 0) return 0;
        
        int start = -1, len = s.length(), res = 0;
        
        Map<Character, Integer> map = new HashMap();
        
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c));
            } else {
                start = Math.max(start, -1);
            }
            res = Math.max(res, i - start);
            
            map.put(c, i);
        }
        
        return res;
    }
}
```

### Method 1 - Sliding Window - :rocket: 6ms (83.94%)

```java
public class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        Map<Character, Integer> map = new HashMap();
        if (s == null) return 0;
        int start = 0, end = 0, cnt = 0, len = s.length();
        int res = 0;
        while (end < len) {
            char c = s.charAt(end++);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > 1) cnt++;
            
            while (cnt > 0) {
                char t = s.charAt(start++);
                int freq = map.get(t);
                if (freq == 2) cnt--;
                map.put(t, freq - 1);
            }
            
            if (res < end - start) {
                res = end - start;
            }
        }
        
        return res;
    }
}
```
