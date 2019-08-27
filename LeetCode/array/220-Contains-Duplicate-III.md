## [220. Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/)

Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Example 1:
```
Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
```
Example 2:
```
Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
```
Example 3:
```
Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
```
## Answer
### Method 1 - TreeSet - :turtle: 24ms (32.80%)
- Time: O(nlogk)
```java
class Solution {
    // ======== Method 1: TreeSet =========
    // O(nlogk) 24ms (32.80%)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0) return false;
        
        TreeSet<Long> ts = new TreeSet();
        for (int i = 0; i < nums.length; i++) {
            Long floor = ts.floor((long)nums[i] + (long)t);
            Long ceil = ts.ceiling((long)nums[i] - (long)t);
            if ((floor != null && floor >= nums[i]) ||
                (ceil != null && ceil <= nums[i]))
                return true;
            
            ts.add((long)(nums[i]));
            
            if (i >= k) {
                ts.remove((long)(nums[i - k]));
            }
        }
        
        return false;
    }
}
```
