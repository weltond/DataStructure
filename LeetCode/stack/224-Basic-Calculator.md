## [224. Basic Calculator](https://leetcode.com/problems/basic-calculator/)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open `(` and closing parentheses `)`, the plus `+` or minus sign `-`, non-negative integers and empty spaces ` `.

Example 1:

```
Input: "1 + 1"
Output: 2
```

Example 2:

```
Input: " 2-1 + 2 "
Output: 3
```

Example 3:

```
Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
```

Note:
- You may assume that the given expression is always valid.
- Do not use the eval built-in library function.

## Answer
### Method 1 - Recursion - :rabbit: 5ms (70%)

- Note: `idx++` should be assigned before `if` statement, otherwise, recursion will cause **TLE** because `idx` is not increased.

#### Approach 3 - Use local variable - :rocket: 3ms (95.46%)

```java
class Solution {
    int idx = 0, size = 0;
    public int calculate(String s) {
        size = s.length();

        return dfs(s);
    }

    private int dfs(String s) {
        int sum = 0, sign = 1;
        while (idx < size) {
            char c = s.charAt(idx++);
            if (c == '(') {
                int ret = dfs(s);
                sum += ret * sign;
            } else if (c == ')') {
                return sum;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (Character.isDigit(c)) {
                int val = c - '0';
                while (idx < size && Character.isDigit(s.charAt(idx))) {
                    val = val * 10 + s.charAt(idx++) - '0';
                }

                sum += val * sign;
            }
        }

        return sum;
    }
}

class Solution {
    int len = 0;
    public int calculate(String s) {
        len = s.length();
        return dfs(s, new int[]{0});
    }
    
    private int dfs(String s, int[] idx) {
        int res = 0, sign = 1;
        while (idx[0] < len) {
            char c = s.charAt(idx[0]++);
            if (Character.isDigit(c)) {
                int val = c - '0';
                
                while (idx[0] < len && Character.isDigit(s.charAt(idx[0]))) {
                    val = val * 10 + s.charAt(idx[0]++) - '0';
                }
                
                res += val * sign;
                
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1; 
            } else if (c == '(') {
                int ret = dfs(s, idx);
                res += ret * sign;
            } else if (c == ')') {
                return res;
            }
        }
        
        return res;
    }
}
```

#### Approach 2 - Using Local variables ONLY - :turtle: 200ms (5.03%)

```java
class Solution {
    
    public int calculate(String s) {
        return dfs(s);
    }
    
    private int dfs(String s) {
        int sum = 0, tmp = 0, idx = 0;
        int sign = 1, len = s.length();
        while (idx < len) {
            char c = s.charAt(idx++);
            if (c == '(') {
                idx--;
                int j = idx, cnt = 0;
                // find the corresponding ending ')'
                for (; idx < len; idx++) {
                    char ch = s.charAt(idx);
                    if (ch == '(') cnt++;
                    else if (ch == ')') cnt--;
                    if (cnt == 0) break;
                }
                tmp = dfs(s.substring(j + 1, idx));
                sum += tmp * sign;
            //} else if (c == ')') {
            //     return sum;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (Character.isDigit(c)) {
                tmp = c - '0';
                while (idx < len && Character.isDigit(s.charAt(idx))) {
                    tmp = tmp * 10 + s.charAt(idx++) - '0';
                }
                sum += tmp * sign;
            }
        }
        
        return sum;
    }
}
```

#### Approach 1 - Using global variables -

```java
class Solution {
    int idx = 0, len = 0;
    public int calculate(String s) {
        len = s.length();
        return dfs(s);
    }
    
    private int dfs(String s) {
        int sum = 0, tmp = 0;
        int sign = 1;
        while (idx < len) {
            char c = s.charAt(idx++);
            if (c == '(') {
                tmp = dfs(s);
                sum += tmp * sign;
            } else if (c == ')') {
                return sum;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (Character.isDigit(c)) {
                tmp = c - '0';
                while (idx < len && Character.isDigit(s.charAt(idx))) {
                    tmp = tmp * 10 + s.charAt(idx++) - '0';
                }
                sum += tmp * sign;
            }
        }
        
        return sum;
    }
}
```

