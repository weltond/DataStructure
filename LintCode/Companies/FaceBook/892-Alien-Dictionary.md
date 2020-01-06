## [892. Alien Dictionary](https://www.lintcode.com/problem/alien-dictionary/description?_from=ladder&&fromId=130)

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

```
Input：["wrt","wrf","er","ett","rftt"]
Output："wertf"
Explanation：
from "wrt"and"wrf" ,we can get 't'<'f'
from "wrt"and"er" ,we can get 'w'<'e'
from "er"and"ett" ,we can get 'r'<'t'
from "ett"and"rftt" ,we can get 'e'<'r'
So return "wertf"
```

Example 2:

```
Input：["z","x"]
Output："zx"
Explanation：
from "z" and "x"，we can get 'z' < 'x'
So return "zx"
```

Notice
- You may assume all letters are in lowercase.
- The dictionary is invalid, if a is prefix of b and b is appear before a.
- If the order is invalid, return an empty string.
- There may be multiple valid order of letters, return the smallest in normal lexicographical order

## Answer
### Method 1 - Topological Sort - 352ms (3.40%) (????)

```java
public class Solution {
    /**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public String alienOrder(String[] words) {
        // Write your code here
        if (words == null || words.length == 0) return "";
        
        Map<Character, Set<Character>> graph = new HashMap();
        
        // build graph
        
        //  a. create edges.
        for (int i = 0; i < words.length - 1; i++) {
            // Take current two words and find the first mismatching char
            String w1 = words[i];
            String w2 = words[i + 1];
            int len1 = w1.length(), len2 = w2.length();
            int len = Math.min(len1, len2);
            
            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) {
                    graph.computeIfAbsent(c1, o -> new HashSet()).add(c2);
                    break;
                }
            }
        }
        
        //  b. create nodes that are not created in above edges
        for (int i = 0; i < words.length; i++) {
            for (int j = 0, wl = words[i].length(); j < wl; j++) {
                char tmp = words[i].charAt(j);
                graph.computeIfAbsent(tmp, o -> new HashSet());
            }
        }
        
        // topological sort
        return topologicalSort(graph);
        
    }
    
    private Map<Character, Integer> getIndegree(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> map = new HashMap();
        for (char c : graph.keySet()) {
            map.put(c, 0);
        }
        
        for (char c : graph.keySet()) {
            for (char u : graph.get(c)) {
                map.put(u, map.get(u) + 1);
            }
        }
        
        return map;
    }
    
    private String topologicalSort(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = getIndegree(graph);
        // ["wrt","wrf","er","ett","rftt"]
        // System.out.println(graph);   // {r=[t], t=[f], e=[r], f=[], w=[e]}
        // System.out.println(indegree); // {r=1, t=1, e=1, f=1, w=0}
        
        PriorityQueue<Character> pq = new PriorityQueue();  // require sorted result
        
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                pq.offer(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (!pq.isEmpty()) {
            char chr = pq.poll();
            sb.append(chr);
            
            for (char neighbor : graph.get(chr)) {
                int freq = indegree.get(neighbor);
                if (freq == 1) {
                    pq.offer(neighbor);
                }
                
                indegree.put(neighbor, freq - 1);
            }
        }
        
        if (sb.length() != graph.size()) return "";
     
        return sb.toString();   
    }
}
```
