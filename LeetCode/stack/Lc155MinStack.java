//https://leetcode.com/problems/min-stack/

// 5ms (66.23%)
class MinStack {

    Node head;
    /** initialize your data structure here. */
    public MinStack() {
        head = null;
    }
    
    public void push(int x) {
        if (head == null) {
            head = new Node(x, x, null);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }
    
    public void pop() {
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
}

class Node {
    int val, min;
    Node next;
    public Node(int val, int min, Node next) {
        this.val = val;
        this.min = min;
        this.next = next;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
// 7ms (23.48%)
class MinStack {
    Deque<Integer> stack;
    int min;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        if (stack.peek() == min) {
            stack.pop();
            min = stack.peek();
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
class MinStack {
    List<Integer> arr;
    int globalMin;
    /** initialize your data structure here. */
    public MinStack() {
        arr = new ArrayList();
        globalMin = Integer.MAX_VALUE;
    }
    
    // ======= Elegant Solution =====
    /** Main Idea:
    Push oldMin into stack with newMin together when need to update min
    Pop twice if the top is min value and set the globalMin to the second pop value
    */
    
    public void push(int x) {
        if (globalMin >= x) {   // IMPORTANT: has to be also equal. Otherwise it will influence pop()
            arr.add(globalMin); // push old_globalMin into stack first
            globalMin = x;
        }
        
        arr.add(x);
    }
    
    public void pop() {
        if (isEmpty()) return;
        
        int tmp = top();
        arr.remove(arr.size() - 1);
        // if top() is globalMin, then it should remove the next again because 
        // If needed, every time we push old globalMin before we push new globalMin into stack
        if (tmp == globalMin) {
            globalMin = top();
            arr.remove(arr.size() - 1);
        }
    }
    
    public int top() {
        return isEmpty() ? Integer.MAX_VALUE : arr.get(arr.size() - 1);
    }
    
    public int getMin() {
        return globalMin;
    }
    
    private boolean isEmpty() {
        return arr.size() == 0;
    }
    
    // ======= Not Solution ======
    // Following will cause overflow if x is greater than Max_VALUE / 2.
    /** Main Idea
    1. Push:
        a) if greater than min, just push
        b) if smaller than min, push 2 * x - min. update min
    2. Pop:
        a) if greater than min, just pop
        b) if smaller than min, pop 2 * min - top(). update min
    */

    
//     public void push(int x) {
//         // if empty, first value is global min.
//         if (isEmpty()) {
//             globalMin = x;
//             arr.add(x);
//             return;
//         }
        
//         // if not empty:
//         // a) x >= globalMin
//         if ((long)x >= globalMin) {
//             arr.add(x);
//         } 
//         // b) x < globalMin, push 2 * x - old_globalMin into stack
//         else {
//             arr.add(2 * x - globalMin);
//             globalMin = x;
//         }
//     }
    
//     public void pop() {
//         if (arr.size() <= 0) return;
        
//         int myTop = top();
//         // a) if top greater than globalMin, just pop
        
//         // b) top smaller than globalMin, update globalMin to 2 * globalMin - myTop
//         //    -> myTop: the one push to stack after calc: 2 * x - old_globalMin
//         //    -> globalMin: x
//         if (myTop < globalMin) {
//             globalMin = 2 * globalMin - myTop;
//         }
        
//         arr.remove(arr.size() - 1);
        
//     }
    
//     public int top() {
//         int tmp = arr.get(arr.size() - 1);
//         if (tmp < globalMin) return globalMin;
//         else return tmp;
//     }
    
//     public int getMin() {
//         return globalMin;
//     }
    
//     private boolean isEmpty() {
//         return arr.size() == 0;
//     }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
