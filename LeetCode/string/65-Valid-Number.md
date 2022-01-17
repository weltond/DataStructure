## [65. Valid Number](https://leetcode.com/problems/valid-number/)

Validate if a given string can be interpreted as a decimal number.

Some examples:
```
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false
```
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

- Numbers `0-9`
- Exponent - `"e"`
- Positive/negative sign - `"+"/"-"`
- Decimal point - `"."`
- Of course, the context of these characters also matters in the input.

## Answer

### Method 1  - :rocket: 1ms (100%)
```java
class Solution {
    // 1ms (100%)
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        int len = s.length();
    
        int i = 0, head = 0, j = len - 1;
        
        while (i < len && s.charAt(i) == ' ') i++;
        if (i == len) return false; // " "
        int start = i;
        while (j >= i && s.charAt(j) == ' ') j--;
        len = j + 1;
        
        boolean hasE = false, hasDot = false, hasNum = false, numAfterE = false;
        while (i < len) {
            char c = s.charAt(i);
            // num
            if ('0' <= c && c <= '9') {
                hasNum = true;
                numAfterE = true;
            }    
            
            // '.' 
            else if (c == '.') {
                if (hasDot || hasE) return false;   //"." return false as well
                hasDot = true;
            } 
            
            // 'e'
            else if (c == 'e' || c == 'E') {
                if (hasE || !hasNum) return false;
                hasE = true;
                numAfterE = false;
            }
            
            // '+-'
            else if (c == '+' || c == '-') {    // '+' or '-' can be before num or after e
                if (i != start && s.charAt(i - 1) != 'e') return false;
            }
            
            // char or space
            else return false;
            
            i++;
        }
        
        return hasNum && numAfterE;
    }
}
```

### Method 2 - Math - :turtle: 5ms (26.92%)

```java
class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        s = s.trim() + " "; // space for dummy
        
        char[] arr = s.toCharArray();
        // symbol + "." / num + "e" + symbol + num
        int i = 0, len = arr.length - 1;
        
        // + or -
        if (arr[i] == '+' || arr[i] == '-') i++;
        
        // calc how many point and numbers
        int nPoint = 0, nNum = 0;
        while (Character.isDigit(arr[i]) || arr[i] == '.') {
            if (Character.isDigit(arr[i])) nNum++;
            else nPoint++;
            i++;
        }
        
        if (nPoint > 1 || nNum <= 0) return false;
        
        // after e
        if (arr[i] == 'e') {
            i++;
            if (arr[i] == '+' || arr[i] == '-') {
                i++;
            }
            
            // no number after e
            if (len == i) return false;
            
            for (; i < len; i++) {
                if (!Character.isDigit(arr[i])) return false;
            }
        }
        
        return i == len;
        
    }
}
```
