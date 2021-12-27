// https://leetcode.com/problems/daily-temperatures/

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[temperatures.length];

        for (int i = temperatures.length - 1; i >= 0; i--) {
            int val = temperatures[i];

            // if val is greater than stack.peek()
            // keep popping until stack.peek() > val, this is the date that have higher temp than i.
            while (!stack.isEmpty() && temperatures[stack.peek()] <= val) {
                stack.pop();
            }

            // stack is empty means no future days have higher temp
            // otherwise, stack.peek() is the first date that has higher temp than current i.
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;

            // push to stack because current i might be the first future date with higher tmp
            stack.push(i);
        }

        return res;
    }
}

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
