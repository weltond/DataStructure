package BFS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 */
public class Lc127WordLadder {
}

class MySolution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> s = new HashSet<>();
        for(String word: wordList) {
            s.add(word);
        }
        return ladderLength(beginWord, endWord, s);
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Set<String> reached = new HashSet<>();
        reached.add(beginWord);
        if (!wordList.contains(endWord)) return 0;
        int distance = 1;
        while(!reached.contains(endWord)) {
            Set<String> toAdd = new HashSet<>();
            for(String each: reached) {
                for(int i = 0; i < each.length(); i++) {
                    char[] wordChars = each.toCharArray();
                    for(char ch = 'a'; ch <= 'z'; ch++) {
                        wordChars[i] = ch;
                        String s = new String(wordChars);
                        if (wordList.contains(s)) {
                            toAdd.add(s);
                            wordList.remove(s);
                        }
                    }
                }
            }
            distance += 1;
            if (toAdd.size() == 0) return 0;
            reached = toAdd;
        }

        return distance;
    }
}

class FastSolution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)) return 0;
        return search(beginSet, endSet, dict, 1);
    }

    private int search(Set<String> beginSet, Set<String> endSet, Set<String> dict, int cnt){
        // beginSet-->dict-->endSet, current #step=cnt, return the final #steps
        if(beginSet.isEmpty() || endSet.isEmpty()) return 0;
        cnt++;
        dict.removeAll(beginSet);// directly delete beginSet
        Set<String> nextSet = new HashSet<>();
        for(String str : beginSet){
            char[] chs = str.toCharArray();
            for(int i = 0; i < chs.length; i++){
                char c = chs[i];
                for(char j = 'a'; j <= 'z'; j++){
                    chs[i] = j;
                    String tmp = new String(chs);
                    if(!dict.contains(tmp)) continue;
                    if(endSet.contains(tmp)) return cnt;
                    nextSet.add(tmp);
                }
                chs[i] = c;
            }
        }
        return nextSet.size() > endSet.size() ? search(endSet, nextSet, dict, cnt) : search(nextSet, endSet, dict, cnt);// always begin search from the smaller set
    }
}