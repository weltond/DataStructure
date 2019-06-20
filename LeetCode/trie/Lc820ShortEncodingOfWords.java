// https://leetcode.com/problems/short-encoding-of-words/

/**
Given a list of words, we may encode it by writing a reference string S and a list of indexes A.

For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].

Then for each index, we will recover the word by reading from the reference string from that index until we reach a "#" character.

What is the length of the shortest reference string S possible that encodes the given words?

Example:

Input: words = ["time", "me", "bell"]
Output: 10
Explanation: S = "time#bell#" and indexes = [0, 2, 5].
 

Note:

1 <= words.length <= 2000.
1 <= words[i].length <= 7.
Each word has only lowercase letters.

*/

class Solution {
    // ============= Method 2: Use defined method ===============
    // it is ok because each word length is less than 7
    // 15ms (79.78%)
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet(Arrays.asList(words));
        
        for (String word : words) {
            for (int k = 1, len = word.length(); k < len; k++) {
                set.remove(word.substring(k));
            }
        }
        
        int ans = 0;
        for (String s : set) {
            ans += s.length() + 1;
        }
        
        return ans;
    }
    // ============= Method 1: Trie ================
    // Approach 3: 12ms (88.67%)
    public int minimumLengthEncoding(String[] words) {
        Trie root = new Trie();
        Set<String> set = new HashSet();
        int res = 0;
        for (String s : new HashSet<String>(Arrays.asList(words))) {
            res += root.calcLength(s);
        }
        
        return res;
    }
    // Approach 2: 15ms (79.78%)
    public int minimumLengthEncoding(String[] words) {
        Trie2 root = new Trie2();
        Map<Trie2, Integer> nodes = new HashMap();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Trie2 cur = root;
            for (int j = word.length() - 1; j >= 0; j--) {
                cur = cur.get(word.charAt(j));
            }
            
            nodes.put(cur, i);
        }
        
        int ans = 0;
        for (Trie2 n: nodes.keySet()) {
            if (n.count == 0) {
                ans += words[nodes.get(n)].length() + 1;
            }
        }
        
        return ans;
    }
    // Approach 1: 16ms (69.33%)
    public int minimumLengthEncoding(String[] words) {
        Trie root = new Trie();
        Set<String> set = new HashSet();
        for (String s : words) {
            set.add(s);
            root.insert(s);
        }
        
        int res = 0;
        
        for (String s : set) {      
            if (!root.isContained(s)) {
                res += s.length() + 1;
            }
        }
    }
}

class Trie2 {
    Trie2[] children;
    int count;
    Trie2() {
        children = new Trie2[26];
        count = 0;
    }
    
    // if has same suffix, its count won't be 0
    // e.g. for ["time", "me"]
    //      root(e).count = 1, root(m).count = 1,
    //      root(i).count = 1, root(t).count = 0
    public Trie2 get(char c) {
        if (children[c - 'a'] == null) {
            children[c - 'a'] = new Trie2();
            count++;
        }
        return children[c - 'a'];
    }
}

class Trie {
    Trie[] children;
    Boolean end;
    int depth;
    Trie() {
        children = new Trie[26];
        end = false;
        depth = 0;
    }
    
    public int calcLength(String word) {
        Trie tmp = this;
        char c = 'a';
        int idx = 0, len = word.length(), ret = len + 1;
        for (int i = len - 1; i >= 0; i--) {
            c = word.charAt(i);
            idx = c - 'a';
            
            if (tmp.children[idx] == null) {
                tmp.children[idx] = new Trie();
            } else if (tmp.children[idx].end) {
                ret = ret - tmp.children[idx].depth - 1;
                
            }
            tmp = tmp.children[idx];
        }
        
        tmp.end = true;
        tmp.depth = len;
        return isEmpty(tmp) ? ret : 0;
    }
    
    public void insert(String word) {
        Trie tmp = this;
        char c = 'a';
        int idx = 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            c = word.charAt(i);
            idx = c - 'a';
            
            if (tmp.children[idx] == null) {
                tmp.children[idx] = new Trie();
            }
            tmp = tmp.children[idx];
        }
        
        tmp.end = true;
    }
    
    public boolean isEmpty(Trie root) {
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null)
                return false;
        }
        return true;
    }
    
    // it is guaranteed that word is in the Trie
    public boolean isContained(String word) {
        Trie tmp = this;
        for (int i = word.length() - 1; i >= 0; i--) {
            tmp = tmp.children[word.charAt(i) - 'a'];
        }
        
        return !isEmpty(tmp);
    }
}
