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
### Method 2 - Stack - :rocket: 0ms

```java
class Solution {
    
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return s;

        Deque<Integer> nstack = new LinkedList();
        Deque<String> cstack = new LinkedList();
        
        String res = "";
        int len = s.length(), idx = 0;
        
        while (idx < len) {
            char c = s.charAt(idx);
            // if is digit. put it to number stack.
            if (Character.isDigit(c)) {
                int val = 0;
                while ((c = s.charAt(idx)) >= '0' && c <= '9') {
                    val = val * 10 + c - '0';
                    idx++;
                }
                
                nstack.push(val);
                idx--;
            }
            // if see a [, push previous string into cstack
            else if (c == '[') {
                cstack.push(res);
                res = "";
            } 
            // if see a ], pop num and prev string from two stacks
            else if (c == ']') {
                int rec = nstack.pop();
                String pre = cstack.pop();
                
                StringBuilder sb = new StringBuilder(pre);
                for (int k = 0; k < rec; k++) {
                    sb.append(res);
                }
                
                res = sb.toString();
            }
            // else, it is char, store it in res.
            else {
                res += c;
            }
            // don't forget to inc idx.
            idx++;
        }
        
        return res;
    }
    
    
}
```

### Method 1 - Recursion - :rabbit: 1ms (58.75%)

- Every time we meet a `[`, we treat it as a subproblem so call our recursive function to get the content in that `[` and `]`. After that, repeat that content for `val` times.
- Every time we meet a `]`, we know a subproblem finished and just return the 'word' we got in this subproblem.
- `i` is global

```java
class Solution {
    int idx = 0;
    public String decodeString(String s) {
        return dfs(s);
    }

    private String dfs(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();

        int repeat = 0;
        String tmp = "";
        while (idx < chars.length) {
            char c = chars[idx];
            //System.out.println(c);
            if (c == '[') {
                idx++;
                tmp = dfs(s);

                for (int i = 0; i < repeat; i++) {
                    sb.append(tmp);
                }

                repeat = 0;
            } else if (c == ']') {
                return sb.toString();
            } else if (Character.isDigit(c)) {
                while (idx < chars.length && Character.isDigit(chars[idx])) {
                    repeat = repeat * 10 + chars[idx] - '0';
                    idx++;
                }
                idx--;
            } else {
                sb.append(c);
            }
            idx++;
        }

        return sb.toString();
    }
}
```

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
