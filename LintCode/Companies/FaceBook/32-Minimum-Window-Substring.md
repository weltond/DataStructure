## [32. Minimum Window Substring
](https://www.lintcode.com/problem/minimum-window-substring/description?_from=ladder&&fromId=130)

Given two strings source and target. Return the minimum substring of source which contains each char of target.

Example 1:

```
Input: source = "abc", target = "ac"
Output: "abc"
```

Example 2:

```
Input: source = "adobecodebanc", target = "abc"
Output: "banc"
Explanation: "banc" is the minimum substring of source string which contains each char of target "abc".
```

Example 3:

```
Input: source = "abc", target = "aa"
Output: ""
Explanation: No substring contains two 'a'.
```

- Challenge: O(n) time

Notice:

- If there is no answer, return "".
- You are guaranteed that the answer is unique.
- target may contain duplicate char, while the answer need to contain at least the same number of that char.

## Answer
### Method 1 - Prefix Sum - :rabbit: 485ms (84.20%)

```java
public class Solution {
    /**
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public String minWindow(String source , String target) {
        // write your code here
        if (source == null || source.length() == 0) return "";
        
        int i = 0, size = source.length(), res = Integer.MAX_VALUE, j = 0, start = 0;
        
        Map<Character, Integer> map = new HashMap();
        for (char c : target.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int len = map.size();
        
        while (i < size) {
            char c = source.charAt(i++);
            if (map.containsKey(c)) {
                int f = map.get(c);
                if (f == 1) {
                    len--;
                }
                map.put(c, f - 1);
            }
            
            while (len == 0) {
                char t = source.charAt(j);
                if (map.containsKey(t)) {
                    int ff = map.get(t);
                    if (ff == 0) {
                        len++;
                    }
                    map.put(t, ff + 1);
                }
                
                if (i - j < res) {
                    res = i - j;
                    start = j;
                }
                j++;
            }
        }
        
        return res == Integer.MAX_VALUE ? "" : source.substring(start, res + start);
        
    }
}
```
