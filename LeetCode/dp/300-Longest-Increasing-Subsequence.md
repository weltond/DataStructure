## [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)
![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:
```
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
```
Note:

- There may be more than one LIS combination, it is only necessary for you to return the length.
- Your algorithm should run in O(n2) complexity.

**Follow up: Could you improve it to O(n log n) time complexity?**

## Answer
### Idea
LIS subsequence depends on all its subproblem results.
1. Using **DP**, for each current element, compare it with **ALL** its previous element.
2. Using **Binary Search**, store each element into an array that is going to be sorted. And the final result would be the last element's index (not the array length).

### Method 2 - DP with Binary Search
- Time: O(nlogn)

#### Approach 2

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int len = 1;
        int[] tailTable = new int[n];
        tailTable[0] = nums[0];
        for(int i = 1; i < n; i++){
            if(tailTable[0] > nums[i]){
                tailTable[0] = nums[i];
            }else if(nums[i] > tailTable[len -1]){
                tailTable[len++] = nums[i];
            }else{
                // nums[i] < tailTable[len-1] && nums[i] > tailTable[0]
                tailTable[findPosition(tailTable, -1, len -1, nums[i])] = nums[i];
            }
        }
        return len;
        
    }
    
    private int findPosition(int[] tailTable, int left, int right, int cur){
        while(left + 1 < right){
            int mid = left + (right - left) / 2;
            
            if(tailTable[mid] >= cur){
                right = mid;
            }else{
                left = mid;
            }
        }
        return right;
    }
}
```

#### Approach 1

```java
class Solution {
    // =========== Method 2: DP with BS ============
    // 1ms (93.07%)
    /**
    [10,9,2,5,3,7,101,18]
    dp[]:
    0 0 0 0 0 0 0 0 
    10 0 0 0 0 0 0 0 
    9 0 0 0 0 0 0 0 
    2 0 0 0 0 0 0 0 
    2 5 0 0 0 0 0 0 
    2 3 0 0 0 0 0 0 
    2 3 7 0 0 0 0 0 
    2 3 7 101 0 0 0 0 
    */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        
        int[] dp = new int[nums.length];

        int len = 0;
        
        for (int num : nums) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[j]+" ");
            }
            System.out.println();
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                // if num not found, return -(insertion point) - 1
                i = -(i + 1);
            }
            
            dp[i] = num;
            
            if (i == len) {
                len++;
            }
        }
        
        return len;
    }
}
```
### Method 1 - DP - :rabbit: 8ms (64.68%)
- Time: O(n^2)
```java
class Solution {
    // =========== Method 1: DP ============
    // 8ms (64.68%)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        
        int[] dp = new int[nums.length];

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        
        return ans;
    }
}
```
