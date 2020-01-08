## [395. Longest Substring with At Least K Repeating Characters](https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/)
![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

```
Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
```

Example 2:

```
Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
```

## Answer
### Method 1 - Divide and Conquer :rabbit:
#### Approach 2 

```java
public class Solution {
    /**
     * @param s: a string
     * @param k: an integer
     * @return: return an integer
     */
    public int longestSubstring(String s, int k) {
        return dc(s, k);
    }
    
    private int dc(String s, int k) {
        int len = s.length();
        if (len == 0) return 0;
        
        int breakIdx = -1;
        
        // need to re-check frequency for every new string.
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        
        int res = 0, left = 0;
        for (int i = 0; i < len; i++) {
            if (arr[s.charAt(i) - 'a'] < k) {
                res = Math.max(res, dc(s.substring(left, i), k));
                left = i + 1;
                breakIdx = i;
            }
        }
        
        if (breakIdx == -1) return len;
        
        res = Math.max(res, dc(s.substring(left, len), k));
        return res;
    }
}
```

#### Approach 1 - :turtle: 104ms (7.78%)

- Could cause **Memory Limit Exceed**

```java
class Solution {
    
    public int longestSubstring(String s, int k) {
        return dc(s, k);
    }
    
    private int dc(String s, int k) {
        int len = s.length();
        if (len == 0) return 0;
        
        int breakIdx = -1;
        
        // need to re-check frequency for every new string.
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        
        
        for (int i = 0; i < len; i++) {
            if (arr[s.charAt(i) - 'a'] < k) {
                breakIdx = i;
                break;
            }
        }
        
        if (breakIdx == -1) return len;
        
        return Math.max(dc(s.substring(0, breakIdx), k), dc(s.substring(breakIdx + 1), k));
    }
}
```
