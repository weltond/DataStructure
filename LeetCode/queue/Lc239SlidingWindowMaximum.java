// https://leetcode.com/problems/sliding-window-maximum/

class Solution {
    // =============== Method 2: Binary Search ==============
    // TO DO
    
    
    // =============== Method 1: Monotonic Queue ==============
    // 8ms (89.03%)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        
        MyQueue q = new MyQueue();
        
        int[] ans = new int[nums.length - k + 1];
        
        for (int i = 0; i < nums.length; i++) {
            q.push(nums[i]);
            
            if (i - k + 1 >= 0) {
                ans[i - k + 1] = q.getMax();
                
                // pop if cur max is about to move out of the window
                if (nums[i - k + 1] == q.getMax()) {
                    q.pop();
                }
            }
        }
        
        return ans;
    }
    
    class MonotonicQueue {
        Deque<Integer> q;
        MyQueue() {
            q = new LinkedList();
        }
        // push an element on the queue will pop all elements smaller than it
        public void push(int val) {
            while (!q.isEmpty() && val > q.getLast()) {
                q.removeLast();
            }
            
            q.addLast(val);
        }
        
        // pop the front. Front Must be the max val
        public void pop() {
            q.removeFirst();
        }
        
        public int getMax() {
            return q.getFirst();
        }
    }
}

class Solution {
    // 12ms 
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        int len = nums.length;
        int[] res = new int[len - k + 1];
        
        Deque<Integer> d = new LinkedList();
        
        for (int i = 0; i < len; i++) {
            if (d.isEmpty() || nums[d.peekLast()] >= nums[i]) {
                d.offerLast(i);
            } else if (nums[d.peekLast()] < nums[i]) {
                while (!d.isEmpty() && nums[d.peekLast()] < nums[i]) {
                    d.pollLast();
                }
                d.offerLast(i);
            }
            
            if (i >= k - 1) {
                res[i - k + 1] = nums[d.peekFirst()];
            }
            
            if (i - d.peekFirst() == k - 1) d.pollFirst();
            
            
        }
        
        return res;
    }
}
