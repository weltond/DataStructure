## [409. Longest Palindrome](https://leetcode.com/problems/longest-palindrome/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
- Assume the length of given string will not exceed 1,010.

Example:

```
Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
```

## Answer
### Method 2 -

```java
class Solution {
    // 1ms (100%)
    public int longestPalindrome(String s) {
        int[] cnt = new int[128];
        for (char c: s.toCharArray()) {
            cnt[c]++;
        }
        
        int ans = 0;
        for (int v : cnt) {
            ans += v / 2 * 2;   // "aaaaa" -> "aaaa"
            if (ans % 2 == 0 && v % 2 == 1) {   // if v % 2 == 1 then this letter can be added as a unique center. We also check ans % 2 == 0 to make sure there isn't a unique center added yet.
                ans++;
            }
        }
        return ans;
    }
}
```

### Method 1 - HashSet - :rabbit: 4ms (54.27%)

- Use hashset to to remove seen chars. And the hashset size should be **less than 2** so that a string can form a palindrome.

```java
class Solution {
    public int longestPalindrome(String s) {
        if (s == null) return 0;
        Set<Character> set = new HashSet();
        int res = 0, len = s.length(), i = 0;
        
        while (i < len) {
            char c = s.charAt(i++);
            if (set.contains(c)) set.remove(c);
            else set.add(c);
        }
        
        
        return Math.min(len, len - set.size() + 1);
    }
}
```


