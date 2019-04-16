// https://leetcode.com/problems/implement-stack-using-queues/


class MyStack {
    // 43ms (97.98%)
    Queue<Integer> q1;
    Queue<Integer> q2;   // always push to empty q2
    //int cnt;
    /** Initialize your data structure here. */
    public MyStack() {
        //cnt = 0;
        q1 = new LinkedList();
        q2 = new LinkedList();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        // always push to empty q2
        q2.offer(x);
        
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        
        // Swap q1 and q2 to make q2 always empty
        Queue tmp = q2;
        q2 = q1;
        q1= tmp;
        
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q1.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return q1.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
