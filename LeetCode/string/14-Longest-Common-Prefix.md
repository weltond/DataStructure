## [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:
```
Input: ["flower","flow","flight"]
Output: "fl"
```
Example 2:
```
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```
Note:

- All given inputs are in lowercase letters a-z.

## Answer
### Method 4 - Divide and Conquer - :rabbit: 1ms (73.69%)
```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
          
        return dc(strs, 0, strs.length - 1);
    }
    
    private String dc(String[] strs, int l, int r) {
        if (l == r) return strs[l];
        
        int mid = l + (r - l) / 2;
        
        String left = dc(strs, l, mid);
        String right = dc(strs, mid + 1, r);
        
        return findCommon(left, right);
    }
    
    private String findCommon(String left, String right) {
        int l1 = left.length(), l2 = right.length();
        int len = Math.min(l1, l2);
        for (int i = 0; i < len; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, len);
    }
}
```
### Method 3 - Horizontal Scan - :rabbit: 1ms (73.69%)
```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        
        return prefix;
    }
}
```

### Method 2 - Vertical Scan - :rabbit: 1ms (73.69%) 
```java
class Solution {
    // ==== Vertical Scan ====
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        for (int i = 0, len = strs[0].length(); i < len; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
```
### Method 1 - Trie - :turtle: 2ms (15.39%)
```java
class Solution {
    int len;
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        Trie root = new Trie();
        for (String s : strs) {
            root.insert(s);
        }
        len = strs.length;
        StringBuilder sb = new StringBuilder();
        get(root, sb);
        
        return sb.toString();
    }
    
    private void get(Trie root, StringBuilder sb) {
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].cnt == len) {
                //System.out.println((char)(i + 'a'));
                sb.append((char)(i + 'a'));
                get(root.children[i], sb);
            }
        }
    }
}

class Trie {
    Trie[] children;
    String word;
    boolean end;
    int cnt;
    public Trie() {
        children = new Trie[26];
        end = false;
        word = null;
        cnt = 0;
    }
    
    public void insert(String s) {
        Trie tmp = this;
        
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            int idx = c - 'a';
            if (tmp.children[idx] == null) {
                tmp.children[idx] = new Trie();
            }
            
            tmp = tmp.children[idx];
            tmp.cnt++;
        }
        
        tmp.word = s;
        tmp.end = true;
    }
}
```
