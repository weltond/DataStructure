## [174. Dungeon Game](https://leetcode.com/problems/dungeon-game/)

Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:

You may assume that both strings contain only lowercase letters.
```
canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
```
## Answer
### Method 1 - :rocket: 2ms(98.66%)
```java
class Solution {
    // 2ms(98.66%)
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] arr = new char[26];
        int len = ransomNote.length();
        if (len == 0) return true;
        
        for (int i = 0; i < len; i++) {
            arr[ransomNote.charAt(i) - 'a']++;
        }
        
        for (int i = 0, l = magazine.length(); i < l; i++) {
            char c=  magazine.charAt(i);
            if (arr[c - 'a'] == 0) continue;
            
            if (--len == 0) return true;
            arr[c - 'a']--;
        }
        
        return false;
    }
}
```
