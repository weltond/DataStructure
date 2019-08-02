## [88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:
```
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
```

## Answer
### Method 1 - Two Pointers - :rocket: 0ms
- Start from the end.
```java
class Solution {
    // 0ms
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) return;
        
        // start from largest
        int l1 = m - 1, l2 = n - 1, i = m + n - 1;
        
        while (i >= 0) {
            if (l2 < 0 || (l1 >= 0 && nums1[l1] >= nums2[l2])) {
                nums1[i--] = nums1[l1--];
            } else {
                nums1[i--] = nums2[l2--];
            }
        }
        
    }
}
```
