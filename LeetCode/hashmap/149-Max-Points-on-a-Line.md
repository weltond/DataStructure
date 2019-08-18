## [149. Max Points on a Line](https://leetcode.com/problems/max-points-on-a-line/)

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:
```
Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
```
Example 2:
```
Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
```

## Answer
### Method 1 - Brute Force - :turtle: 18ms (32.20%)
```java
class Solution {
    // 18ms (32.20%)
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) return 0;
 
        int res = 0;
        
        for (int i = 0; i < points.length; i++) {
            int dup = 1;
            Map<Map<Integer, Integer>, Integer> map = new HashMap(); //<rate(gcd), freq>
            
            for (int j = i + 1; j < points.length; j++) {
                // same point
                if (points[i][0] == points[j][0] && points[j][1] == points[i][1]) {
                    dup++;
                    continue;
                }
                
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                int d = gcd(dx, dy);

                // int[] t = new int[2];
                // t[0] = dx / d;
                // t[1] = dy / d;
                Map<Integer, Integer> t = new HashMap<>();
                t.put(dx / d, dy / d);

                map.put(t, map.getOrDefault(t, 0) + 1);
            }
            res = Math.max(res, dup);   // in case only one element
            for (Map.Entry<Map<Integer, Integer>, Integer> e : map.entrySet()) {
                res = Math.max(res, e.getValue() + dup);
            }
        }
        
        return res;
    }
    
    // to prevent using Double
    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}
```
