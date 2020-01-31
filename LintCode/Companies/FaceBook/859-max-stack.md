## [859. Max Stack](https://www.lintcode.com/problem/max-stack/description?_from=ladder&&fromId=130)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Design a max stack that supports push, pop, top, peekMax and popMax.

- push(x) -- Push element x onto stack.
- pop() -- Remove the element on top of the stack and return it.
- top() -- Get the element on the top.
- peekMax() -- Retrieve the maximum element in the stack.
- popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.

Example

```
Input:
push(5)
push(1)
push(5)
top()
popMax()
top()
peekMax()
pop()
top()
Output:
5
5
1
5
1
5
```

Notice

- -1e7 <= x <= 1e7
- Number of operations won't exceed 10000.
- The last four operations won't be called when stack is empty.

## Answer
### Method 1 - Stack - :turtle: 202ms (19.25%)

```java
class MaxStack {
    Map<Integer, Integer> map = new HashMap();  // <val, freq>
    Deque<Integer> stack = new LinkedList();
    int maxVal = Integer.MIN_VALUE;
    public MaxStack() {
        // do intialization if necessary
    }

    /*
     * @param number: An integer
     * @return: nothing
     */    
    public void push(int x) {
        // write your code here
        stack.push(x);
        if (x > maxVal) {
            maxVal = x;
        }
        
        map.put(x, map.getOrDefault(x, 0) + 1);
    }

    public int pop() {
        // write your code here
        int val = stack.pop();
        int freq = map.get(val);
        map.put(val, freq - 1);
        if (freq == 1) {
            map.remove(val);
            if (val == maxVal) {
                maxVal = Integer.MIN_VALUE;
                for (int key : map.keySet()) {
                    maxVal = Math.max(maxVal, key);
                }
            }
        }
        
        return val;
    }

    /*
     * @return: An integer
     */    
    public int top() {
        // write your code here
        return stack.peek();
    }

    /*
     * @return: An integer
     */    
    public int peekMax() {
        // write your code here
        return maxVal;
    }

    /*
     * @return: An integer
     */    
    public int popMax() {
        // write your code here
        int freq = map.get(maxVal);
        
        Deque<Integer> tmp = new LinkedList();
        
        while (stack.peek() != maxVal) {
            tmp.push(stack.pop());
        }
        
        stack.pop();
        
        while (!tmp.isEmpty()) {
            stack.push(tmp.pop());
        }
        
        map.put(maxVal, freq - 1);
        if (freq != 1) return maxVal;
        
        map.remove(maxVal);
        maxVal = Integer.MIN_VALUE;
        for (int key : map.keySet()) {
            maxVal = Math.max(maxVal, key);
        }
        
        return maxVal;
    }
}
```
