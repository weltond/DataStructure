## [582. Word Break II](https://www.lintcode.com/problem/word-break-ii/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

Example 1:

```
Input："lintcode"，["de","ding","co","code","lint"]
Output：["lint code", "lint co de"]
Explanation：
insert a space is "lint code"，insert two spaces is "lint co de".
```

Example 2:

```
Input："a"，[]
Output：[]
Explanation：dict is null.
```

## Answer
### Method 1 - DFS + memo - :rocket: 542ms (88.60%)

```java
public class Solution {
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    Map<String, List<String>> map = new HashMap();
    public List<String> wordBreak(String s, Set<String> wordDict) {
        return dfs(s, wordDict);
    }
    
    private List<String> dfs(String s, Set<String> set) {
        List<String> res = new ArrayList();
        if (map.containsKey(s)) return map.get(s);
        
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        
        for (int i = 0, len = s.length(); i < len; i++) {
            String sub = s.substring(0, i + 1);
            if (!set.contains(sub)) continue;
            
            List<String> ret = dfs(s.substring(i + 1), set);
            for (String next : ret) {
                StringBuilder sb = new StringBuilder();
                sb.append(sub).append(next.equals("") ? "" : " ").append(next);
                res.add(sb.toString());
            }
        }
        map.put(s, res);
        
        return res;
    }
}
```
