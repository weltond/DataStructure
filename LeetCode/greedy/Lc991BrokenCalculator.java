// https://leetcode.com/problems/broken-calculator/

/**
Input: X = 3, Y = 10
Output: 3
Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.

Input: X = 1024, Y = 1
Output: 1023
Explanation: Use decrement operations 1023 times.
*/

class Solution {
    // ======== Method 2: Work Backwards ========
    // 0ms
    /**
    Instead of multiplying by 2 or subtracting 1 from X, we could divide by 2 (when Y is even) or add 1 to Y.
    The motivation for this is that it turns out we always greedily divide by 2.
    While Y is larger than X, add 1 if it is odd, else divide by 2. After, we need to do X - Y additions to reach X.
    */
    public int brokenCalc(int x, int y) {
        int step = 0;
        
        while (y > x) {
            if (y % 2 == 0) {
                y = y / 2;
            } else {
                y += 1;
            }
            step++;
        }
        
        return step + x - y;
    }
    
    // ======== Method 1: BFS ===========
    // TLE
//     public int brokenCalc(int x, int y) {
//         Queue<Integer> q = new LinkedList();
        
//         q.offer(x);
        
//         int step = 0;
//         while (!q.isEmpty()) {
//             int size = q.size();
//             step++;
            
//             for (int i = 0; i < size; i++) {
//                 int cur = q.poll();
//                 int n1 = cur - 1;
//                 int n2 = cur * 2;
//                 if (n1 == y || n2 == y) return step;
//                 else {
//                     q.offer(n1);
//                     q.offer(n2);
//                 }
//             }
//         }
        
//         return -1;
//     }
}
