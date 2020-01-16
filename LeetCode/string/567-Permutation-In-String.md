## [637. Valid Word Abbreviation](https://www.lintcode.com/problem/valid-word-abbreviation/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a non-empty string word and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as `"word"` contains only the following valid abbreviations:

`["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]`

Example
Example 1:

```
Input : s = "internationalization", abbr = "i12iz4n"
Output : true
```

Example 2:

```
Input : s = "apple", abbr = "a2e"
Output : false
```

Notice
- Notice that only the above abbreviations are valid abbreviations of the string word. Any other string is not a valid abbreviation of word.

## Answer
### Method 1 - Two Pointer - :rabbit: 330ms (70%)

```java
public class Solution {
    /**
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        // write your code here
        if (word == null || word.length() == 0) return false;
        
        int i = 0, j = 0;
        int lenw = word.length(), lena = abbr.length();
        
        while (i < lena) {
            char c = abbr.charAt(i);
            if (c > '0' && c <= '9') {     // ignore num start with `0`
                int res = 0;
                while (i < lena && Character.isDigit(abbr.charAt(i))) {
                    res = res * 10 + abbr.charAt(i) - '0';
                    i++;
                }
                j = j + res;
            } else {
                if (j >= lenw || c != word.charAt(j++)) {
                    //System.out.println(i+","+j);
                    return false;
                }
                i++;
            }
        }
        
        return i == lena && j == lenw;
    }
}
```

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
}
