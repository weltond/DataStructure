// https://leetcode.com/problems/longest-word-in-dictionary/
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
