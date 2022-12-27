// https://leetcode.com/problems/trapping-rain-water/

/**
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.
Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
*/

class Solution {
    // ======== Method 3: DP ==========
    // 1ms (99.37%) O(n) O(n)
    public int trap(int[] height) {
       if (height == null || height.length == 0) return 0;
        int[] maxL = new int[height.length];
        int[] maxR = new int[height.length];
        
        maxL[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            maxL[i] = Math.max(height[i], maxL[i - 1]);
        }
        maxR[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxR[i] = Math.max(height[i], maxR[i + 1]);
        }
        
        int ans = 0;
        for (int i = 0; i < height.length - 1; i++) {
            ans += Math.min(maxR[i], maxL[i]) - height[i];
        }
        
        return ans;
    }
    
    // 1ms (99.56%)
    class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                left[i] = height[i];
                continue;
            }
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                right[i] = height[i];
                continue;
            }
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }

        int res = 0;

        for (int i = 0; i < n; i++) {
            res += Math.max(0, Math.min(left[i], right[i]) - height[i]);
        }

        return res;
    }

}
    
    // ======== Method 2 : Stack ========
    // 2ms (60.39%)
    class Solution {
    // Increase Monolonic Stack
    public int trap(int[] height) {
        Deque<Integer> stack = new LinkedList<>();  // index

        int idx = 0, res = 0;

        // stack stores info of left highest hight of current bar
        while (idx < height.length) {
            int val = height[idx];
            // if cur is greater than top, continue popping until 
            //  cur is less than top or stack is empty
            //  we calculate res exclude current bar
            while (!stack.isEmpty() && height[idx] > height[stack.peek()]) {
                int h = height[stack.pop()];    

                // no left bound
                if(stack.isEmpty()) {
                    break;
                }

                int width = idx - stack.peek() - 1;
                int minBound = Math.min(height[stack.peek()], height[idx]);
                res += width * (minBound- h);
            }

            stack.push(idx++);
        }

        return res;
    }
}
    
    // ======== Method 1: Brute Force ==========
    // 59ms(5.53%) O(n2) O(1)
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int maxL = 0, maxR = 0, j = 0;
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            j = i;
            maxL = 0;
            maxR = 0;
            while (j >= 0) {
                maxL = Math.max(maxL, height[j--]);
            }
            j = i;
            while (j < height.length) {
                maxR = Math.max(maxR, height[j++]);
            }
            
            int cur = Math.min(maxL, maxR) - height[i];
            ans += cur;
        }
        
        return ans;
    }
}
