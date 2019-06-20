// https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/

/**
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
*/

class Solution {
    // ============= Approach 2 ==============
    // 22ms (77.65%)
    public boolean isSubsequence(String x, String y) {
        int j = 0;
        for (int i = 0; i < y.length() && j < x.length(); i++)
            if (x.charAt(j) == y.charAt(i))
                j++;
        return j == x.length();
    }
    public String findLongestWord(String s, List < String > d) {
        String max_str = "";
        for (String str: d) {
            if (isSubsequence(str, s)) {
                if (str.length() > max_str.length() || (str.length() == max_str.length() && str.compareTo(max_str) < 0))
                    max_str = str;
            }
        }
        return max_str;
    }
    
    // ============= Method 1 ==============
    // 32ms (43.81%)
    public String findLongestWord(String s, List<String> d) {
        int maxSize = 0;
        String res = "";
        for (String str : d) {
            int len = str.length();
            if (len < maxSize) continue;
            if (isMatch(s, str)) {
                if (maxSize == len) {
                    res = str.compareTo(res) <= 0 ? str : res;
                } else {
                    maxSize = len;
                    res = str;
                }
            }
        }
        
        return res;
    }
    
    private boolean isMatch(String s, String d) {
        int lens = s.length(), lend = d.length();
        int i = 0, j = 0;
        
        while (i < lend && j < lens) {
            while (j < lens && s.charAt(j) != d.charAt(i)) j++;
            if (j < lens) {
                i++;
                j++;
            }
        }
        
        return i == lend;
    }
}
