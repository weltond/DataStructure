## [227. Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/)

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:
```
Input: "3+2*2"
Output: 7
```
Example 2:
```
Input: " 3/2 "
Output: 1
```
Example 3:
```
Input: " 3+5 / 2 "
Output: 5
```
Note:

- You may assume that the given expression is always valid.
- Do not use the eval built-in library function.

## Answer

### Method 3 :rabbit: 19ms (50.00%)

```java
class Solution {
    public int calculate(String s) {
        int sum = 0;
        int tmp = 0, pre = 0;
        
        int idx = 0, len = s.length(), sign = 1;
        char symbol = ' ';
        while (idx < len) {
            char c = s.charAt(idx++);
            if (Character.isDigit(c)) {
                tmp = c - '0';
                while (idx < len && Character.isDigit(s.charAt(idx))) {
                    tmp = tmp * 10 + s.charAt(idx++) - '0';
                }
                
                if (symbol == '*') {
                    sum = sum - pre + pre * tmp;
                    pre = pre * tmp;
                }
                    
                else if (symbol == '/') {
                    sum = sum - pre + pre / tmp;
                    pre = pre / tmp;
                }
                    
                
                else {
                    sum += tmp * sign; 
                    pre = sign == 1 ? tmp : -tmp;
                }
            } else if (c == '+') {
                symbol = ' ';
                sign = 1;
            } else if (c == '-') {
                symbol = ' ';
                sign = -1;
            } else if (c == '*') {
                symbol = '*';
            } else if (c == '/') {
                symbol = '/';
            }
        }
        
        return sum;
    }
}
```

### Method 2 :rocket: 7ms (97.58%)
```java
class Solution {
    public int calculate(String s) {
        int len = s.length(), res = 0, mul = 1, tmp = 0;
        boolean isMul = true, isAdd = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c <= '9' && c >= '0') {
                tmp = c - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    tmp = tmp * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                continue;
            }
            if (c == ' ') continue;
            mul = isMul ? mul * tmp : mul / tmp;
            if (c == '+' || c == '-') {
                res = isAdd ? res + mul : res - mul;    //last + or -
                tmp = 0;
                mul = 1;
                isMul = true;
                isAdd = c == '+';   // current + or -
            } else if (c == '*' || c == '/') {
                tmp = 0;
                isMul = c == '*';
            }
        }
        mul = isMul ? mul * tmp : mul / tmp;
        
        return isAdd ? res + mul : res - mul;
    }
    
    // public OP getOp(char c) {
    //     switch c:
    //     case '+':
    //     return OP.ADD;
    //     case '-':
    //     return OP.MINUS;
    //     case '*':
    //     return OP.MULTI;
    //     case '/':
    //     return OP.DIV;
    // }
}

// enum OP {
//     ADD('+', 0), MINUS('-', 0), MULTI('*', 1), DIV('/', 1);
//     private char c;
//     private int lvl;
//     OP(char c, int lvl) {
//         this.c = c;
//         this.lvl = lvl;
//     }
//     public int getLvl() {
//         return lvl;
//     }
//     public char getVal() {
//         return c;
//     }
// }
```
### Method 1 :rabbit: 13ms(81.21%)
```java
class Solution {
    // 13ms (81.21%)
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList();
        int res = 0;
        int symbol = 1;
        char sign = '+';
        int tmp = 0;
        s = s.trim();
        for (int i = 0, len = s.length(); i < len; i++) {
            while (i < len && Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i++) - '0';
                tmp = tmp * 10 + num;
            }
            if ((i < len && s.charAt(i) != ' ') || i == len) {
                if (sign == '+') {
                    stack.push(tmp);
                } else if (sign == '-') {
                    stack.push(-tmp);
                } else if (sign == '*') {
                    stack.push(tmp * stack.pop());
                } else if (sign == '/') {
                    stack.push(stack.pop() / tmp);
                }
                
                tmp = 0;
                if (i < len)
                    sign = s.charAt(i);
            }
        }
        
        while (!stack.isEmpty()) {
            int i = stack.pop();
            res += i;
        }
        
        return res;
    }
}
```
