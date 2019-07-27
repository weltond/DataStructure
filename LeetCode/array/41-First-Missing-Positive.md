## [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/)

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
```
Input: [1,2,0]
Output: 3
```
Example 2:
```
Input: [3,4,-1,1]
Output: 2
```
Example 3:
```
Input: [7,8,9,11,12]
Output: 1
```
Note:

- Your algorithm should run in O(n) time and uses constant extra space.

## Answer
### Method 2 - Array - :rocket: 0ms
```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while(i<nums.length){
            if(nums[i]>=1 && nums[i]<nums.length && nums[nums[i]-1]!=nums[i]){
                swap(nums,i,nums[i]-1);
            }else{
                i++;
            }
        }
        i=0;
        while(i<nums.length && nums[i]==i+1){
            i++;
        }
        return i+1;
    }
    
    public void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```
```java
class Solution {
    /**
        Idea is use the array to store the index like this:
            nums[] = {1,2,3,4,5...}
            -> nums[i] = i + 1.
        Not work if nums[0] stores 0. e.g. [1,2]
    */
    
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        int i = 0;
        
        // place nums[i] to its corresponding index
        while (i < nums.length) {
            // ignore negative and num that larger than the length since we don't need to store its index.
            if (nums[i] <= 0 || nums[i] > nums.length) {
                nums[i++] = 0;
            } 
            // swap if possible and avoid duplicate like [1,1]
            else if (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            } 
            // move to next element once meet previous condition
            else {
                i++;
            }
        }
        
        // iterate through the array to find out the missing index.
        i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        
        return nums.length + 1;
    }
    
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
```

### Method 1 - Quick Partition - :rabbit: 1ms (40.96%)
```java
class Solution {
    // ========= Method 2: O(n) O(1) ===========
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        
        int k = quickPartition(nums);

        System.out.println();
        for (int i = 0; i < k; i++) {
            int tmp = Math.abs(nums[i]);
            if (tmp <= k) {
                nums[tmp - 1] = -Math.abs(nums[tmp - 1]);
            }
        }
        
        int res = k + 1;    // init res to be the last one.
        for (int i = 0; i < k; i++) {
            if (nums[i] > 0) {
                res = i + 1;
                break;
            }
        }
        
        return res;
    }
    
    private int quickPartition(int[] nums) {
        int i = 0, j = nums.length - 1;
        
        while (i <= j) {
            while (i <= j && nums[i] > 0) i++;
            while (j >= i && nums[j] <= 0) j--;
            
            if (i <= j) {
                swap(nums, i, j);
            }
        }
        
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    // ========= Method 1: O(n) O(n) ===========
    // 1ms (52.06%)
    // 1. find min, max positive in arr.
    // 2. Add to set.
//     public int firstMissingPositive(int[] nums) {
//         if (nums == null || nums.length == 0) return 1;
        
//         Set<Integer> set = new HashSet();
//         int minNum = Integer.MAX_VALUE, maxNum = Integer.MIN_VALUE, cnt = 0;
//         for (int i = 0; i < nums.length; i++) {
//             if (nums[i] <= 0) continue;
            
//             if (set.add(nums[i])) {
//                 cnt++;  // Avoid duplicate.
//             }
//             if (nums[i] < minNum) {
//                 minNum = nums[i];
//             }
//             if (nums[i] > maxNum) {
//                 maxNum = nums[i];
//             }
//         }
        
//         if (minNum != 1) return 1;
//         if (cnt == maxNum) return maxNum + 1;
        
//         for (int i = 2; i < maxNum; i++) {
//             if (!set.contains(i))  {
//                 return i;
//             }
//         }
        
//         return -1;  // Never executed.
//     }
}
```
