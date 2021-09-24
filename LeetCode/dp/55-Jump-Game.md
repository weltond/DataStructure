## [55. Jump Game](https://leetcode.com/problems/jump-game/)

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:
```
Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
```
Example 2:
```
Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
```

## Idea
### Difference Between DP and Greedy
#### Dynamic Programming
Looking from the end and at each point ahead checking the best possible way to reach the end

```c++
    bool canJump(vector<int>& nums) {
        int n = nums.size();
        vector<bool> jump(n,false);
        jump[n-1]=true;
        
        for(int i=n-2;i>=0;i--)
        {
            for(int j=0;j<=nums[i] && i+j<n;j++)
            {
                if(jump[i+j]==true) 
                {
                    jump[i]=true; 
                    break;
                }
            }
        }
        
        return jump[0];
    }
```

#### Greedy
[Reference](https://leetcode.com/problems/jump-game/discuss/20923/leetcode.com/problems/jump-game/discuss/20900/Simplest-O(N)-solution-with-constant-space/20948)

Looking from the start and selecting the locally optimum in the hope of reaching global optimum

```c++
    bool canJump(vector<int>& nums) {
      int n = nums.size(), farest = 0;
      for(int i = 0;i < n; i++)
      {
        if(farest < i) return false;
        farest = max(i + nums[i], farest);
      }
      
      return true;
    }
```

Example

![img](https://assets.leetcode.com/users/sohammehta/image_1539664091.png)

If we have a Greedy Approach here then we will take the path 1+99+1 as we select local optimum from the beggining

But if we take DP Approach then we start from back and find the cost of reaching end from that specific node. So when we reach the first node we will have two options

`. 99+1 path
2. 5+1 path

Now we simply have to decide between (1+(99+1)) and (20+(5+1)) path

## [Answer](https://leetcode.com/problems/jump-game/solution/)
### Method 4 - Greedy - :rocket: 1ms (99.46%)
```java
class Solution {
    public boolean canJump(int[] nums) {
        int last = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= last) {
                last = i;
            }
        }
        
        return last == 0;
    }
}
```
### Method 3 - DP Bottom-up 
#### Approach 2 - Right to Left - :turtle: 174ms (25.76%)
```java
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        
        for (int i = nums.length - 2; i >= 0; i--) {
            int fur = Math.min(nums.length - 1, i + nums[i]);
            for (int j = i + 1; j <= fur; j++) {
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[0];
    }

}
```
#### Approach 1 - Left to Right - :rocket: 1ms (99.46%)
```java
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && (i - j <= nums[j])) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[nums.length - 1];
    }

}
```
### Method 2 - DP Top-down - :turtle: 743ms (7.34%)
```java
class Solution {
    Boolean[] memo;
    public boolean canJump(int[] nums) {
        memo = new Boolean[nums.length];
        return bt(nums, 0);
    }
    
    private boolean bt(int[] nums, int lvl) {
        if (lvl >= nums.length - 1) return true;
        
        if (memo[lvl] != null) return memo[lvl];
        
        int fur = Math.min(nums.length - 1, lvl + nums[lvl]);
        for (int i = fur ; i >= lvl + 1; i--) {
            if (bt(nums, i)) {
                memo[lvl] = true;
                return true;
            }
                
        }
        
        memo[lvl] = false;
        return false;
    }
}
```
### Method 1 - Backtracking - TLE (74 / 75 test cases passed.)
```java
class Solution {
    // === Method 1: Backtracking ====
    // 
    public boolean canJump(int[] nums) {
        return bt(nums, 0);
    }
    
    private boolean bt(int[] nums, int lvl) {
        if (lvl >= nums.length - 1) return true;
        
        for (int i = lvl + 1; i <= Math.min(nums.length - 1, lvl + nums[lvl]); i++) {
            if (bt(nums, i))
                return true;
        }
        
        return false;
    }
}
```
