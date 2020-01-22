## [271. Encode and Decode Strings]()

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Please implement encode and decode

Example1

```
Input: ["lint","code","love","you"]
Output: ["lint","code","love","you"]
Explanation:
One possible encode method is: "lint:;code:;love:;you"
```

Example2

```
Input: ["we", "say", ":", "yes"]
Output: ["we", "say", ":", "yes"]
Explanation:
One possible encode method is: "we:;say:;:::;yes"
```

## Answer
### Method 1 : rabbit: 1263ms (42.40%)

- length + "/" + string

```java
public class Solution {
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        // write your code here
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            if (s == null) continue;
            sb.append(s.length()).append("/").append(s);
        }
        
        return sb.toString();
    }

    /*
     * @param str: A string
     * @return: dcodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        // write your code here
        List<String> res = new ArrayList();
        int i = 0, len = str.length();
        
        while (i < len) {
            int idx = str.indexOf("/", i);
            // get length
            int tmp = 0, j = i;
            while (j < idx) {
                tmp = tmp * 10 + str.charAt(j++) - '0';
            }
            res.add(str.substring(idx + 1, idx + tmp + 1)); // end is exclusive
            i = idx + tmp + 1;
        }
        
        return res;
    }
}
```
