## [849. Basic Calculator III](https://www.lintcode.com/problem/basic-calculator-iii/description)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open `(` and closing parentheses `)`, the plus `+` or minus sign `-`, non-negative integers and empty spaces .

The expression string contains only non-negative integers, `+`, `-`, `*`, `/` operators , open `(` and closing parentheses `)` and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647]


Example 1:

```
Input："1 + 1"
Output：2
Explanation：1 + 1 = 2
```

Example 2:

```
Input：" 6-4 / 2 "
Output：4
Explanation：4/2=2，6-2=4
```

- Notice: Do not use the eval built-in library function.

## Answer
### Method 1 - Recursion - :rocket: 201ms (98.00%)

```java
public class Solution {
    /**
     * @param s: the expression string
     * @return: the answer
     */
    int idx, len;
    public int calculate(String s) {
        // Write your code here
        idx = 0;
        len = s.length();
        
        return dfs(s);
    }
    
    private int dfs(String s) {
        int tmp = 0, sign = 1, pre = 0, sum = 0;
        char symbol = '+';
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
                } else if (symbol == '/') {
                    sum = sum - pre + pre / tmp;
                    pre = pre / tmp;
                } else {
                    sum += tmp * sign;
                    pre = sign == 1 ? tmp : -tmp;
                }
            } else if (c == '+') {
                sign = 1;
                symbol = ' ';
            } else if (c == '-') {
                sign = -1;
                symbol = ' ';
            } else if (c == '*') {
                symbol = '*';
            } else if (c == '/') {
                symbol = '/';
            } else if (c == '(') {
                tmp = dfs(s);
                if (symbol == '*') {
                    sum = sum - pre + pre * tmp;
                    pre = pre * tmp;
                } else if (symbol == '/') {
                    sum = sum - pre + pre / tmp;
                    pre = pre / tmp;
                } else {
                    sum += tmp * sign;
                    pre = sign == 1 ? tmp : -tmp;
                }
            } else if (c == ')') {
                return sum;
            }
        }
        
        return sum;
    }
}
```
