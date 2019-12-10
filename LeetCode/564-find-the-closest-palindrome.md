## [564. Find the Closest Palindrome](https://leetcode.com/problems/find-the-closest-palindrome/)

Given an integer n, find the closest integer (not including itself), which is a palindrome.

The 'closest' is defined as absolute difference minimized between two integers.

Example 1:

```
Input: "123"
Output: "121"
```

Note:
- The input n is a positive integer represented by string, whose length will not exceed 18.
- If there is a tie, return the smaller one as answer.

## Answer
### Method 1 :rocket: 2ms (97.35%)

```java
class Solution {
    public String nearestPalindromic(String n) {
        Long number = Long.parseLong(n);
        Long big = findNext(number, 1); //get next bigger palindrome
        Long small = findNext(number, -1);  // get next smaller palindrome
        return Math.abs(number - small) > Math.abs(number - big) ? "" + big : "" + small;
    }
    
    private Long findNext(Long l, int offset) {
        String n  = Long.toString(l + offset);
        char[] s = n.toCharArray();
        int m = s.length;
        
        char[] t = Arrays.copyOf(s, m); //target
        
        makePalindrome(t);
        
        for (int i = 0; i < m; i++) {
            if (s[i] < t[i]) {
                // if get larger, just return
                if (offset > 0) return Long.parseLong(String.valueOf(t));
                
                // if get smaller
                for (int j = (m - 1) / 2; j >= 0; j--) {
                    if (--t[j] < '0') {
                        t[j] = '9';
                    } else {
                        break;
                    }
                }
                
                // corner case: "11"
                if (t[0] == '0') {
                    char[] tmp = new char[m - 1];
                    Arrays.fill(tmp, '9');
                    return Long.parseLong(String.valueOf(tmp));
                }
                
                makePalindrome(t);
                return Long.parseLong(String.valueOf(t));
                
            } else if (s[i] > t[i]) {
                // if get smaller, just return
                if (offset < 0) return Long.parseLong(String.valueOf(t));
                
                // if get larger
                for (int j = (m - 1) / 2; j >= 0; j--) {
                    if (++t[j] > '9') {
                        t[j] = '0';
                    } else {
                        break;
                    }
                }
                makePalindrome(t);
                return Long.parseLong(String.valueOf(t));
            }
        }
        
        return Long.parseLong(String.valueOf(t));
    }
    
    private void makePalindrome(char[] t) {
        for (int i = 0; i < t.length / 2; i++) {
            t[t.length - 1 - i] = t[i];
        }
    }
}
```
