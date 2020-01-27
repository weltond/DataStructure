## [720. Longest Word in Dictionary](https://leetcode.com/problems/longest-word-in-dictionary/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.

Example 1:

```
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation: 
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
```

Example 2:

```
Input: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: 
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
```

Note:

- All the strings in the input will only contain lowercase letters.
- The length of words will be in the range `[1, 1000]`.
- The length of words[i] will be in the range `[1, 30]`.

## Answer
### Method 1 - Trie - :rocket:6ms(94.16%)

#### Approach 2

- Smarter way.
  - Iterate from `z` to `a` so that it is guranteed that if length is same, the lexi smaller will be returned last.
  - Save each word at the end of trie point so that we don't need a `stringbuilder` to reconstruct the result.
  
```java
class Solution {
    String res = "";
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) return "";
        
        TrieNode root = new TrieNode();
        buildTrie(root, words);
        
        for (int i = 25; i >= 0; i--) {
            getLongest(root.children[i]);
        }
        return res;
    }
    
    public void getLongest(TrieNode root) {
        if(root == null || root.word == null) return;
       
        if(root.word.length() >= res.length()) {
            res = root.word;
        } 
        
        for (int i = 25; i >= 0; i--) {
            getLongest(root.children[i]);  
        }
    }
    
    public void insert(TrieNode root, String key) {
        TrieNode tmp = root;
        for (int i = 0; i < key.length(); i++) {
            int idx = key.charAt(i) - 'a';
            if (tmp.children[idx] == null) {
                tmp.children[idx] = new TrieNode();
            }
            tmp = tmp.children[idx];
        }
        tmp.word = key;
    }
    
    public void buildTrie(TrieNode root, String[] words) {
        for (int i = 0; i < words.length; i++) {
            insert(root, words[i]);
        }
    }
    
    public boolean isLeaf(TrieNode root) {
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                return false;
            }
        }
        return true;
    }
} 

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    String word = null;
}
```

#### Approach 1
```java
class Solution {
    int globalMax = 0;
    String ret = "";
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) return "";
        Trie root = new Trie();
        for (String s : words) {
            root.add(s);
        }
        // root.print();
        dfs(root, new StringBuilder());
        
        return ret;
    }
    
    private void dfs(Trie root, StringBuilder sb) {
        boolean empty = true;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].isEnd) {
                sb.append((char)(i + 'a'));
                empty = false;
                dfs(root.children[i], sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        
        if (empty) {
            int len = sb.length();
            String s = sb.toString();
            if (globalMax < len) {
                globalMax = len;
                ret = s;
            } else if (globalMax == len) {
                ret = s.compareTo(ret) <= 0 ? s : ret;
            }
        }
    }
}

class Trie {
    Trie[] children;
    boolean isEnd;
    public Trie() {
        children = new Trie[26];
    }
    
    public void add(String s) {
        Trie tmp = this;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int idx = s.charAt(i) - 'a';
            if (tmp.children[idx] == null) {
                tmp.children[idx] = new Trie();
            }
            tmp = tmp.children[idx];
        }
        
        tmp.isEnd = true;
    }
    
    public void print() {
        printUtil(this, 0, new StringBuilder());
    }
    
    public void printUtil(Trie root, int lvl, StringBuilder sb) {
        if (root.isEnd) {
            System.out.println(sb);
        }
        boolean find = false;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                sb.append((char)(i + 'a'));
                //System.out.println(sb);
                printUtil(root.children[i], lvl + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
                find = true;
            }
        }
    }
}
```
