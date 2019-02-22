// https://leetcode.com/problems/add-and-search-word-data-structure-design/
class WordDictionary {
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode tmp = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (tmp.children[idx] == null) {
                tmp.children[idx] = new TrieNode();
            }
            tmp = tmp.children[idx];
        }
        tmp.isEnd = true;
        tmp.word = word;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return helper(word, root, 0);
    }
    
    public boolean helper(String word, TrieNode root, int level) {
        if (level == word.length()) return root.isEnd;
        
        if (word.charAt(level) == '.') {
            for (int i = 0; i < 26; i++) {
                if (root.children[i] != null) {
                    if (helper(word, root.children[i], level + 1)) {
                        return true;
                    }
                }
            }
        } else {
            char c = word.charAt(level);
            int index = c - 'a';
            if (root.children[index] != null) {
                if (helper(word, root.children[index], level + 1)) {
                    return true;
                }
            }
        }
        
        return false;   
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
    String word;
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
