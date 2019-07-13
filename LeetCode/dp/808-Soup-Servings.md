## [808. Soup Servings](https://leetcode.com/problems/soup-servings/)

There are two types of soup: type A and type B. Initially we have N ml of each type of soup. There are four kinds of operations:

1. Serve 100 ml of soup A and 0 ml of soup B
2. Serve 75 ml of soup A and 25 ml of soup B
3. Serve 50 ml of soup A and 50 ml of soup B
4. Serve 25 ml of soup A and 75 ml of soup B

When we serve some soup, we give it to someone and we no longer have it.  Each turn, we will choose from the four operations with equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will serve as much as we can.  We stop once we no longer have some quantity of both types of soup.

Note that we do not have the operation where all 100 ml's of soup B are used first.  

Return the probability that soup A will be empty first, plus half the probability that A and B become empty at the same time.

Example:
```
Input: N = 50
Output: 0.625
Explanation: 
If we choose the first two operations, A will become empty first. For the third operation, A and B will become empty at the same time. For the fourth operation, B will become empty first. So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.
```
Notes:

- `0 <= N <= 10^9. `
- Answers within 10^-6 of the true value will be accepted as correct.

## Answer
### Method 1 - DFS + Memo - :rabbit: 2ms (79.60%)
- Time, Space: O(200 * 200)
```java
class Solution {
    // ============= Method 1: DFS + Memo ===========
    // 2ms (79.60%)
    double memo[][] = new double[200][200];  // if N >= 4800, always return 1.0
    public double soupServings(int N) {
        // 1 serving = 25 ml. So if N <= 25, we get 1 serving. 26 <= N <= 50, we get 2 servings.
        // 51 <= N <= 75, we get 3 servings. ==> (N + 24) / 25 servings
        int servings = (N + 24) / 25;
        return N >= 4800 ? 1.0 : dfs(servings, servings);
    }
    
    private double dfs(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1;
        if (b <= 0) return 0;
        
        if (memo[a][b] != 0) return memo[a][b];
        
        double opt1 = dfs(a - 4, b);
        double opt2 = dfs(a - 3, b - 1);
        double opt3 = dfs(a - 2, b - 2);
        double opt4 = dfs(a - 1, b - 3);
        
        memo[a][b] = 0.25 * (opt1 + opt2 + opt3 + opt4);
        
        return memo[a][b];
    }
}
```
