## [Question](https://leetcode.com/problems/excel-sheet-column-title/)

Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:
```
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
```
Example 1:
```
Input: 1
Output: "A"
```
Example 2:
```
Input: 28
Output: "AB"
```
Example 3:
```
Input: 701
Output: "ZY"
```
## Answer
:rocket: 0ms 
```java
class Solution {
    // 0ms
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int tmp = (n - 1) % 26 + 65;
            sb.append((char)tmp);
            
            n = (n - 1) / 26;
        }
        return sb.reverse().toString();
    }
}
```
