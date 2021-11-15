## [6. ZigZag Conversion](https://leetcode.com/problems/zigzag-conversion/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

The string `"PAYPALISHIRING"` is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

```
P   A   H   N
A P L S I I G
Y   I   R
```

And then read line by line: `"PAHNAPLSIIGYIR"`

Write the code that will take a string and make this conversion given a number of rows:

`string convert(string s, int numRows);`

Example 1:

```
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
```

Example 2:

```
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
```

## Answer
### Method 2 - :rocket: 3ms (97.01%)

The idea is to find the regularity and process line by line. Unlike `Method 1` which process column by column.

```java
class Solution {
    public String convert(String s, int numRows) {

        if (s == null) return "";
        int len = s.length();
        if (len == 0 || numRows < 2 || numRows >= len) return s;
        
        char[] arr = s.toCharArray();
        int idx = 0;
        
        int grandIndex = numRows * 2 - 2;
        
        for (int i = 0; i < numRows; i++) {
            for (int j = i, sum = 2 * i; j < len;) {
                arr[idx++] = s.charAt(j);
                if (sum < grandIndex) {
                    sum = grandIndex - sum;
                }
                j += sum;
            }
        }
        
        return String.valueOf(arr);
    }
}
```

### Method 1 - :turtle: 29ms (16.60%)

The idea is to create a container for each row while processing. At the end, generate output from left to right, top to down.

```java
class Solution {
    public String convert(String s, int numRows) {
       if (s == null || s.length() == 0) return "";
        if (numRows >= s.length() || numRows == 1) return s;
        List<StringBuilder> list = new LinkedList();
        
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        boolean goingDown = false;
        int row = 0;
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            list.get(row).append(c);
            
            if (row == 0 || row == numRows - 1) goingDown = !goingDown;
            
            row = goingDown ? row + 1 : row - 1;
        }
        
        StringBuilder ret = new StringBuilder();
        for (StringBuilder sb : list) {
            ret.append(sb);
        }
        return ret.toString();
    }
}
```
