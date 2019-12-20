## [472. Concatenated Words](https://leetcode.com/problems/concatenated-words/)

Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:

```
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
```

Note:

- The number of elements of the given array will not exceed 10,000
- The length sum of elements in the given array will not exceed 600,000.
- All the input string will only include lower case letters.
- The returned elements order does not matter.

## Answer
### Method 2 - Trie - :rocket: 43ms (89.97%)

```java
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new LinkedList();
        if (words == null || words.length == 0) return res;
        
        Trie root = new Trie();
        
        // constuct trie
        for (String word : words) {
            if (word.length() == 0) continue;
            addWord(word, root);
        }
        
        // check word is a concatnated word or not
        for (String word : words) {
            if (word.length() == 0) continue;
            if (check(word.toCharArray(), 0, root, 0)) {
                res.add(word);
            }
        }
        
        return res;
    }
    
    // cnt means how many words during search path
    public boolean check(char[] arr, int idx, Trie root, int cnt) {
        Trie cur = root;
        int n = arr.length;
        for (int i = idx; i < n; i++) {
            if (cur.children[arr[i] - 'a'] == null) return false;
            
            if (cur.children[arr[i] - 'a'].isEnd) {
                if (i == n - 1) // no next word, so check cnt to get result
                    return cnt >= 1;
                
                if (check(arr, i + 1, root, cnt + 1))
                    return true;
            }
            
            cur = cur.children[arr[i] - 'a'];
        }
        
        return false;
    }
    
    public void addWord(String str, Trie root) {
        int len = str.length();
        
        for (int i = 0; i < len; i++) {
            int idx = str.charAt(i) - 'a';
            if (root.children[idx] == null) {
                root.children[idx] = new Trie();
            }
            
            root = root.children[idx];
        }
        
        root.isEnd = true;
    }
}

class Trie {
    Trie[] children;
    boolean isEnd;
    
    public Trie() {
        children = new Trie[26];
    }
    
    
}
```

Method 1 - DP - :turtle: 243ms (19.70%)

```java
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res=  new LinkedList();
        Set<String> set = new HashSet();
        
        // sort based on string length
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        
        for (String s : words) {
            if (!set.isEmpty() && wordBreak(s, set)) {
                res.add(s);
            }
            
            set.add(s); // string should be added to set after checking wordbreak
        }
        
        return res;
    }

    private boolean wordBreak(String s, Set<String> set) {
        if (s == null || s.length() == 0) return false;
        
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        
        dp[0] = true;
        
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[len];
    }
}
```
