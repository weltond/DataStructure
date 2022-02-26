## [494. Target Sum](https://leetcode.com/problems/target-sum/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols `+` and `-`. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:

```
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
```

Note:
- The length of the given array is positive and will not exceed `20`.
- The sum of elements in the given array will not exceed `1000`.
- Your output answer is guaranteed to be fitted in a 32-bit integer.

## Answer
### Method 1 - DFS + Memo - :rabbit: 23ms (68.63%)
Time: O(t*n), t is the sum of `nums`.

Space: O(t*n)

We cannot use `memo[lvl][target + total]` because `target` might be a negative val that is larger than `total`. 

```java
class Solution {
    int[][] memo;
    int total;
    
    public int findTargetSumWays(int[] nums, int target) {
        // Sum of the nums.
        // It is used to take care of negative value.
        total = Arrays.stream(nums).sum();  
        
        memo = new int[nums.length][total * 2  + 1];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        int res = dfs(nums, target, 0, 0);
        
        return res;
    }
    
    private int dfs(int[] nums, int target, int sum, int lvl) {
        if (lvl == nums.length) {
            if (target == sum) return 1;
            return 0;
        }

        if (memo[lvl][sum + total] != -1) return memo[lvl][sum + total];
        
        int plus = dfs(nums, target, sum + nums[lvl], lvl + 1);
        
        int minus = dfs(nums, target, sum - nums[lvl], lvl + 1);
        
        
        memo[lvl][sum + total] = plus + minus;
        
        return plus + minus;
    }
}
```

```java
public class Solution {
    /**
     * @param nums: the given array
     * @param s: the given target
     * @return: the number of ways to assign symbols to make sum of integers equal to target S
     */
    Integer[][] memo;
    public int findTargetSumWays(int[] nums, int s) {
        // Write your code here
        memo = new Integer[20001][nums.length];
        
        return dfs(nums, s, 0, 0);
    }
    
    private int dfs(int[] nums, int s, int sum, int lvl) {
        if (lvl == nums.length) {
            return sum == s ? 1 : 0;
        }
        
        if (memo[sum + 1000][lvl] != null) return memo[sum + 1000][lvl];
        
        int res = dfs(nums, s, sum + nums[lvl], lvl + 1) + dfs(nums, s, sum - nums[lvl], lvl + 1);
        
        memo[sum + 1000][lvl] = res;
        
        return memo[sum + 1000][lvl];
    }
}
```

#### Approach 1 - DFS - 496ms (34.84%)
Time: O(2^n) because it is a binary tree.

Space: O(n), hight of the tree.

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int res = dfs(nums, target, 0);
        
        return res;
    }
    
    private int dfs(int[] nums, int target, int lvl) {
        if (lvl == nums.length) {
            if (target == 0) return 1;
            return 0;
        }
        
        int plus = dfs(nums, target - nums[lvl], lvl + 1);
        
        int minus = dfs(nums, target + nums[lvl], lvl + 1);
        
        return plus + minus;
    }
}
```
