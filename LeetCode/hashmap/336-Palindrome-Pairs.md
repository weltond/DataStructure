## [336. Palindrome Pairs](https://leetcode.com/problems/palindrome-pairs/)

Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
```
Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
```
Example 2:
```
Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]
```
## Answer
### Method 2 - Trie - 
```java
// TO DO...
```
### Method 1 - HashMap - :turtle: 72ms (39.96%)
```java
class Solution {
    // 72ms (39.96%)
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList();
        if (words == null || words.length < 2) return ret;
        
        Map<String, Integer> map = new HashMap();
        for (int i = 0; i < words.length; i++) map.put(words[i], i);
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0, len = words[i].length(); j <= len; j++) {
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        List<Integer> list = new ArrayList();
                        list.add(map.get(str2rvs));
                        list.add(i);
                        ret.add(list);
                    }
                }
                
                if (isPalindrome(str2)) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length() != 0) {  // str2.length() != 0 is to avoid duplicates like [abcd, dcba]
                        List<Integer> list = new ArrayList();
                        list.add(i);
                        list.add(map.get(str1rvs));
                        ret.add(list);
                    }
                }
            }
        }
        
        return ret;
    }
    
    private boolean isPalindrome(String str) {
        int l = 0, r = str.length() - 1;
        while (l <= r) {
            if (str.charAt(l++) != str.charAt(r--)) return false;
        }
        
        return true;
    }
}
```
