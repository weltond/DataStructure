// https://leetcode.com/problems/implement-trie-prefix-tree/


/**
Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
*/


// =========== Method 1: Array =============
// 73ms (98.45%)
class Trie {
    Trie[] children;
    boolean isWord;
    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26];
        isWord = false;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie tmp = this;
        for (int i = 0, len = word.length(); i < len; i++) {
            int idx = word.charAt(i) - 'a';
            if (tmp.children[idx] == null) {
                tmp.children[idx] = new Trie();
            }
            tmp = tmp.children[idx];
        }
        
        tmp.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(this, word, 0);
    }
    
    private boolean search(Trie root, String word, int lvl) {
        if (word.length() == lvl) return root.isWord;
        
        int idx = word.charAt(lvl) - 'a';
        
        if (root.children[idx] != null) {
            return search(root.children[idx], word, lvl + 1);
        }
        
        return false;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie tmp = this;
        
        for (int i = 0, len = prefix.length(); i < len; i++) {
            int idx = prefix.charAt(i) - 'a';
            if (tmp.children[idx] == null) return false;
            
            tmp = tmp.children[idx];
        }
        
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
