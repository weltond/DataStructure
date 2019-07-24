## [13. Roman to Integer](https://leetcode.com/problems/roman-to-integer/)

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
```
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

- `I` can be placed before `V` (5) and `X` (10) to make 4 and 9. 
- `X` can be placed before `L` (50) and `C` (100) to make 40 and 90. 
- `C` can be placed before `D` (500) and `M` (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:
```
Input: "III"
Output: 3
```
Example 2:
```
Input: "IV"
Output: 4
```
Example 3:
```
Input: "IX"
Output: 9
```
Example 4:
```
Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
```
Example 5:
```
Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```

## Answer
### Method 2 :rabbit: 3ms 
```java
class Solution {
    public int romanToInt(String s) {
        char[] arr = s.toCharArray();
        // * If I comes before V or X, subtract 1 eg: IV = 4 and IX = 9
        // * If X comes before L or C, subtract 10 eg: XL = 40 and XC = 90
        // * If C comes before D or M, subtract 100 eg: CD = 400 and CM = 900
        
        int sum = 0;
        for (int i = 0, len = s.length(); i < len; i++) {
            if (arr[i] == 'I') {
                if (i + 1 < len && arr[i + 1] == 'V') {
                    i++;
                    sum += 4;
                } else if (i + 1 < len && arr[i + 1] == 'X') {
                    i++;
                    sum += 9;
                } else {
                    sum += 1;
                }
            } else if (arr[i] == 'X') {
                if (i + 1 < len && arr[i + 1] == 'L') {
                    i++;
                    sum += 40;
                } else if (i + 1 < len && arr[i + 1] == 'C') {
                    i++;
                    sum += 90;
                } else {
                    sum += 10;
                }
            } else if (arr[i] == 'C') {
                if (i + 1 < len && arr[i + 1] == 'D') {
                    i++;
                    sum += 400;
                } else if (i + 1 < len && arr[i + 1] == 'M') {
                    i++;
                    sum += 900;
                } else {
                    sum += 100;
                }
            } else if (arr[i] == 'L') {
                sum += 50;
            } else if (arr[i] == 'V') {
                sum += 5;
            } else if (arr[i] == 'D') {
                sum += 500;
            } else if (arr[i] == 'M') {
                sum += 1000;
            }
        }
        
        return sum;
    }
}
```
### Method 1 :rabbit: 6ms (51.87%)
```java
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int res = 0;
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            int cur = map.get(c);
            // add if last char or current char is greater than its right neighbor
            if (i == len - 1 || cur >= map.get(s.charAt(i + 1))) res += cur;
            else res -= cur;
        }
        
        return res;
    }
}
```
