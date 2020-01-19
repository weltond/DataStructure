## [283. Move Zeroes](https://leetcode.com/problems/move-zeroes/)

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:
```
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
```

Note:

- You must do this in-place without making a copy of the array.
- Minimize the total number of operations.

## Answer
### Method 3 - Two Pointer - :rocket: 0ms

```java
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int j = 0;  // means slot to fill in non-zero
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (; j < nums.length; j++) {
            nums[j] = 0;
        }
    }
}
```

### Method 2 - :rocket: 0ms
```java
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        int s = 0;
        boolean found = false;
        for (int f = 0; f < nums.length; f++) {
            if (nums[f] == 0 && !found) {
                s = f;
                found = true;
            } else if (nums[f] != 0) {
                swap(nums, s++, f);
            }
        }
    }
    private void swap(int[] n, int l, int r) {
        int t = n[l];
        n[l] = n[r];
        n[r] = t;
    }
}
```
### Method 1 - :turtle: 1ms (18.52%)
```java
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        int slow = 0;   // slow's left is final result (exclude slow)
        int cntZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, slow++);
            } 
        }
        
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
        
    }
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
```
