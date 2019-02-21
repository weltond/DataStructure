// https://leetcode.com/problems/word-search-ii/

class Solution {
    int[] dirX = {0, 1, 0, -1};
    int[] dirY = {1, 0, -1, 0};
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList();
        
        if (board == null || words == null || words.length == 0) return res;
        
        TrieNode root = new TrieNode();
        
        buildTrie(root, words);
        //print(root);
        
        int n = board.length;
        int m = board[0].length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bt(board, i, j, root, res);
            }
        }

        return res;
    }
    
    private void bt(char[][] board, int x, int y, TrieNode root, List<String> res) {
        if (x >= board.length || y >= board[0].length || x < 0 || y < 0) {
            return;
        }
        
        char c = board[x][y];
        int index = c - 'a';
        
        if (c == '#' || root.children[index] == null) return;
        
        
        TrieNode p = root.children[index];

        if (p != null && p.word != null) {
            res.add(p.word);
            p.word = null;  // avoid duplicate
            //return;   // !!! Notice: We don't return here because it may only be a prefix of other words.
        }
        
        // we are not using extra m * n boolean[][] to optimize space complexity. Notice it also works if you use visited[][] 
        //visited[x][y] = true;
        board[x][y] = '#';  
        for (int i = 0; i < 4; i++) {
            bt(board, x + dirX[i], y + dirY[i], p, res);
        }
        board[x][y] = c;
        //visited[x][y] = false;
    }
    
    public void buildTrie(TrieNode root, String[] words) {
        for (String word : words) {
            insert(root, word);    
        }
    }
    
    public void insert(TrieNode root, String key) {
        int len = key.length();
        TrieNode dummy = root;
        int index = 0;
        for (int i = 0; i < len; i++) {
            index = key.charAt(i) - 'a';
            if (dummy.children[index] == null) {
                dummy.children[index] = new TrieNode();
            }
            dummy = dummy.children[index];
        }
        
        dummy.word = key;
    }
    
    // public void print(TrieNode root) {
    //     if (root.word != null) {
    //         System.out.println(root.word);
    //         return;
    //     }
    //     for (int i = 0; i < 26; i++) {
    //         if (root.children[i] != null) {
    //             print(root.children[i]);
    //         }
    //     }
    // }
}

class TrieNode {
    String word;
    TrieNode[] children = new TrieNode[26];
    
    public TrieNode() {
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}
