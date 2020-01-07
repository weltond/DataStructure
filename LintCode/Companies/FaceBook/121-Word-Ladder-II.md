## [121. Word Ladder II](https://www.lintcode.com/problem/word-ladder-ii/description?_from=ladder&&fromId=130)

Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, output sequence in dictionary order.
Transformation rule such that:

Only one letter can be changed at a time

Each intermediate word must exist in the dictionary


Example 1:

```
Input：start = "a"，end = "c"，dict =["a","b","c"]
Output：[["a","c"]]
Explanation：
"a"->"c"
```

Example 2:

```
Input：start ="hit"，end = "cog"，dict =["hot","dot","dog","lot","log"]
Output：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation：
1."hit"->"hot"->"dot"->"dog"->"cog"
2."hit"->"hot"->"lot"->"log"->"cog"
The dictionary order of the first sequence is less than that of the second.
```

Notice
- All words have the same length.
- All words contain only lowercase alphabetic characters.
- At least one solution exists.

## Answer
### 9C Answer

```java
public class Solution {
    public List<List<String>> findLadders(String start, String end,
            Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);
 
        bfs(map, distance, end, start, dict);
        
        List<String> path = new ArrayList<String>();
        
        dfs(ladders, path, start, end, distance, map);

        return ladders;
    }

    void dfs(List<List<String>> ladders, List<String> path, String crt,
            String end, Map<String, Integer> distance,
            Map<String, List<String>> map) {
        path.add(crt);
        if (crt.equals(end)) {
            ladders.add(new ArrayList<String>(path));
        } else {
            for (String next : map.get(crt)) {
                if (distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) { 
                    dfs(ladders, path, next, end, distance, map);
                }
            }           
        }
        path.remove(path.size() - 1);
    }

    void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
            String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }
        
        while (!q.isEmpty()) {
            String crt = q.poll();

            List<String> nextList = expand(crt, dict);
            for (String next : nextList) {
                map.get(next).add(crt);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }

    List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();

        for (int i = 0; i < crt.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != crt.charAt(i)) {
                    String expanded = crt.substring(0, i) + ch
                            + crt.substring(i + 1);
                    if (dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }

        return expansion;
    }
}
```

### Method 1 - Bi-BFS + DFS - :turtle: 3680ms (21%)

```java
public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */
    public List<List<String>> findLadders(String start, String stop, Set<String> dict) {
        // write your code here
        List<List<String>> res = new LinkedList();
        Set<String> begin = new HashSet();
        Set<String> end = new HashSet();
        
        begin.add(start);
        
        //if (dict.contains(stop)) end.add(stop);
        dict.add(stop);
        end.add(stop);
        
        if (dict.contains(start)) dict.remove(start);
        
        String s = null;
        Map<String, List<String>> map = new HashMap();
        boolean direction = false, found = false;
        
        while (!end.isEmpty() && !begin.isEmpty() && !found) {
            Set<String> tmp = new HashSet();
            for (String str: begin) {
                char[] arr = str.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char ch = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == ch) continue;
                        arr[i] = c;
                        s = new String(arr);
                        
                        if (!end.contains(s) && !dict.contains(s)) continue;
                        
                        addWords(direction, map, str, s);
                        
                        if (end.contains(s)) {
                            found = true;
                        }
                        
                        if (dict.contains(s)) {
                            tmp.add(s);
                        }
                    }
                    arr[i] = ch;
                }
            }
            
            direction = !direction;
            begin = end;
            end = tmp;
            
            for (String t : tmp) {
                dict.remove(t);
            }
        }
        
        if(found) {
            //System.out.println(map);
            List<String> list = new ArrayList();
            list.add(start);
            dfs(start, stop, map, list, res);
        }
        
        return res;
    }
    
    private void addWords(boolean dir, Map<String, List<String>> map, String str, String s) {
        if (dir) {
            map.computeIfAbsent(s, o-> new ArrayList()).add(str);
        } else {
            map.computeIfAbsent(str, o->new ArrayList()).add(s);
        }
    }
    
    private void dfs(String start, String end, Map<String, List<String>> map, List<String> list, List<List<String>> res) {
        if (start.equals(end)) {
            res.add(new ArrayList(list));
            return;
        }
        if (!map.containsKey(start)) return;
        
        for (String s : map.get(start)) {
            list.add(s);
            dfs(s, end, map, list, res);
            list.remove(list.size() - 1);
        }
        
    }
}
```
