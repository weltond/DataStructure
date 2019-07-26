## [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:
```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
```
Example 2:
```
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```

## Answer
### Method 1 - Binary Search - :rocket: 0ms
#### Approach 2
```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        
        int left = 0, right = nums.length - 1, mid = -1, start = -1, tmpEnd = nums.length;
        
        while (left <= right) {
            mid = left + (right - left) / 2;
            
            if (nums[mid] > target) {
                right = mid - 1;
                tmpEnd = mid;
            }
            else if (nums[mid] < target) left = mid + 1;
            else {
                if (mid == 0 || nums[mid - 1] != target) {
                    start = mid;
                    break;
                }
                else right = mid - 1;
            }
        }
        
        if (start == -1) return new int[]{-1, -1};
        
        int end = start;
        for (; end < tmpEnd; end++) {
            if (nums[end] != target) break;
        }
        
        return new int[]{start, end - 1};
    }
}
```
#### Approach 1
```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        
        int[] res = new int[2];
        
        int left = 0, right = nums.length - 1;
        
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) right = mid;
            else if (nums[mid] > target) right =  mid - 1;
            else left = mid + 1;
        }
        
        int start = nums[left] == target ? left : nums[right] == target ? right : -1;
        int end = -1;
        for (end = start; end < nums.length && end >= 0; end++) {
            if (nums[end] != target) {
                break;
            }
        }
        
        return new int[]{start, end >= 0 ? --end : -1};
    }
}
```
