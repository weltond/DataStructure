## [Question](https://leetcode.com/problems/excel-sheet-column-number/)

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:
```
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
```

Example 1:
```
Input: "A"
Output: 1
```
Example 2:
```
Input: "AB"
Output: 28
```
Example 3:
```
Input: "ZY"
Output: 701
```
## Answer
:rocket: 1ms (99.98%)
```java
class Solution {
    // 1ms (99.98%)
    public int titleToNumber(String s) {
        char[] arr = s.toCharArray();
        
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            int val = c - 65 + 1;
            sum = sum * 26 + val;
        }
        
        return sum;
    }
}
```
