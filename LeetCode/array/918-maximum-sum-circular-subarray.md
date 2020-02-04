## [918. Maximum Sum Circular Subarray](https://leetcode.com/problems/maximum-sum-circular-subarray/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.

Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, `C[i] = A[i]` when `0 <= i < A.length`, and `C[i+A.length] = C[i]` when `i >= 0`.)

Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray `C[i], C[i+1], ..., C[j]`, there does not exist` i <= k1, k2 <= j` with `k1 % A.length = k2 % A.length`.)

 

Example 1:

```
Input: [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3
```

Example 2:

```
Input: [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
```

Example 3:

```
Input: [3,-1,2,-1]
Output: 4
Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
```

Example 4:

```
Input: [3,-2,2,-3]
Output: 3
Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
```

Example 5:

```
Input: [-2,-3,-1]
Output: -1
Explanation: Subarray [-1] has maximum sum -1
```

Note:

- `-30000 <= A[i] <= 30000`
- `1 <= A.length <= 30000`

## Answer
### Method 1 - :rocket: 4ms (95.21%)

```java
class Solution {
    public int maxSubarraySumCircular(int[] a) {
        if (a == null || a.length == 0) return 0;
        
        int curMax = 0, curMin = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, total = 0;
        // two cases: 
        //      1. max subarray
        //      2. total - min subarray
        for (int i = 0; i < a.length; i++) {
            curMax = Math.max(curMax + a[i], a[i]);
            curMin = Math.min(curMin + a[i], a[i]);
            max = Math.max(curMax, max);
            min = Math.min(curMin, min);
            total += a[i];
        }
        
        // corner case: total - min subarray = 0 which means they are negative. So we need to return max instead.
        
        return total - min == 0 ? max : Math.max(max, total - min);
    }
}
```
