// https://leetcode.com/problems/word-break-ii/

class Solution {
    /**
    Input:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    Output:
    [
      "cats and dog",
      "cat sand dog"
    ]
    */
    
    // =============== DFS + Memoization ================
    // 7ms (73.91%)
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0) return new ArrayList();
        
        Set<String> set = new HashSet();
        for (String str : wordDict) {
            set.add(str);
        }
        
        Map<String, List<String>> map = new HashMap();
        
        return dfs(s, set, map);
    }
    
    // Use map to memoize. Consider input: [aaaaaa], dict: [a,aa,aaa]
    // map would be: 
    // {aa=[a a, aa], aaa=[a a a, a aa, aa a, aaa], a=[a], aaaa=[a a a a, a a aa, a aa a, a aaa, aa a a, aa aa, aaa a]}
    private List<String> dfs(String s, Set set, Map<String, List<String>> map) {
        // memoize
        if (map.containsKey(s)) return map.get(s);
        
        List<String> list = new ArrayList();    // for each node, store list from its children node
        
        // base case s = ""
        if (s.equals("")) {
            list.add("");
            return list;
        }
        
        for (int i = 0, len = s.length(); i < len; i++) {
            String str = s.substring(0, i + 1);
            
            // only recurse to next level when dict contains cur substring
            if (set.contains(str)) {
                List<String> subList = dfs(s.substring(i + 1), set, map);
                
                for (String subStr : subList) {
                    String follow = subStr == "" ? "" : " " + subStr;   // deal with substr 1) end of word 2) rest with space between 2 words
                    list.add(str + follow);
                }
            }
        }
        
        map.put(s, list);   // memoize list result for current node s
        
        return list;
    }

}
