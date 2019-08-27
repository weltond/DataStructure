## [214. Shortest Palindrome](https://leetcode.com/problems/shortest-palindrome/)

Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

Example 1:
```
Input: "aacecaaa"
Output: "aaacecaaa"
```
Example 2:
```
Input: "abcd"
Output: "dcbabcd"
```
## Answer
### Method 2 - [KMP](https://www.cnblogs.com/grandyang/p/4523624.html) -
```java
// TO DO...
```
### Method 1 - String - :rocket: 1ms (100%)
- Time: O(n^2)
```java
class Solution {
    //1ms (100%)
    public String shortestPalindrome(String s) {
        int j = 0;
        for (int len = s.length(), i = len - 1; i >= 0; i--) {
            if (s.charAt(j) == s.charAt(i)) j++;
        }
        
        if (j == s.length()) return s;
        
        String rem = s.substring(j);
        String rev = new StringBuilder(rem).reverse().toString();
        
        return rev + shortestPalindrome(s.substring(0, j)) + rem;
    }
}
```
