// https://leetcode.com/problems/baseball-game/

class Solution {
    
    // =========== Approach 2: Array ==============
    // 1ms (100%)
    public int calPoints(String[] ops) {
        if (ops == null) return 0;
        int sum = 0;
        int[] valid = new int[1000];
        int index = 0;
        
        for (String c : ops) {
            if (c.equals("C")) {
                sum -= valid[--index];
            } else if (c.equals("D")) {
                valid[index] = valid[index - 1] * 2;
                sum += valid[index++];
            } else if (c.equals("+")) {
                valid[index] = valid[index - 1] + valid[index - 2];
                sum += valid[index++];
            } else {
                int score = Integer.parseInt(c);
                valid[index++] = score;
                sum += score;
            }
        }
        
        return sum;
    }
    
    // =========== Approach 1: Stack ==============
    // 2ms (99.47%). O(n)
    public int calPoints(String[] ops) {
        if (ops == null) return 0;
        int sum = 0;
        Deque<Integer> stack = new LinkedList();
        for (int i = 0; i < ops.length; i++) {
            String s = ops[i];
            if (s.equals("C")) {
                sum -= stack.pop();
            } else if (s.equals("D")) {
                int cur = stack.peek() * 2;
                sum += cur;
                stack.push(cur);
            } else if (s.equals("+")) {
                int tmp = stack.pop();
                int cur = tmp + stack.peek();
                sum += cur;
                stack.push(tmp);
                stack.push(cur);
            } else {
                int cur = Integer.parseInt(s);
                stack.push(cur);
                sum += cur;
            }
        }
        
        return sum;
    }
}