```java
class Solution {
    // 2ms
    int idx = 0;
    public int calculate(String s) {
        int res = 0, sign = 1, len = s.length(), tmp = 0;
        
       while (idx < len) {
            char c = s.charAt(idx++);
            if (Character.isDigit(c)) {
                tmp = c - '0';
                while (idx < len && Character.isDigit(s.charAt(idx))) {
                    tmp = tmp * 10 + s.charAt(idx++) - '0';
                }
                
                res += tmp * sign;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                tmp = calculate(s);
                res += tmp * sign;
            } else if (c == ')') {
                return res;
            }
           
        }
        
        
        return res;
    }
}
```

```java
class Solution {
    // ========== Method 3: Recursion + No stack ==========
    // 2ms (99.77%)
    int index = 0;
    public int calculate(String s) {
        int res = 0, sign = 1, len = s.length(), tmp = 0;
        
        while (index < len) {
            char c = s.charAt(index++);
            if (Character.isDigit(c)) {
                tmp = tmp * 10 + c - '0';
            } else if (c == '+') {
                res += tmp * sign;
                sign = 1;
                tmp = 0;
            } else if (c == '-') {
                res += tmp * sign;
                sign = -1;
                tmp = 0;
            } else if (c == '(') {
                tmp = calculate(s);
            } else if (c == ')') {
                res += tmp * sign;
                return res;
            }
        }
        
        res += tmp * sign;
        return res;
    }

}
```

### Method 2 - One Stack - :rabbit: 13ms (54.05%)

```java
class Solution {
    // ========== Method 2: One stack ==========
    // 13ms (54.05%)
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList();
        int res = 0, sign = 1, len = s.length(), tmp = 0;
        
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                tmp = c - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    tmp = tmp * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                
                res += tmp * sign;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                sign = 1;
                res = 0;
            } else if (c == ')') {
                res = res * stack.pop() + stack.pop();
            }
        }
        
        return res;
    }

}
```

### Method 3 - Two Stacks - :turtle: 18ms (32.65%)

```java
class Solution {
    // ========== Method 1: Naive ==========
    // 18ms (32.65%)
    public int calculate(String s) {
        if (s == null) return 0;
        Deque<Integer> numStack = new LinkedList();
        Deque<Character> opStack = new LinkedList();
        
        int i = 0, len = s.length();
        while (i < len) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < len && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i++) - '0';
                }
                numStack.push(num);
                
                //System.out.println("num: " + num);
                if (!opStack.isEmpty() && opStack.peek() != '(') {
                    evaluate(numStack, opStack);
                }
                i--;
            } else if (c == '+' || c == '-' || c == '(') {
                //System.out.println("operator: " + c);
                opStack.push(c);
            } else if (c == ')') {
                while (opStack.peek() != '(') {
                    evaluate(numStack, opStack);    // "(1-(3-4))"
                }
                opStack.pop();
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    evaluate(numStack, opStack);    // "(7)-(0)+(4)"
                }
            }
            
            i++;
        }
        
        while (!opStack.isEmpty()) {
            evaluate(numStack, opStack);
        }
        return numStack.pop();
    }
    
    private void evaluate(Deque<Integer> numStack, Deque<Character> opStack) {
        int rightnum = numStack.pop();
        int leftnum = numStack.pop();
        char op = opStack.pop();
        if (op == '-') {
            rightnum = leftnum - rightnum;
        } else {
            rightnum = rightnum + leftnum;
        }
        numStack.push(rightnum);
    }
}
```
### Old Post

