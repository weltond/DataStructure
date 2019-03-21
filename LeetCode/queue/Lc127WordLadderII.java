// https://leetcode.com/problems/word-ladder-ii/

class Solution {
    // ========== Method 1: Bidirectional BFS and DFS traverse ==============
    // 85ms (76.28%)
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        List<List<String>> ans = new ArrayList();
        for (String s: wordList) {
            set.add(s);
        }
        
        Set<String> end = new HashSet();
        Set<String> begin = new HashSet();
        if (set.contains(endWord)) {
            end.add(endWord);
        }
        
        begin.add(beginWord);
        // remove begin word from set if it is included
        if (set.contains(beginWord)) {
            set.remove(beginWord);
        }
        
        Map<String, List<String>> map = new HashMap();  // for DFS traverse 
        boolean found = false;  // to make sure output the minimum step solutions 
        boolean backward = false;   // to make sure the output order is correct for DFS traverse
        
        String s = null;
        while (!begin.isEmpty() && !end.isEmpty() && !found) {
            Set<String> tmp = new HashSet();
            // Set<String> toDelete = new HashSet();
            for (String str : begin) {
                char[] arr = str.toCharArray();
                for (int i = 0; i < beginWord.length(); i++) {
                    char ch = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == ch) continue;  // avoid duplicate
                        arr[i] = c;
                        s = new String(arr);

                        if (!end.contains(s) && !set.contains(s)) continue;
                        
                        addWords(backward, map, str, s);
                        
                        // CANNOT stop during this loop if found!!!!!
                        // Because there might be other possible words meet requirement in current loop
                        if (end.contains(s)) {
                            found = true;    
                            //break;
                        }

                        // otherwise add it to tmp (the next round of end)
                        if (set.contains(s)) {
                            tmp.add(s);
                            //toDelete.add(s);  // CANNOT remove it from wordList here to avoid missing items
                        }
                    }
                    arr[i] = ch;    // reset i-th char
                }
            }
            
            // swap end and begin
            backward = !backward;   // flag to notice bidirectional search
            begin = end;
            end = tmp;
            // remove tmp words in set to avoid revisit
            for (String t : tmp) {
                set.remove(t);
            }
        }
        
        if (found) {
            //System.out.println(map);
            dfs(map, beginWord, endWord, new ArrayList(), ans);
        }
        
        return ans;
        
    }
    
    public void addWords(boolean backward, Map<String, List<String>> map, String str, String s) {
        if (backward) {
            map.computeIfAbsent(s, o -> new ArrayList()).add(str);
        } else {
            map.computeIfAbsent(str, o -> new ArrayList()).add(s);
        }
    }
    
    private void dfs(Map<String, List<String>> map, String begin, String end, List<String> path, List<List<String>> ans) {
        if (begin.equals(end)) {
            path.add(end);
            ans.add(new ArrayList(path));
            path.remove(path.size() - 1);
            return;
        }
        
        if (!map.containsKey(begin)) return;
        
        path.add(begin);
        for (String str : map.get(begin)) {
            //System.out.println(begin + " : " + str);
            dfs(map, str, end, path, ans);
        }
        path.remove(path.size() - 1);
    }
}
