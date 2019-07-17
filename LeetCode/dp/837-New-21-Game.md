## [837. New 21 Game](https://leetcode.com/problems/new-21-game/)

Alice plays the following game, loosely based on the card game "21".

Alice starts with `0` points, and draws numbers while she has less than `K` points.  During each draw, she gains an integer number of points randomly from the range `[1, W]`, where `W` is an integer.  Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets `K` or more points.  What is the probability that she has N or less points?

Example 1:
```
Input: N = 10, K = 1, W = 10
Output: 1.00000
Explanation:  Alice gets a single card, then stops.
```
Example 2:
```
Input: N = 6, K = 1, W = 10
Output: 0.60000
Explanation:  Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.
```
Example 3:
```
Input: N = 21, K = 17, W = 10
Output: 0.73278
```
Note:

- `0 <= K <= N <= 10000`
- `1 <= W <= 10000`
- Answers will be accepted as correct if they are within `10^-5` of the correct answer.
- The judging time limit has been reduced for this question.

## Answer
### Method 2 - DP

### Method 1 - DFS - TLE(96 / 146 test cases passed.)
```java
class Solution {
    // ========= Method 1: DFS + Memo ============
    // TLE (96 / 146 test cases passed.)
    public double new21Game(int N, int K, int W) {
        Double[][] memo = new Double[K + 1][K + W - 1];   //<level, max value>
        return dfs(N, K, W, 0, 0, memo);
    }
    
    private double dfs(int n, int k, int w, int prev, int level, Double[][] memo) {
        if (prev >= k) return 1.0;
        if (memo[level][prev] != null) return memo[level][prev];
        double sum = 0;
        for (int i = 1; i <= w; i++) {
            if (i + prev <= n) {
                sum += dfs(n, k, w, prev + i, level + 1, memo) / w;
            }
        }
        
        memo[level][prev] = sum;
        
        return sum;
    }
}
```
