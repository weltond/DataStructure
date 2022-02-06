## [791. Custom Sort String](https://leetcode.com/problems/custom-sort-string/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

    

You are given two strings order and s. All the words of order are unique and were sorted in some custom order previously.

Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.

Return any permutation of s that satisfies this property.

 

Example 1:

```
Input: order = "cba", s = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
```

Example 2:

```
Input: order = "cbafg", s = "abcd"
Output: "cbad"
``` 

Constraints:

- 1 <= order.length <= 26
- 1 <= s.length <= 200
- order and s consist of lowercase English letters.
- All the characters of order are unique.

## Answers
### Method 1 - 0ms - 🚀

```java
class Solution {
    public String customSortString(String order, String s) {
        int[] cnts = new int[26];

        // count each char in s
        for (char c : s.toCharArray()) {
            cnts[c - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        
        // add char exists in s by order
        for (char c : order.toCharArray()) {
            while (cnts[c - 'a']-- > 0) {
                sb.append(c);
            }        
        }
        
        // add remaining not exist in order
        for (int i = 0; i < 26; i++) {
            while (cnts[i]-- > 0) {
                sb.append((char)(i + 'a'));
            }
        }
        
        return sb.toString();
    }
}
```
