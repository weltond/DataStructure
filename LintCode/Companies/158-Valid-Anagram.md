## [158. Valid Anagram](https://www.lintcode.com/problem/valid-anagram/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Write a method anagram(s,t) to decide if two strings are anagrams or not.

Example 1:

```
Input: s = "ab", t = "ab"
Output: true
```

Example 2:

```
Input:  s = "abcd", t = "dcba"
Output: true
```

Example 3:

```
Input:  s = "ac", t = "ab"
Output: false
```

- Challenge: **O(n) time, O(1) extra space**

## Answer
### Method 2 - XOR - :rocket: 201ms

```java
public class Solution {
    /**
     * @param s: The first string
     * @param t: The second string
     * @return: true or false
     */
    public boolean anagram(String s, String t) {
        int cor = 0;
        // System.out.println(97 ^ 98); //3
        // System.out.println(121 ^ 122);   //3
        int hash = 0;
        for (char c : s.toCharArray()) {
            cor ^= c;
            hash += c * c % 26;
        }
        
        for (char c : t.toCharArray()) {
            cor ^= c;
            hash -= c * c % 26;
        }
        
        return cor == 0 && hash == 0;
    }
}
```

### Method 1 - Hash Table - :rocket: 201ms (99.80%)
#### Approach 2

```java
public class Solution {
    /**
     * @param s: The first string
     * @param t: The second string
     * @return: true or false
     */
    public boolean anagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        
        int[] freq = new int[256];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            freq[s.charAt(i)]++;
            freq[t.charAt(i)]--;
        }
        
        for (int i = 0; i < 256; i++) {
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
````

#### Approach 1

```java
public class Solution {
    /**
     * @param s: The first string
     * @param t: The second string
     * @return: true or false
     */
    public boolean anagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        
        Map<Character, Integer> map = new HashMap();
        
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) return false;
            else {
                int freq = map.get(c);
                if (freq <= 0) return false;
                map.put(c, freq - 1);
            }
        }
        
        return true;
    }
}
```
