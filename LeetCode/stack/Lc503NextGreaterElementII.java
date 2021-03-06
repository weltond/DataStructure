// https://leetcode.com/problems/next-greater-element-ii/

class Solution {
    // ============== Method 1: Monotonic Stack =================
    // 11ms (96.96%)
    // Approach 2
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];

        Deque<Integer> stack = new LinkedList();
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % nums.length]);
        }
        return res;
    }
    
    // Approach 1
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        int[] tmp = new int[nums.length * 2];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = nums[i % nums.length];
        }
        Deque<Integer> stack = new LinkedList();
        for (int i = tmp.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= tmp[i]) {
                stack.pop();
            }
            if (i < nums.length)
                res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(tmp[i]);
        }
        return res;
    }
}
