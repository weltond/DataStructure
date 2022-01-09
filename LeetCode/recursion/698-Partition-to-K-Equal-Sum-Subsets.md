## [698. Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/)

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:

```
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
```

Note:

- `1 <= k <= len(nums) <= 16`.
- `0 < nums[i] < 10000`.

## Answer
### Method 1 - DFS - :rocket: 1ms (100%)

#### Approach 2
From the view of bucket, every bucket iterates through items in `nums`, and decide if the item can be fit into current bucket.

```java
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        
        if (sum % k != 0) return false;
        
        int each = sum / k;
        
        return dfs(nums, each, 0, k, each, new boolean[nums.length]);
        
    }
    
    private boolean dfs(int[] nums, int subSum, int start, int k, int sum, boolean[] used) {
        // base case, all bucket matches requirement
        if (k == 0) return true;
        
        if (subSum == 0) {
            // current bucket is full, move to next bucket.
            return dfs(nums, sum, 0, k - 1, sum, used);
        }
        
        
        for (int i = start; i < nums.length; i++) {
            // nums[i] has been used
            // current bucket cannot have nums[i]
            if (used[i] || subSum < nums[i]) continue;
            
            // put nums[i] into current bucket.
            used[i] = true;
            
            if (dfs(nums, subSum - nums[i], i + 1, k, sum, used)) {
                return true;
            }
            
            used[i] = false;
        }
        
        return false;
    }
}
```

#### Approach 1 

From the view of `nums`. Put every item into k bucket.

- optimize - 21ms (21.1%)

Sort array and put item from `nums` to bucket in descending order, in order to prune faster.

```java
class Solution {
    class C {
        int x;
    }
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) return false;

        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % k != 0) return false;

        int target = sum / k;
        
        Arrays.sort(nums);
        
        return bt(nums, target, new int[k], nums.length - 1);
    }

    private boolean bt(int[] nums, int target, int[] bucket, int idx) {
        if (idx == -1) {
            // check if all bucket sum equal target
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != target) return false;
            }

            return true;
        }

        // brute force all nums[index] into bucket
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] + nums[idx] > target) continue;

            bucket[i] += nums[idx];

            if (bt(nums, target, bucket, idx - 1)) return true;

            bucket[i] -= nums[idx];
        }

        return false;
    }
}
```

- No optimize - 22204ms (5.02%) 🐢
- 
```java
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) return false;

        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % k != 0) return false;

        int target = sum / k;
        
        
        return bt(nums, target, new int[k], 0);
    }

    private boolean bt(int[] nums, int target, int[] bucket, int idx) {
        if (idx == nums.length) {
            // check if all bucket sum equal target
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != target) return false;
            }

            return true;
        }

        // brute force all nums[index] into bucket
        for (int i = 0; i < bucket.length; i++) {
            //prune
            if (bucket[i] + nums[idx] > target) continue;

            bucket[i] += nums[idx];

            if (bt(nums, target, bucket, idx + 1)) return true;

            bucket[i] -= nums[idx];
        }

        return false;
    }
}
```
