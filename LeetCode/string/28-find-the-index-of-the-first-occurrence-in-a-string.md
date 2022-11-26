## [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 

Example 1:

```
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
```

Example 2:

```
Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.
``` 

**Constraints**:

- 1 <= haystack.length, needle.length <= 104
- haystack and needle consist of only lowercase English characters.

## Answer
### Method 1 :rabbit: 1ms (67.86%)

```java
class Solution {
    public int strStr(String haystack, String needle) {
        int lenh = haystack.length(), lenn = needle.length();
        for (int i = 0; i <= lenh - lenn; i++) {
            if (compare(haystack, needle, i, lenh, lenn)) {
                return i;
            }
        }
        
        return -1;
    }
    
    public boolean compare(String a, String b, int i, int lena, int lenb) {
        int j = 0;
        while (i < lena && j < lenb) {
            char s1 = a.charAt(i++), s2 = b.charAt(j++);
            if (s1 != s2) return false;
        }
        
        return j == lenb;
    }
}
```
