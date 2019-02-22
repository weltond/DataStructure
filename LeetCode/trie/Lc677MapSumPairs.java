// =============== Method 1 ===================
class MapSum {
    HashMap<String, Integer> map;
    TrieNode root;
    public MapSum() {
        map = new HashMap();
        root = new TrieNode();
    }
    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNode cur = root;
        cur.score += delta;
        for (char c: key.toCharArray()) {
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
            cur.score += delta;
        }
    }
    public int sum(String prefix) {
        TrieNode cur = root;
        for (char c: prefix.toCharArray()) {
            cur = cur.children.get(c);
            if (cur == null) return 0;
        }
        return cur.score;
    }
}
class TrieNode {
    Map<Character, TrieNode> children = new HashMap();
    int score;
}

// ================ Method 2 ==================
class MapSum {
    TrieNode root;
    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        TrieNode tmp = root;
        for (int i = 0; i < key.length(); i++) {
            int idx = key.charAt(i) - 'a';
            if (tmp.children[idx] == null) {
                tmp.children[idx] = new TrieNode();
            }
            tmp = tmp.children[idx];
        }
        tmp.isEnd = true;
        tmp.val = val;
    }
    
    public int sum(String prefix) {
        TrieNode tmp = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (tmp.children[idx] == null) return 0;
            tmp = tmp.children[idx];
        }
        
        int[] res = new int[]{0};   // need to reset res for every query
        getSum(tmp, res);
        
        return res[0];
    }
    
    // root starts at the end of prefix
    public void getSum(TrieNode root, int[] total) {
        if (root.isEnd) {
            total[0] += root.val;
        }
        
        if (isLeaf(root)) return;
        
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                getSum(root.children[i], total);
            }
        }
    }
    
    public boolean isLeaf(TrieNode root) {
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null)
                return false;
        }
        return true;
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
    int val;
}
/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
