## [67. Add Binary](https://leetcode.com/problems/add-binary/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Given two binary strings a and b, return their sum as a binary string.

 

Example 1:

```
Input: a = "11", b = "1"
Output: "100"
```

Example 2:

```
Input: a = "1010", b = "1011"
Output: "10101"
``` 

**Constraints**:

- 1 <= a.length, b.length <= 104
- a and b consist only of '0' or '1' characters.
- Each string does not contain leading zeros except for the zero itself.


## Answers
### Approach 2 - Bit - Facebook -

```java

```

### Approach 1 - Add - 🚀 1ms (100%)

```java
class Solution {
    public String addBinary(String a, String b) {
        int lena = a.length() - 1;
        int lenb = b.length() - 1;
        int cnt = 0, carry = 0;
        StringBuilder sb = new StringBuilder();
        
        while (lena >= 0 || lenb >= 0) {
            cnt = 0;
            cnt += (lena >= 0) ? a.charAt(lena) - '0' : 0;
            cnt += (lenb >= 0) ? b.charAt(lenb) - '0' : 0;
            cnt += carry;
            
            carry = cnt / 2;
            cnt = cnt % 2;
            
            sb.append(cnt);
            
            lena--;
            lenb--;
        }
        
        if (carry == 1) sb.append(1);
        
        return sb.reverse().toString();
    }
}
```
