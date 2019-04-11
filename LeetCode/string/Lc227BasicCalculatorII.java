// https://leetcode.com/problems/basic-calculator-ii/

/**
Input: "3+2*2"
Output: 7
*/
// ====================== Method 2 ========================
// 7ms (97.22%)
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

// ====================== Method 1 =====================
// 78ms (5.80%)
class Solution {
    public int calculate(String s) {
        List<String> res = convert(s);
        // for (String str: res) {
        //     System.out.print(str + ", ");
        // }
        // System.out.println(res.size());
        return calc(res);
    }
    
    public int calc(List<String> l) {
        //1. if digit, push
        //2. if operator, pop two elements and then do calc, push
        Deque<String> stack = new LinkedList();
        int i = 0;
        while (i < l.size()) {
            if (l.get(i).equals("+") || l.get(i).equals("-") || l.get(i).equals("*") || l.get(i).equals("/")) {
                //System.out.println("get " + l.get(i) + "at " + i + ", op");
                if (stack.size() <= 1) {
                    System.out.print(stack.peek());
                    return Integer.MIN_VALUE;
                } 
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                //System.out.println("a: " + a + l.get(i) + " b: " + b);
                if (l.get(i).equals("+")) stack.push(String.valueOf(a + b));
                if (l.get(i).equals("-")) stack.push(String.valueOf(b - a));
                if (l.get(i).equals("*")) stack.push(String.valueOf(a * b));
                if (l.get(i).equals("/")) stack.push(String.valueOf(b / a));
                
            } else {
                //System.out.println("get " + l.get(i) + "at " + i);
                stack.push(l.get(i));
            }
            
            i++;
        }
        
        return Integer.valueOf(stack.pop());
    }
    
    public List<String> convert(String s) {
        s = s.trim();
        char[] a = s.toCharArray();
        
        List<String> l = new ArrayList();
        Deque<Character> stack = new LinkedList();
        
        int i = 0;
        while (i < a.length) {
            StringBuilder sb = new StringBuilder();
            boolean digit = false;
            // 1. if digit, save to stringbuilder
            while (i < a.length && a[i] - '0' <= '9' && a[i] - '0' >= 0){
                sb.append(a[i]);
                i++;
                digit = true;
            }
            
            if (digit) {
                //System.out.print("add digit: " + sb.toString() + " at " + i + "..");
                l.add(sb.toString());
                //i++;
                continue;
            }
            //2. push ( to stack (if feasible)
            //3. pop when see ) and save operators to list until see (
            
            //4. If stack not empty, pop high priority operators and then push cur
            //5. If stack is empty, push operator to stack
            if (stack.isEmpty() && a[i] != ' ') {
                stack.push(a[i]);
            } else {
                if (a[i] == '*' || a[i] == '/') {
                    while (stack.peek() != null && (stack.peek() == '*' || stack.peek() == '/')) {
                        sb.append(stack.pop());
                        l.add(sb.toString());
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    stack.push(a[i]);
                } else if (a[i] == '+' || a[i] == '-'){
                    while (stack.peek() != null) {
                        sb.append(stack.pop());
                        l.add(sb.toString());
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    stack.push(a[i]);
                }
            }
            
            i++;
        }
        
        //6. After iteration. pop all.
        while (!stack.isEmpty()) {
            char ss = stack.pop();
            l.add(String.valueOf(ss));
        }
        
        return l;
    }
}
