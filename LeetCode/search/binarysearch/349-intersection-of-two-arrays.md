## [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given two arrays, write a function to compute their intersection.

Example 1:

```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
```

Example 2:

```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
```

Note:
- Each element in the result must be unique.
- The result can be in any order.

## Answer
### Method 2 - HashTable - 

```java

```

### Method 1 - Two Pointers - :rocket: 1ms (99.88%)

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return new int[]{};
        
        if (nums1.length > nums2.length) return intersection(nums2, nums1);
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int i = 0, j = 0, idx = 0;
        
        int[] res = new int[nums1.length];
        
        while (i < nums1.length && j < nums2.length) {
            while (i > 0 && nums1[i] == nums1[i - 1]) {
                i++;
                if (i >= nums1.length) break;
            }
            while (j > 0 && nums2[j] == nums2[j - 1]) {
                j++;
                if (j >= nums2.length) break;
            }
            if (i >= nums1.length || j >= nums2.length) break;
            
            if (nums1[i] == nums2[j]) {
                res[idx++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
            
            
        }
        
        int[] ret = new int[idx];
        idx = 0;
        for (i = 0; i < ret.length; i++) {
            ret[idx] = res[idx++];
        }
        
        return ret;
    }
}
```
