## [410. Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/)

Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

- 1 ≤ n ≤ 1000
- 1 ≤ m ≤ min(50, n)
Examples:
```
Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
```

## Answer
### Method 2 - DP -
```java
// TO DO...
```

### Method 1 - Binary Search - :rocket: 0ms 
Idea:
- If m = 1, the res is the sum of the array.
- If m = n, the res is the max num of the array.
- If 1 < m < n, we can use Binary Search to find the result: **find largest subarray that is less or equal to mid**:
  - if we can't, it means the `mid` we pick is too small because we've tried our best to make sure each part holds as many numbers as we can but we still have numbers left. So it's impossible to cut array into `m` parts while make sure each part is no larger than `mid`. we should increase size m. This leads to `l = mid + 1`
  - if we can, it's either we successfully divide the array into m parts and sum of each part is less than 'mid'; or we used up all numbers before we reach m. Both means that we should lower `mid` because we need to find the minimum one.
```java
class Solution {
    public int splitArray(int[] nums, int m) {
        int max = 0;
        long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        
        if (m == 1) return (int) sum;
        
        long l = max, r = sum;
        
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (canSplit(mid, nums, m)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        
        return (int) r;
    }
    
    private boolean canSplit(long target, int[] nums, int m) {
        int cnt = 1;
        long total = 0;
        
        for (int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                cnt++;
                if (cnt > m) return false;
            }
        }
        
        return true;
    }
}
```
