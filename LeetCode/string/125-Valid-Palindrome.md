## [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:
```
Input: "A man, a plan, a canal: Panama"
Output: true
```
Example 2:
```
Input: "race a car"
Output: false
```
## Answer
### Method 1 - Brute Force - :rabbit: 4ms (83.17%)
```java
class Solution {
    // 4ms (83.17%)
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        
        int i = 0, j = s.length() - 1;
        s = s.toLowerCase();
        
        while (i < j) {
            char x = s.charAt(i);
            char y = s.charAt(j);
  
            // Don't forget "i < j"
            while (i < j && !isAlpha(x)) {
                x = s.charAt(++i);
            }
                
            while (i < j && !isAlpha(y)) {
                y = s.charAt(--j);
            }
            
            if (i < j && x != y) {
                return false;
            }
            i++; j--;
        }
        
        return true;
    }
    
    private boolean isAlpha(char c) {
        if ((c >= 48 && c <= 57) || (c >= 97 && c <= 122)) {
            return true;
        }
        return false;
    }
}
```
