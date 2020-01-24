## [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/)

![](https://github.com/weltond/DataStructure/blob/master/easy.PNG)

Implement the following operations of a queue using stacks.

- push(x) -- Push element x to the back of queue.
- pop() -- Removes the element from in front of queue.
- peek() -- Get the front element.
- empty() -- Return whether the queue is empty.

Example:

```
MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);  
queue.peek();  // returns 1
queue.pop();   // returns 1
queue.empty(); // returns false
```

Notes:

- You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
- Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
- You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).

## Answer
### Method 1 - Two Stacks - :rocket: 1ms (100%)

- `s1` stack is used to store recently `pushed` values.
- `s2` stack is used to perform as a queue.
  - if `s2` is empty, push all element in `s1` into `s2`.
  - if not empty, just simply return `top`.

```java
class MyQueue {

    Deque<Integer> s1, s2;
    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new LinkedList();
        s2 = new LinkedList();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // if (s2.isEmpty()) {
        //     while (!s1.isEmpty()) {
        //         s2.push(s1.pop());
        //     }
        // }
        peek();
        
        return s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        
        return s2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```
