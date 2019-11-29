## [223. Rectangle Area](https://leetcode.com/problems/rectangle-area/)

Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area

Example:
```
Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
Output: 45
```

Note:

- Assume that the total area is never beyond the maximum possible value of int.

## Answer
### Approach 2 - :rocket: 2ms (100%)
```java
class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max(A, E), right = Math.max(Math.min(C, G), left);
        int bot = Math.max(B, F), top = Math.max(Math.min(D, H), bot);
        
        return (C - A) * (D - B) - (right - left) * (top - bot) + (G - E) * (H - F);
    }
}
```
### Approach 1 - :rocket: 2ms (100%)
```java
class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
        int area2 = (G - E) * (H - F);
        
        int x1 = Math.max(A, E);
        int y1 = Math.max(B, F);
        int x2 = Math.min(C, G);
        int y2 = Math.min(D, H);
        boolean isCross = x2 > x1 && y2 > y1 ;
        int cross = isCross ? (x2 - x1) * (y2 - y1) : 0;
        
        return area1 + area2 - cross;
    }
}
```
