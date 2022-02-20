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
### Approach 2 - Bit - Facebook - 20ms (5.80%)
Here XOR is a key as well, because it's a sum of two binaries without taking carry into account.

To find current carry is quite easy as well, it's AND of two input numbers, shifted one bit to the left.

<img width="236" alt="image" src="https://user-images.githubusercontent.com/9000286/154856526-b5f2c3e1-55ba-4155-a52d-8d5d741d6576.png">

```java
import java.math.BigInteger;
class Solution {
    public String addBinary(String a, String b) {
        // radix is 2
        BigInteger i = new BigInteger(a, 2), j = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        System.out.println(i+","+j);
        BigInteger carry, answer;

        while (j.compareTo(zero) != 0) {
            answer = i.xor(j);
            carry = i.and(j).shiftLeft(1);
            
            i = answer;
            j = carry;
        }
        
        return i.toString(2);
    }
}
```

- Use long. Wrong because `a` or `b` may out of scope.

```java
class Solution {
    public String addBinary(String a, String b) {
        // radix is 2
        Long i = Long.parseLong(a, 2), j = Long.parseLong(b, 2);
        
        System.out.println(i+","+j);

        while (j != 0) {
            long answer = i ^ j;
            long carry = (i & j) << 1;
            
            i = answer;
            j = carry;
        }
        
        return Long.toString(i, 2);
    }
}
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
