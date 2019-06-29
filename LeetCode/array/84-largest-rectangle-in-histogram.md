## [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

Example:
```
Input: [2,1,5,6,2,3]
Output: 10
```

## Answer
### Method 3 - Stack
- Time: O(n)
```java
class Solution {
    // ========= Method 3: Stack ===========
    // 9ms (76.25%) O(n)
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new LinkedList();    // index of array
        int res = 0, i = 0;
        if (heights == null) return res;
        
        int[] h = new int[heights.length + 1];  //add a 0 at the end
        h = Arrays.copyOf(heights, heights.length + 1);
        
        while (i < h.length) {
            if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
                stack.push(i++);
            } else {
                int idx = stack.pop();
                res = Math.max(res, h[idx] * (stack.isEmpty() ? i: i - stack.peek() - 1));
            }
        }
        
        return res;
    }
}
```
### Method 2 - Pruning - :rocket: 3ms (89.36%)
- Time: O(n^2)
```java
class Solution {
    // ====== Method 2: Pruning. Find peak =======
    // 3ms (89.36%)
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            //pruning. Because higher height after current will contribute to the final result
            if (i + 1 < n  && heights[i] <= heights[i + 1]) continue;
            
            int minVal = heights[i];

            // j starts from the peek
            for (int j = i; j >= 0; j--) {
                minVal = Math.min(minVal, heights[j]);
                res = Math.max(res, (i - j + 1) * minVal);
            }
        }
        
        return res;
    }
}
```
### Method 1 - Brute Force - :turtle: 470ms (7.97%)
- Time: O(n ^ 2)
- Space: O(1)
```java
class Solution {
    // ========== Method 1: Brute Force ========
    // 470ms (7.97%)
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        
        int n = heights.length;
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            int minVal = heights[i];
            for (int j = i; j < n; j++) {
                minVal = Math.min(minVal, heights[j]);  // get min height from i-th to end
                res = Math.max(res, (j - i + 1) * minVal);
            }
        }
        
        return res;
    }
}
```
