## [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)

Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.

 

Example 1:

```
Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
```

Example 2:

```
Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
```

Note:

- 1 <= A.length <= 10000
- -10000 <= A[i] <= 10000
- A is sorted in non-decreasing order.

## Answer
### Method 1 - Two Pointer - :rocket: 1ms (100%)

```java
class Solution {
    public int[] sortedSquares(int[] a) {
        int i = 0;  // min positive number
        
        while (i < a.length && a[i] < 0) {
            i++;
        }
        int j = i - 1;  // min abs of negative number.
        
        int[] ans = new int[a.length];
        
        int t = 0;
        while (j >= 0 && i < a.length) {
            if (a[j] * a[j] < a[i] * a[i]) {
                ans[t++] = a[j] * a[j--];
            } else {
                ans[t++] = a[i] * a[i++];
            }
        }
        
        while (j >= 0) {
            ans[t++] = a[j] * a[j--];
        }
        
        while (i < a.length) {
            ans[t++] = a[i] * a[i++];
        }
        
        return ans;
    }
}
```
