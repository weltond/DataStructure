## [567. Permutation in String](https://leetcode.com/problems/permutation-in-string/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.


Example 1:

```
Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
```

Example 2:

```
Input:s1= "ab" s2 = "eidboaoo"
Output: False
```

Note:

- The input strings only contain lower case letters.
- The length of both given strings is in range `[1, 10,000]`.

## Answer
### Method 3 - Efficient Sliding Window - 

```java
class Solution {
    // =========== Method 2: Efficient Sliding Window============
    // 5ms (95.4%) O(l1+26*(l2-l1))
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) return true;
        
        int len1 = s1.length(), len2 = s2.length();
        
        if (len1 > len2) return false;
        
        int[] cnt = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < len1; i++) {
            cnt[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < len2 - len1; i++) {
            if (compare(cnt, cnt2)) return true;

            cnt2[s2.charAt(i + len1) - 'a']++;
            cnt2[s2.charAt(i) - 'a']--;
        }
        return compare(cnt, cnt2);
    }
    
    private boolean compare(int[] a1, int[] a2) {
        for (int i = 0; i < 26; i++) {
            if (a1[i] != a2[i]) return false;
        }
        return true;
    }
    
}
```

### Method 2 - General Sliding Window - :rabbit: 16ms (42.15%)

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> map = new HashMap();
        if (s1 == null || s2 == null) return false;
        
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        int len = s2.length(), start = 0, end = 0, size = map.size();
        
        while (end < len) {
            char c = s2.charAt(end++);
            
            if (map.containsKey(c)) {
                int freq = map.get(c);
                if (freq == 1) {
                    size -= 1;
                }
                map.put(c, freq - 1);
            }
            
            while (size == 0) {
                if (end - start == s1.length()) return true;
                
                char t = s2.charAt(start++);
                if (map.containsKey(t)) {
                    int f = map.get(t);
                    if (f == 0) {
                        size += 1;
                    }
                    map.put(t, f + 1);
                }
                
            }
        }
        
        return false;
    }
}
```

### Method 1 - Naive - :turtle: 168ms (12.37%)

```java
// =========== Method 1: Naive Sliding Window ==============
    // 168ms (12.37%)
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) return true;
        
        int len1 = s1.length(), len2 = s2.length();
        
        int[] cnt = new int[26];
        for (int i = 0; i < len1; i++) {
            cnt[s1.charAt(i) - 'a']++;
        }
        
        int match = len1;
        
        for (int i = 0; i < len2; i++) {
            if (cnt[s2.charAt(i) - 'a'] != 0) {
                int j = i;
                match = len1;
                /** "adc"
                    "dcda"
                    =======
                    "a"
                    "ab"
                */
                int[] tmp = cnt.clone();
                while (j < len2 && tmp[s2.charAt(j) - 'a'] != 0) {
                    tmp[s2.charAt(j) - 'a']--;
                    j++;
                    match--;
                    if (match == 0) return true;
                }
            }
        }
        
        return false;
    }
```


