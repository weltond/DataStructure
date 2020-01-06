## [62. Search in Rotated Sorted Array](https://www.lintcode.com/problem/search-in-rotated-sorted-array/description?_from=ladder&&fromId=130)

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., `0 1 2 4 5 6 7` might become `4 5 6 7 0 1 2`).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Example 1:

```
Input: [4, 5, 1, 2, 3] and target=1, 
Output: 2.
```

Example 2:

```
Input: [4, 5, 1, 2, 3] and target=0, 
Output: -1.
```

Challenge
- O(logN) time

## Answer
### Method 1 - Binary Search- 
#### Approach 1 :rocket: 309ms (82.20%)

```java
public class Solution {
    /**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] a, int target) {
        // write your code here
        if (a == null || a.length == 0) return -1;
        
        int l = 0, r = a.length - 1;
        
        while (l <= r) {
            int m = l + (r - l) / 2;
            
            // left sorted
            if (a[m] > a[r]) {
                if (target <= a[m] && a[l] <= target) {
                    return bs(a, target, l, m);
                } else {
                    l = m + 1;
                }
            } else {    // right sorted
                if (target >= a[m] && target <= a[r]) {
                    return bs(a, target, m, r);
                } else {
                    r = m - 1;
                }
            }
        }
        
        return -1;
    }
    
    private int bs(int[] a, int target, int l, int r) {
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (a[m] == target) return m;
            else if (a[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}
```
