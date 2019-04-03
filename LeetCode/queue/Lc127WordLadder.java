// https://leetcode.com/problems/word-ladder/


class Solution {
    // ========= Method 1: Bidirectional BFS ============
    // 36ms (90.59%)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> list = new HashSet();
        for (String str : wordList) {
            list.add(str);
        }
        
        Set<String> end = new HashSet();
        Set<String> begin = new HashSet();
        
        if (list.contains(endWord)) {  
            end.add(endWord);
        }
        begin.add(beginWord);
        
        int steps = 0;
        // bidirection
        while (!end.isEmpty() && !begin.isEmpty()) {
            steps++;
            Set<String> tmp = new HashSet();
            
            // iterate each word in begin set and generate possible word 
            for (String st : begin) {
                //System.out.println(st);
                char[] arr = st.toCharArray();
                for (int j = 0; j < beginWord.length(); j++) {
                    char ch = arr[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == ch) continue;
                        arr[j] = c;
                        String s = new String(arr);
                        
                        // return if found a matching word in end set
                        if (end.contains(s)) {
                            return steps + 1;
                        }
                        // otherwise add it to tmp(next round of end)
                        if (list.contains(s)) {
                            //System.out.println("?" + s);
                            tmp.add(s);
                            list.remove(s); // remove it from wordList to avoid re-traverse
                        }
                    }
                    arr[j] = ch;
                }
            }
            //System.out.println("===");
            
            //swap the "Queue" to decrease the BFS search space
            begin = end;
            end = tmp;

        }
        
        // no matching found
        return 0;
    }
}
