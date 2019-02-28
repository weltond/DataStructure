// https://leetcode.com/problems/longest-common-prefix/
// ============== BELOW is LC Solution ============

/**
Method 1: Horizontal scanning
*/

/**
Method 2: Vertical scanning
*/

/**
Method 3: Divide and Conquer
*/

/**
Method 4: Trie
*/
// q is query
public String longestCommonPrefix(String q, String[] strs) {
    if (strs == null || strs.length == 0)
         return "";  
    if (strs.length == 1)
         return strs[0];
    Trie trie = new Trie();      
    for (int i = 1; i < strs.length ; i++) {
        trie.insert(strs[i]);
    }
    return trie.searchLongestPrefix(q);
}

class TrieNode {

    // R links to node children
    private TrieNode[] links;

    private final int R = 26;

    private boolean isEnd;

    // number of children non null links
    private int size;    
    public void put(char ch, TrieNode node) {
        links[ch -'a'] = node;
        size++;
    }

    public int getLinks() {
        return size;
    }
    //assume methods containsKey, isEnd, get, put are implemented as it is described
   //in  https://leetcode.com/articles/implement-trie-prefix-tree/)
}

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    private String searchLongestPrefix(String word) {
        TrieNode node = root;
        StringBuilder prefix = new StringBuilder();
        
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            
            if (node.containsKey(curLetter) && (node.getLinks() == 1) && (!node.isEnd()) {
                prefix.append(curLetter);
                node = node.get(curLetter);
            } else {
                return prefix.toString();
            }
        }
        
        return prefix.toString();
    }

// ============== BELOW is my CODE =============
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        return method0(strs);
    }
    // ====== Sol 0: Trie =========
    public String method0(String[] strs) {
        Trie root = new Trie();
        for (String s : strs) {
            // if contains null, just return ""
            if (s == null || s.length() == 0) return "";
            buildTrie(root, s);
        }
        
        StringBuilder sb = new StringBuilder();
        f(root, sb);
        
        return sb.toString();
    }
    
    private void f(Trie root, StringBuilder sb) {
        if (root == null) return;
        
        int idx = isOne(root);
        
        if (idx == -1) return;
        
        sb.append((char) (idx + 'a'));
        
        // if current is end of word, also return
        if (root.children[idx].isEnd) return;
        
        f(root.children[idx], sb);
    }
    
    private void buildTrie(Trie root, String str) {
        Trie tmp = root;
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            if (tmp.children[idx] == null) {
                tmp.children[idx] = new Trie();
            }
            tmp = tmp.children[idx];
        }
        
        tmp.w = str;
        tmp.isEnd = true;
    }
    
    // return -1 if multiple children
    // return index if only one child
    private int isOne(Trie root) {
        if (root == null) return -1;
        Trie tmp = root;
        int cnt = 0, idx = -1;
        for (int i = 0; i < 26; i++) {
            if (tmp.children[i] != null) {
                cnt++;
                idx = i;
            }
            if (cnt > 1) return -1;
        }
        return idx;
    }
    
    // ====== Sol 1: Naive ========
    public String method1(String[] strs) {
        int i = 0;
        String m = minLen(strs);
        char c;    // first char
        boolean flag = true;
        
        while (i < m.length()) {
            c = m.charAt(i);
            for (String s : strs) {
                if (s.charAt(i) != c) {
                    flag = false;
                    break;
                }
            }
            
            if (!flag) break;
            i++;
        }
        
        return i > 0 ? m.substring(0, i) : "";
    }
    
    private String minLen(String[] strs) {
        int m = Integer.MAX_VALUE;
        String res = null;
        for (String s : strs) {
            if (s.length() < m) {
                res = s;
                m = s.length();
            }
        }
        return res;
    }
    
    // ===== Sol 2: Use APIs ======
    public String method2(String[] strs) {
        if(strs == null || strs.length == 0)    return "";
        String pre = strs[0];
        int i = 1;
        while(i < strs.length){
            while(strs[i].indexOf(pre) != 0)
                pre = pre.substring(0,pre.length()-1);
            i++;
        }
        return pre;
    }
}

class Trie {
    Trie[] children;
    String w;
    boolean isEnd;
    public Trie() {
        children = new Trie[26];
        w = null;
        isEnd = false;
    }
}
