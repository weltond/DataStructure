// https://leetcode.com/problems/daily-temperatures/

class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Deque<Integer> stack = new ArrayDeque();    // Stack stores index, NOT value
        
        // 1. If stack is empty, res = 0. Then push current idx into stack
        // 2. If current T[index] >= T[stack.peek()], pop until meet the greater value or empty. 
        //      res = 0 if empty or res = stack.peek() - currnet index. Then push current idx to stack
        // 3. If current T[index] < T[stack.peek()], res = stack.peek() - index. Then push current idx to stack
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        
        return res;
    }
}
