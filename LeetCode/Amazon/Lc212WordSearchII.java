// https://leetcode.com/problems/word-search-ii/

// ======================== Backtracking + Trie =========================
// 10ms(99.77%)
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || words == null || words.length == 0) return new ArrayList();
        
        Trie root = new Trie();
        
        //Set<String> set = new HashSet();    // to speed up search process
        
        // build trie;
        for (int i = 0; i < words.length; i++) {
            root.insert(words[i]);
            //set.add(words);
        }
        
        List<String> res = new ArrayList();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                backtracking(board, i, j, root, res);
            }
        }
        
        return res;
    }
    
    private void backtracking(char[][] board, int x, int y, Trie root, List<String> res) {
        if (x >= board.length || y >= board[0].length || x < 0 || y < 0) {
            return;
        }
        
        char c = board[x][y];
        
        // base case: return directly if c is visited or no trie node
        if (c == '#' || root.children[c - 'a'] == null) return;
        // base case: add to res if found
        Trie tmp = root.children[c - 'a'];
        if (tmp != null && tmp.word != null) {
            res.add(tmp.word);
            tmp.word = null;    // set to null to avoid udplicate
            // we are not going to return here because we want to keep digging the Trie
        }
        
        board[x][y] = '#';  // mark as visited
        int[] dir = {0, 1, 0, -1, 0};
        
        // 4 way search
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i];
            int ny = y + dir[i + 1];
            
            backtracking(board, nx, ny, tmp, res);
        }
        
        board[x][y] = c;    // backtracking
    }
    
    class Trie {
        Trie[] children;
        String word;
        Trie() {
            children = new Trie[26];
            word = null;
        }
        // If not present, inserts key into Trie
        // If the key is prefix of Trie node, just marks leaf node
        public void insert(String s) {
            int len = s.length();
            int idx = 0;
            Trie tmp = this;
            
            for (int i = 0; i < len; i++) {
                idx = s.charAt(i) - 'a';
                
                if (tmp.children[idx] == null) {
                    tmp.children[idx] = new Trie();
                }
                tmp = tmp.children[idx];
            }
            
            tmp.word = s;
        }
        
        public boolean search(String s) {
            int len = s.length();
            int idx = 0;
            Trie tmp = this;
            
            for (int i = 0; i < len; i++) {
                idx = s.charAt(i) - 'a';
                if (tmp.children[idx] == null) return false;
                
                tmp = tmp.children[idx];
            }
            
            return tmp != null && tmp.word != null;
        }
        
        public boolean isEmpty(Trie root) {
            for (int i = 0; i < 26; i++) {
                if (root.children[i] != null)
                    return false;
            }
            return true;
        }
        
        public Trie delete(Trie root, String s) {
            return delete(root, s, 0);
        }
        
        // recursive to delete a string from given trie
        public Trie delete(Trie root, String s, int lvl) {
            if (root == null) return null;
            
            // If last character of key is being processed
            if (lvl == s.length()) {
                // This node is no more end of word after removal of given key
                if (root.word != null) {
                    root.word = null;
                }
                
                // If given is not prefix of any other word
                if (isEmpty(root)) {
                    root = null;
                }
                
                return root;
            }
            
            // If not last character, recur for the child obtained using ASCII value
            int idx = s.charAt(lvl) - 'a';
            
            root.children[idx] = delete(root, s, lvl + 1);
            
            // If root deos not have any child (its only child got deleted),
            // and it is not end of another word
            if (isEmpty(root) && root.word == null) {
                root = null;
            }
            
            return root;
        }
        
        public void print(Trie root) {
            if (root.word != null) {
                System.out.println(root.word);
                return;
            }
            for (int i = 0; i < 26; i++) {
                if (root.children[i] != null) {
                    print(root.children[i]);
                }
            }
        }
    }
}
