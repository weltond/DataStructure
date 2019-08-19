## [151. Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/)

Given an input string, reverse the string word by word.

Example 1:
```
Input: "the sky is blue"
Output: "blue is sky the"
```
Example 2:
```
Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
```
Example 3:
```
Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
```

Note:

- A word is defined as a sequence of non-space characters.
- Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
- You need to reduce multiple spaces between two words to a single space in the reversed string.

## Answer
### Method 2 - API -
```java
// TO DO...
```
### Method 1 - Brute Force - :rocket: 2ms (84.22%)
```java
public class Solution {
    // 2ms (84.22%)
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";
        //if (s.length() == 1) return s;
        int i = s.length() - 1, j = 0, idx = 0, tmp = 0;
        // pre processing leading space
        while (j < s.length() && s.charAt(j) == ' ') {
            j++;   
        }
        // pre processing trailing space
        while (i > 0 && s.charAt(i) == ' ') {
            i--;
        }
        if (j > i + 1) return "";
        char[] a = s.substring(j, i + 1).toCharArray();
        
        j = 0; i = 0;
        
        while (j <= a.length) {
            if (j == a.length || a[j] == ' ') {
                reverse(a, i, j - 1);
                tmp = i;
                int tmp1 = idx;

                // fill in remaining spaces
                for (int x = 0; x < j - i; x++) {
                    a[idx++] = a[tmp++];
                }
                
                //idx = tmp1 + j - i + 1; // pos for new substring

                // don't forget to set this idx to empty space!
                if (idx < a.length) a[idx] = ' ';  
                
                idx++;
                
                while (j < a.length && a[j] == ' ') {
                    j++;
                }
                
                i = j;
                // if (j < a.length) {
                //     a[idx - 1] = ' ';
                //     i = j;
                // }   
            }
            j++;
        }
        
        // reverse the whole char array
        reverse(a, 0, idx - 2);
        return new String(a).substring(0, idx - 1);
    }
    
    // ============= HELPER ===============
    public void reverse(char[] a, int left, int right) {
        if (left >= right) return;
        
        swap(a, left, right);
        
        reverse(a, left + 1, right - 1);
    }
    
    private void swap(char[] a, int l, int r) {
        char tmp = a[l];
        a[l] = a[r];
        a[r] = tmp;
    }
}
```
