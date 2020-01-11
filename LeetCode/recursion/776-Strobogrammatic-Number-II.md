## [776. Strobogrammatic Number II](https://www.lintcode.com/problem/strobogrammatic-number-ii/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example

```
Given n = 2, return ["11","69","88","96"].
```

- Hint: Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.

## Answer
### Idea
```
n = 0:   none

n = 1:   0, 1, 8

n = 2:   11, 69, 88, 96

n = 3:   101, 609, 808, 906, 111, 619, 818, 916, 181, 689, 888, 986

n = 4:   1001, 6009, 8008, 9006, 1111, 6119, 8118, 9116, 1691, 6699, 8698, 9696, 1881, 6889, 8888, 9886, 1961, 6969, 8968, 9966
```
compare n = 1 & n = 3. For n = 3, it add (1,1), (6,9), (8,8) and (9,6) on both side of num in n = 1.

### Method 1 - DFS - 

```java
public class Solution {
    /**
     * @param n: the length of strobogrammatic number
     * @return: All strobogrammatic numbers
     */
    public List<String> findStrobogrammatic(int n) {
        
        return dfs(n, n);
    }
    
    private List<String> dfs(int m, int n) {
        List<String> res = new LinkedList();
        if (m == 0) {
            res.add("");
            return res;
        }
        
        if (m == 1) {
            res.add("0");
            res.add("1");
            res.add("8");
            return res;
        }
        
        List<String> list = dfs(m - 2, n);
        
        for (String s : list) {
            if (m != n) {
                res.add("0" + s + "0");
            }
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        
        return res;
    }
}
```
