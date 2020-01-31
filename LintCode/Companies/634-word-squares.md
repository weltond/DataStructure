## [634. Word Squares](https://www.lintcode.com/problem/word-squares/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

Given a set of words without duplicates, find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

```
b a l l
a r e a
l e a d
l a d y
```

Example

Example 1:

```
Input:
["area","lead","wall","lady","ball"]
Output:
[["wall","area","lead","lady"],["ball","area","lead","lady"]]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
```

Example 2:

```
Input:
["abat","baba","atan","atal"]
Output:
 [["baba","abat","baba","atan"],["baba","abat","baba","atal"]]
```

Notice
- There are at least 1 and at most 1000 words.
- All words will have the exact same length.
- Word length is at least 1 and at most 5.
- Each word contains only lowercase English alphabet a-z.

## Answer
### Method 1 - DFS - :turtle: 3279ms (24.40%)

```java
public class Solution {
    /*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    public List<List<String>> wordSquares(String[] words) {
        // write your code here
        List<List<String>> res = new ArrayList();
        if (words.length == 0) return res;
        int size = words[0].length();
        Map<String, List<String>> prefixes = new HashMap();
        getPrefix(prefixes, words);
        
        List<String> sub = new ArrayList();
        
        for (int i = 0; i < words.length; i++) {
            sub.add(words[i]);
            dfs(res, sub, size, prefixes);
            sub.remove(sub.size() - 1);
        }
        
        return res;
    }
    
    private void dfs(List<List<String>> res, List<String> sub, int size, Map<String, List<String>> prefixes) {
        if (size == sub.size()) {
            res.add(new ArrayList(sub));
            return;
        }
        
        int rowNum = sub.size();
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < rowNum; i++) {
            prefix.append(sub.get(i).charAt(rowNum));
        }
        
        String prefixS = prefix.toString();
        
        if (!prefixes.containsKey(prefixS)) return;
        for (String word : prefixes.get(prefixS)) {
            sub.add(word);
            dfs(res, sub, size, prefixes);
            sub.remove(sub.size() - 1);
        }
    }
    
    private void getPrefix(Map<String, List<String>> prefixes, String[] words) {
        for (String word : words) {
            for (int i = 0, len = word.length(); i < len; i++) {
                String sub = word.substring(0, i + 1);
                prefixes.computeIfAbsent(sub, o -> new ArrayList()).add(word);
            }
        }
    }
}
```
