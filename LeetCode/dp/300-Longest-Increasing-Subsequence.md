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
- 使用一个辅助空间B数组。
- B[i]存储Dp值为i的最小的数字。（有多个位置，以这些位置为结尾的LIS长度都为i， 则这些数字中最小的一个存在B[i]中）
-  则B数组严格递增。且下标表示LIS长度，也是严格递增，可以在B数组中进行二分查找。
- 对于每个位置i，我们要找，所有小于A[i], 且Dp值最大的那个。这个操作在B数组中二分查找。

#### Approach 3

```java
public class Solution {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        int[] minLast = new int[nums.length + 1];
        minLast[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            minLast[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 0; i < nums.length; i++) {
            // find the first number in minLast >= nums[i]
            int index = binarySearch(minLast, nums[i]);
            minLast[index] = nums[i];
        }
        
        for (int i = nums.length; i >= 1; i--) {
            if (minLast[i] != Integer.MAX_VALUE) {
                return i;
            }
        }
        
        return 0;
    }
    
    // find the first number > num
    private int binarySearch(int[] minLast, int num) {
        int start = 0, end = minLast.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (minLast[mid] < num) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        return end;
    }
}
```

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
