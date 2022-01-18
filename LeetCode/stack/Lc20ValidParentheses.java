// https://leetcode.com/problems/valid-parentheses/
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList();
        
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || c != stack.pop()) return false;
        }
        
        return stack.isEmpty();
    }
}

class Solution {
    // ====== Use fake Stack ======
    // 0ms (100%)
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        
        char[] stack = new char[s.length()];
        int head = 0;
        
        for (char c : s.toCharArray()) {
            switch(c) {
                case '{' :
                case '[' :
                case '(' :
                    stack[head++] = c;
                    break;
                case '}':
                    if (head == 0 || stack[--head] != '{') return false;
                    break;
                case ']' :
                    if (head == 0 || stack[--head] != '[') return false; 
                    break;
                case ')' :
                    if (head == 0 || stack[--head] != '(') return false; 
                    break;
            }
        }
        return head == 0;
    }
    
    // ====== Use real Stack ======
    // 1ms (99.89%), 35.6MB (36.87%)
//     public boolean isValid(String s) {
//         if (s == null || s.length() == 0) return true;
        
//         Map<Character, Character> map = new HashMap();
//         map.put(')', '(');
//         map.put(']', '[');
//         map.put('}', '{');
        
//         Deque<Character> stack = new ArrayDeque();
        
//         for (int i = 0, len = s.length(); i < len; i++) {
//             char c = s.charAt(i);
            
//             if (c == '(' || c == '[' || c == '{') {
//                 stack.push(c);
//             } else {
//                 if (stack.isEmpty() || !stack.pop().equals(map.get(c))) return false;
//             }
//         }
        
//         return stack.isEmpty();
//     }
}
