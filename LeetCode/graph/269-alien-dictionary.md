## [269. Alien Dictionary](https://leetcode.com/problems/alien-dictionary/)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.

A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. **If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.**

 

Example 1:

```
Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
```

Example 2:

```
Input: words = ["z","x"]
Output: "zx"
```

Example 3:

```
Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".
``` 

**Constraints**:

- 1 <= words.length <= 100
- 1 <= words[i].length <= 100
- words[i] consists of only lowercase English letters.

## Answers

### Method 1 - Topological Sort - 19ms (5.79%)

Use topological sort. Store each relationship as a graph.

```java
class Solution {
    public String alienOrder(String[] words) {
        int[] degree = new int[26];
        Arrays.fill(degree, -1);
        
        for (String s : words) {
            for (char c : s.toCharArray()) {
                degree[c - 'a'] = 0;
            }
        }
        
        // <parent_char, set of children>
        Map<Character, Set<Character>> map=new HashMap<Character, Set<Character>>();
        
        // build indegree
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i], next = words[i + 1];
            int len = Math.min(cur.length(), next.length());
            
            int j = 0;
            for (; j < len; j++) {
                char c1 = cur.charAt(j), c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = new HashSet();
                    // higher priority char exists before
                    if (map.containsKey(c1)) {
                        set = map.get(c1);
                    }
                    
                    // add lower priority to children of higher priority char
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree[c2 - 'a']++; // c2 has parent
                    }
                    
                    // stop because already find different chars
                    break;
                }
            }
            
            // corner case ["abc", "ab"]
            if (j == len && j < cur.length()) return "";
        }
        
        System.out.println(map);
        // Topological sort
        int total = 0;
        
        Deque<Character> q = new LinkedList();
        for (int i = 0; i < 26; i++) {
            if (degree[i] == 0) q.offer((char)(i + 'a'));
            if (degree[i] != -1) total++;
        }
    
        System.out.println(total+","+q.size());
        StringBuilder sb = new StringBuilder();
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                char c = q.poll();

                sb.append(c);
                
                if (!map.containsKey(c)) continue;
                
                for (char next : map.get(c)) {
                    int cnt = degree[next - 'a']--;

                    if (cnt == 1) {
                        q.offer(next);
                    }           
                }
            }
        }
        
        if (sb.length() != total) return "";
        
        return sb.toString();
    }
}
```
