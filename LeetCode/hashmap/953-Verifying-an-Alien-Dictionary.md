## [953. Verifying an Alien Dictionary](https://leetcode.com/problems/verifying-an-alien-dictionary/)

In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:
```
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
```
Example 2:
```
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
```
Example 3:
```
Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
```

Note:

- 1 <= words.length <= 100
- 1 <= words[i].length <= 20
- order.length == 26
- All characters in words[i] and order are english lowercase letters.

## Answer
### Method 1 - Naive - :1ms (42.07%)
#### Approach 4
```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];
        int priority = 0;
        for (int i = 0, size = order.length(); i < size; i++) {
            map[order.charAt(i) - 'a'] = priority++;
        }

        String prev = words[0];
        int idx = 1;
        while (idx < words.length) {
            String cur = words[idx];
            int ic = 0, ip = 0, csize = cur.length(), psize = prev.length();
            while (ic < csize && ip < psize && cur.charAt(ic) == prev.charAt(ip)) {
                ic++;
                ip++;
            } 

            if (ip < psize && (ic == csize || map[cur.charAt(ic) - 'a'] < map[prev.charAt(ip) - 'a'])) {
                return false;
            }
            
            idx++;
            prev = cur;
        }

        return true;
    }
}
```
#### Approach 3

```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length <= 1) return true;
        
        int[] arr = new int[26];
        for (int i = 0, len = order.length(); i < len; i++) {
            arr[order.charAt(i) - 'a'] = i;
        }
        
        int i = 0;
        while (i < words.length - 1) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int l1 = w1.length(), l2 = w2.length();
            boolean isEnd = true;
            
            for (int k = 0; k < Math.min(l1, l2); k++) {
                char c1 = w1.charAt(k), c2 = w2.charAt(k);
                if (c1 != c2) {
                    if (arr[c1 - 'a'] > arr[c2 - 'a']) {
                        return false;
                    } else {
                        isEnd = false;
                        break;
                    }
                }
            }
            
            if (isEnd && l1 > l2) return false;
            
            i++;
        }
        
        return true;
    }
}
```

#### Approach 2

```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] arr = new int[26];
        int pos = 1;
        for (char c : order.toCharArray()) {
            arr[c - 'a'] = pos++;
        }
        // 1. compare each char from first to end.
        // 2. if prev word reach end but cur not. return false
        String pre = words[0];
        
        int idx = 1;
        
        while (idx < words.length) {
            String cur = words[idx++];
            
            int i = 0, j = 0, len1 = pre.length(), len2 = cur.length();
            
            boolean notEqual = false;
            while (i < len1 && j < len2) {
                char c1 = pre.charAt(i++), c2 = cur.charAt(j++);
                if (c1 != c2) {
                    if (arr[c1 - 'a'] > arr[c2 - 'a']) return false;
                    else {
                        notEqual = true;
                        break;
                    }
                }
            }
            
            if (!notEqual && i != len1 && j == len2) return false;
            
            pre = cur;
        }
        
        return true;
    }
}
```

#### Approach 1
**Wrong Solution below when two words are the same. However, LeetCode accepted the following code......**

```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < 26; i++) {
            map.put(order.charAt(i), i);
        }
        
        int s = 0;
        for (int i = 1; i < words.length; i++) {
            int j = 0, k = 0;
            while (j < words[s].length() && k < words[i].length() && words[s].charAt(j) == words[i].charAt(k)) {
                j++;k++;
            }
            if (j != words[s].length() && k ==  words[i].length()) return false;
            if (map.get(words[s].charAt(j)) > map.get(words[i].charAt(k))) return false;
            s++;
        }
        
        return true;
    }
}
```