```java
/**
 * @author weltond
 * @project LeetCode
 * @date 1/24/2019
 *
 * Ideas:
 * 1. Compute the parenthesis level of each operator.
 * 2. Use a STACK to maintain the execution order of operators.
 *      a. If top operator < current, push
 *      b. If top operator > current, pop
 * 3. Use a separate stack to maintain operands.
 *      a. when we scan an operand, push
 *      b. when we pop an operator, pop two operands and push the result
 */
public class LC224BasicCalculator {
    public static void main(String[] args) {
        String s = " 2-1 + 2 ";
        System.out.println(calculate(s));
    }
    // Time = O(n), Space = O(n)
    public static int calculate(String expression) {
        Deque<OperatorLevel> operatorStack = new ArrayDeque<>();
        Deque<Integer> operandStack = new ArrayDeque<>();

        int parenthesisLevel = 0;
        ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
        //for (ExpressionToken token = tokenizer.nextToken(); tokenizer.hasMoreTokens(); token = tokenizer.nextToken()) {
        while (tokenizer.hasMoreTokens()) {
            ExpressionToken token = tokenizer.nextToken();
            System.out.println("get: " + token.toString());
            switch (token.getType()) {
                case NUMBER:
                    operandStack.addLast(token.intValue());
                    break;
                case PARENTHESIS:
                    char parenthesis = token.charValue();
                    if (parenthesis == '(') {
                        parenthesisLevel++;
                    } else {
                        parenthesisLevel--;
                    }
                    break;
                case OPERATOR:
                    while (!operatorStack.isEmpty() && operatorStack.getLast().getLevel() >= parenthesisLevel) {
                        evaluateTopOperator(operatorStack, operandStack);
                        System.out.println(parenthesisLevel);
                    }
                    operatorStack.addLast(
                            new OperatorLevel(token.charValue(), parenthesisLevel)
                    );
                    break;
            }
        }
        while (!operatorStack.isEmpty()) {
            evaluateTopOperator(operatorStack, operandStack);
        }
        return operandStack.getLast();
    }

    private static void evaluateTopOperator(Deque<OperatorLevel> operatorStack, Deque<Integer> operandStack) {
        int rightOperand = operandStack.removeLast();
        int leftOperand = operandStack.removeLast();
        System.out.println(leftOperand + ", " + rightOperand);
        char op = operatorStack.removeLast().getOperator();
        int result = 0;

        switch (op) {
            case '+':
                result = leftOperand + rightOperand;
                break;
            case '-':
                result = leftOperand - rightOperand;
                break;
            default:
                assert false : String.format("Operator '%c' not supported.", op);
        }
        operandStack.addLast(result);
    }
}

class ExpressionTokenizer {
    private int currentPosition;
    private int maxPosition;
    private char[] arr;
    ExpressionTokenizer(String expression) {
        currentPosition = 0;
        maxPosition = expression.length();
        this.arr = expression.toCharArray();
    }
    ExpressionToken nextToken(){
        StringBuilder token = new StringBuilder();
        boolean endOfToken = false;
        Type type = Type.UNKNOWN;
        while (!endOfToken && hasMoreTokens()) {
            while (hasMoreTokens() && arr[currentPosition] == ' ') {
                currentPosition++;
            }
            if (!hasMoreTokens()) break;
            switch (arr[currentPosition]) {
                case '+':
                case '-':
                case '*':
                case '/':
                    if (type != Type.NUMBER) {
                        type = Type.OPERATOR;
                        token.append(arr[currentPosition]);
                        currentPosition++;
                    }
                    endOfToken = true;
                    break;
                case '(':
                case ')':
                    if (type != Type.NUMBER) {
                        type = Type.PARENTHESIS;
                        token.append(arr[currentPosition]);
                        currentPosition++;
                    }
                    endOfToken = true;
                    break;
                case ' ':
                    endOfToken = true;
                    currentPosition++;
                    break;
                default:
                    if(Character.isDigit(arr[currentPosition]) || arr[currentPosition] == '.') {
                        token.append(arr[currentPosition]);
                        type = Type.NUMBER;
                    } else {
                        System.out.println("Syntax error at position: " + currentPosition);
                    }
                    currentPosition++;
                    break;
            }
        }
        return new ExpressionToken(type, token.toString());
    }

    boolean hasMoreTokens(){
        return currentPosition < maxPosition;
    }
}

enum Type {
    NUMBER, //e.g. 23
    OPERATOR,   // +,-
    PARENTHESIS, // (, )
    UNKNOWN
}

class ExpressionToken {
    private String token;
    private Type type;

    ExpressionToken(Type type, String token){
        this.type = type;
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Token is ");
        sb.append(token);
        sb.append(", and Type is ");
        sb.append(type);
        return sb.toString();
    }

    Type getType() {
        return this.type;
    }

    // Only when the type is NUMBER.
    int intValue(){
        if (type == Type.NUMBER) {
            return Integer.valueOf(token);
        }
        return 0;
    }

    // Only when the type is OPERATOR or PARENTHESIS
    char charValue(){
        if (type == Type.OPERATOR || type == Type.PARENTHESIS) {
            return token.toCharArray()[0];
        }
        return ' ';
    }
}

class OperatorLevel {
    private char op;
    private int level;

    public OperatorLevel(char op, int level) {
        this.op = op;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public char getOperator() {
        return op;
    }
}
```
