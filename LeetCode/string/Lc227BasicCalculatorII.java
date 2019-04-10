// 

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
