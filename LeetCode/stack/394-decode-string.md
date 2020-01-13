## [394. Decode String](https://leetcode.com/problems/decode-string/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

```
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
```

## Answer
### Method 1 - Recursion - :rabbit: 1ms (58.75%)

- Every time we meet a `[`, we treat it as a subproblem so call our recursive function to get the content in that `[` and `]`. After that, repeat that content for `val` times.
- Every time we meet a `]`, we know a subproblem finished and just return the 'word' we got in this subproblem.
- `i` is global

```java
class Solution {
    int i = 0;
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return s;

        return dfs(s);
    }
    
    
    private String dfs(String s) {
        String res = "";
        
        int len = s.length();
        int val = 0;
        char c;
        
        while (i < len) {
            c = s.charAt(i);

            if (Character.isLetter(c)) res += c;
            else if (c == ']') return res;  // return when meet ']'
            else if (Character.isDigit(c)) {
                val = 0;
                while ((c = s.charAt(i)) >= '0' && c <= '9') {
                    val = val * 10 + c - '0';
                    i++;
                }
                
                // here, i is [
                ++i;
                String ret = dfs(s);
                
                for (int k = 0; k < val; k++) {
                    res += ret;
                }
            }
            
            i++;
            
        }
        return res;
    }
}
```
