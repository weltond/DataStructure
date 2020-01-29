## [639. Word Abbreviation](https://www.lintcode.com/problem/word-abbreviation/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.

Example 1:

```
Input:
["like","god","internal","me","internet","interval","intension","face","intrusion"]
Output:
["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
```

Example 2:

```
Input:
["where","there","is","beautiful","way"]
Output:
["w3e","t3e","is","b7l","way"]
```

Notice
- Both n and the length of each word will not exceed 400.
- The length of each word is greater than 1.
- The words consist of lowercase English letters only.
- The return answers should be in the same order as the original array.

## Answer
### Method 1 - HashMap - :rocket: 1681ms (92.60%)

```java
public class Solution {
    /**
     * @param dict: an array of n distinct non-empty strings
     * @return: an array of minimal possible abbreviations for every word
     */
    public String[] wordsAbbreviation(String[] dict) {
        // write your code here
        Map<String, Integer> map = new HashMap();   // <abbrev, cnt>
        String[] res = new String[dict.length];
        
        int round = 1;
        for (int i = 0; i < dict.length; i++) {
            res[i] = getAbbrev(dict[i], round);
            map.put(res[i], map.getOrDefault(res[i], 0) + 1);
        }
        
        while (true) {
            boolean unique = true;
            round++;
            for (int i = 0; i < dict.length; i++) {
                if (map.get(res[i]) > 1) {
                    res[i] = getAbbrev(dict[i], round);
                    map.put(res[i], map.getOrDefault(res[i], 0) + 1);
                    unique = false;
                }
            }
            if (unique) break;
        }
        
        return res;
    }
    
    private String getAbbrev(String s, int p) {
        int len = s.length();
        if (len <= p + 2) return s;
        
        int cnt = len - 1 - p;
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, p)).append(cnt).append(s.charAt(len - 1));
        
        return sb.toString();
    }
}
```

### Wrong - 60% Passed

```java
public class Solution {
    /**
     * @param dict: an array of n distinct non-empty strings
     * @return: an array of minimal possible abbreviations for every word
     */
    public String[] wordsAbbreviation(String[] dict) {
        // write your code here
        Map<String, Integer> map = new HashMap();   // <abbrev, pos>
        String[] res = new String[dict.length];
        
        for (int i = 0; i < dict.length; i++) {
            String s = getAbbrev(dict[i]);
            if (map.containsKey(s)) {
                int pos = map.get(s);
                //map.remove(s);
                String common = findCommon(dict[i], dict[pos]);
                int dictLen = dict[i].length(), comLen = common.length();
                if (dictLen - comLen <= 3) {
                    res[pos] = dict[pos];
                    res[i] = dict[i];
                } else {
                    res[pos] = common + dict[pos].charAt(comLen) + Integer.toString(dictLen - comLen - 2) + dict[pos].charAt(dictLen - 1);
                    res[i] = common + dict[i].charAt(comLen) + Integer.toString(dictLen - comLen - 2) + dict[i].charAt(dictLen - 1);
                    map.put(res[pos], pos);
                    map.put(res[i], i);
                }
            } else {
                map.put(s, i);
                res[i] = s;
            }
        }
        
        return res;
    }
    
    private String getAbbrev(String s) {
        int len = s.length();
        if (len <= 3) return s;
        
        int cnt = len - 2;
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0)).append(cnt).append(s.charAt(len - 1));
        
        return sb.toString();
    }
    
    private String findCommon(String s1, String s2) {
        int i = 0, len = s1.length();
        StringBuilder sb = new StringBuilder();
        while (i < len) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            if (c1 == c2) {
                sb.append(c1);
            } else {
                break;
            }
            i++;
        }
        
        return sb.toString();
    }
}
```
