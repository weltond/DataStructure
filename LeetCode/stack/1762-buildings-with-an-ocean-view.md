## [1762. Buildings With an Ocean View](https://leetcode.com/problems/buildings-with-an-ocean-view/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.

 

Example 1:

```
Input: heights = [4,2,3,1]
Output: [0,2,3]
Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
```

Example 2:

```
Input: heights = [4,3,2,1]
Output: [0,1,2,3]
Explanation: All the buildings have an ocean view.
```

Example 3:

```
Input: heights = [1,3,2,4]
Output: [3]
Explanation: Only building 3 has an ocean view.
``` 

**Constraints**:

- 1 <= heights.length <= 105
- 1 <= heights[i] <= 109

## Answers

### Method 2 - Monotonic Stack (optimize) - 5ms (75.45%) 🐰

Time: O(N)

Space: O(1)

```java
class Solution {
    public int[] findBuildings(int[] h) {
        int maxHeight = -1;
        
        List<Integer> list = new ArrayList();
        
        for (int i = h.length - 1; i >= 0; i--) {
            int val = h[i];
            
            // If there is no building higher (or equal) than the current one to its right
            // push it in the list
            if (maxHeight < val) {
                list.add(i);
                
                // update max building till now
                maxHeight = val;
            }
        }
        
        int[] res = new int[list.size()];
        
        int idx = res.length - 1;
        for (int val : list) {
            res[idx--] = val;
        }
        
        return res;
    }
}
```

### Method 1 - Monotonic Stack - 45ms (17.22%) 🐢

Time: O(N)

Space: O(N)

```java
class Solution {
    public int[] findBuildings(int[] h) {
        Deque<Integer> stack = new LinkedList();
        List<Integer> list = new ArrayList();
        
        for (int i = h.length - 1; i >= 0; i--) {
            int val = h[i];
            
            // pop if val is greater than top
            while (!stack.isEmpty() && val > stack.peek()) {
                stack.pop();
            }
            
            if (stack.isEmpty()) {
                list.add(i);
            }    
            
            stack.push(h[i]);
        }
        
        int[] res = new int[list.size()];
        
        int idx = res.length - 1;
        for (int val : list) {
            res[idx--] = val;
        }
        
        return res;
    }
}
```
