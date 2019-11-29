## [836. Rectangle Overlap](https://leetcode.com/problems/rectangle-overlap/)

A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.

Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.

Given two (axis-aligned) rectangles, return whether they overlap.

Example 1:
```
Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
Output: true
```
Example 2:
```
Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
Output: false
```
Notes:

- Both rectangles rec1 and rec2 are lists of 4 integers.
- All coordinates in rectangles will be between -10^9 and 10^9.

## Answer
### Method 1 - Naive - :turtle: 484ms (8.04%)
```java
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x1 = rec1[0], x2 = rec1[2], y1 = rec1[1], y2 = rec1[3];
        int x1s = rec2[0], x2s = rec2[2], y1s = rec2[1], y2s = rec2[3];
        
        int xmax = Math.max(x1, x1s);
        int xmin = Math.min(x2, x2s);
        int ymax = Math.max(y1, y1s);
        int ymin = Math.min(y2, y2s);
        
        if (xmax < xmin && ymax < ymin)
            return true;
        
        return false;
    }
}
```
