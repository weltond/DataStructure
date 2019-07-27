## [38. Count and Say](https://leetcode.com/problems/count-and-say/)

The count-and-say sequence is the sequence of integers with the first five terms as following:
```
1.     1
2.     11
3.     21
4.     1211
5.     111221
```

`1` is read off as `"one 1"` or `11`.
                       
`11` is read off as `"two 1s"` or `21`.
                       
`21` is read off as `"one 2, then one 1"` or `1211`.

Given an integer n where `1 ≤ n ≤ 30`, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:
```
Input: 1
Output: "1"
```                       
Example 2:
```
Input: 4
Output: "1211"
```                       
                      
## Answer
### Method 1 - :rocket: 1ms (99.07%)
#### Approach 2                       
```java
class Solution {
    public String countAndSay(int n) {
        String ret = "1";
        
        while (--n > 0) {
            ret = cnt(ret);
        }
        
        return ret;
    }
    
    private String cnt(String s) {
        int len = s.length();
        
        StringBuilder sb = new StringBuilder();

        int cnt = 0, i = 0;
        while (i < len) {
            char c = s.charAt(i);
            while (i < len && c == s.charAt(i)) {
                cnt++;
                i++;
            }
            sb.append(cnt).append(c);
            cnt = 0;
        }
        
        return sb.toString();
    }
}
```
#### Approach 1                       
```java
class Solution {
    public String countAndSay(int n) {
        String ret = "1";
        
        while (--n > 0) {
            ret = cnt(ret);
        }
        
        return ret;
    }
    
    private String cnt(String s) {
        int len = s.length();
        
        StringBuilder sb = new StringBuilder();
        
        int cnt = 1;
        char prev = s.charAt(0);
        for(int i = 1; i < len; i++) {
            if (s.charAt(i) == prev) cnt++;
            else {
                sb.append((char)(cnt + '0'));
                sb.append(prev);
                prev = s.charAt(i);
                cnt = 1;
            }
        }
        sb.append(cnt);
        sb.append(prev);
        return sb.toString();
    }
}
```
