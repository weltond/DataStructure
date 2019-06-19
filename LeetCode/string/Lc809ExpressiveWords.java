// https://leetcode.com/problems/expressive-words/

/**
Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".

For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.

Given a list of query words, return the number of words that are stretchy. 

 

Example:
Input: 
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 

Notes:

0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters
*/

class Solution {
    
    public int expressiveWords(String S, String[] words) {
        if (words.length == 0) return 0;
        
        int cnt = 0;
        for (String w : words) {
            if (isStretchy(w, S)) {
                cnt++;
                //System.out.println(w);
            }
        }
        
        return cnt;
    }
    // =========== Method 2 : 2 pointers ==========
    // 1ms (100%)
    private boolean isStretchy(String w, String s) {
        int lenw = w.length(), lens = s.length();
        int i = 0, j = 0;
        
        for (j = 0; j < lens; j++) {
            if (i < lenw && s.charAt(j) == w.charAt(i)) i++;
            else if (j >= 2 && s.charAt(j) == s.charAt(j - 1) && s.charAt(j - 1) == s.charAt(j - 2)) ;
            else if (j > 0 && j + 1 < lens && s.charAt(j - 1) == s.charAt(j) && s.charAt(j) == s.charAt(j + 1)) ;
            else return false;
        }
        
        return i == lenw;
    }
    
    // =========== Method 1 : 4 pointers ==========
    // 1ms (100%)
    private boolean isStretchy(String w, String s) {
        int lenw = w.length(), lens = s.length();
        int i = 0, j = 0;
        
        for (int i1 = 0, j1 = 0; i < lenw && j < lens; i = i1, j = j1) {
            if (w.charAt(i) != s.charAt(j)) return false;
            
            while (i1 < lenw && w.charAt(i) == w.charAt(i1)) i1++;
            while (j1 < lens && s.charAt(j) == s.charAt(j1)) j1++;
            
            if (j1 - j != i1 - i && (j1 - j) < Math.max(3, i1 - i)) return false;   // "aaaa", "aaa"
        }
        
        return i == lenw && j == lens;  // "abcd", "abc"
    }
}
