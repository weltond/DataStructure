## [375. Guess Number Higher or Lower II](https://leetcode.com/problems/guess-number-higher-or-lower-ii/)

We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay **$x**. You win the game when you guess the number I picked.

Example:
```
n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
```
Given a particular n ≥ 1, **find out how much money you need to have to guarantee a win.**

## Answer
**Minimax**

Our goal is try all possible guesses at every stage and choose the minimum. 

But remember every time we guess, we are diving the search space, `[i..j]`, into two parts, the target could be anywhere (obvioulsy except our current guess). 

So we solve each part with the assumption that target could be anywhere and see the cost at each possible `k` in `[i..j]`. We take the **max cost out of two parts because target could be anywhere and we need to cover the cost for that**. (i.e. If we take minimum say that is from left part, what if the target is in right part).

Example:
1. If there is only 1 number, our cost would be `0` without any doubt.
2. If two numbers: `[1,2]`:
  - choose 1: cost `1`. And then we are sure we can get the correct result. `Total cost = 1`.
  - choose 2: cost `2`.
So `Total cost = min(choose 1, choose 2) = 1`.
3. If three numbers: `[1,2,3]`
  - If choose `1`: cost `1`. And then we have `2` and `3` left. Based on last discussion, `total cost = 1 + max([], [2, 3]) = 3`.
  - If choose `2`, it is guaranteed to win. `total cost = 2`.
  - If choose `3`, cost `3`. And then we have `1` and `2` left. Based on last discussion, `total cost = 3 + max([1, 2], []) = 4`.
So `total cost = min(choose 1, choose 2, choose 3) = 2`.

### Method 2 - DP

### Method 1 - DFS + Memo - :turtle: 8ms (25.67%)
```java
class Solution {
    // =========== Method 1: DFS + Memo ==========
    // 8ms (25.67%)
    public int getMoneyAmount(int n) {
        Integer[][] memo = new Integer[n + 1][n + 1];
        
        return dfs(1, n, memo);
    }
    
    private int dfs(int i, int j, Integer[][] memo) {
        if (i >= j) return 0;   // if i == j, we return 0 because we don't have to pay for the only guess.
        if (memo[i][j] != null) return memo[i][j];
        
        int res = Integer.MAX_VALUE;
        // divide [i...j] into two part, and get min val from the max([i..k-1], [k+1..j])
        for (int k = i; k <= j; k++) {
            int localMax = Math.max(dfs(i, k - 1, memo), dfs(k + 1, j, memo)) + k;
            res = Math.min(res, localMax);
        }
        
        memo[i][j] = res;
        
        return res;
    }
}
```
