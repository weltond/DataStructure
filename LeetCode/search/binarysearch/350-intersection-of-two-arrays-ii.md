## [350. Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-ii/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given two arrays, write a function to compute their intersection.

Example 1:

```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
```

Example 2:

```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
```

Note:

- Each element in the result should appear as many times as it shows in both arrays.
- The result can be in any order.

## Follow up:

- What if the given array is already sorted? How would you optimize your algorithm?
- What if nums1's size is small compared to nums2's size? Which algorithm is better?
- What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

## Answer
### Method 1 - Two Pointer - :rabbit: 330ms (70%)

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return new int[]{};
        
        Map<Integer, Integer> map = new HashMap();
        
        int[] ans = new int[nums1.length];
        int idx = 0;
        
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        for (int i : nums2) {
            Integer k = map.get(i);
            if (k == null || k == 0) continue;
            ans[idx++] = i;
            map.put(i, k - 1);
        }
        
        return Arrays.copyOf(ans, idx);
    }
}
```
