## [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

 

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

 

Example 1:

```
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
```

Example 2:

```
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
``` 

**Constraints**:

- 1 <= k <= nums.length <= 104
- -104 <= nums[i] <= 104

## Answers 

### Method 2 - Quick Select - 10ms (35.38%)
```java
class Solution {
    // ===== Method 2: Quick Partition ======
    // 30ms
    public int findKthLargest(int[] nums, int k) {
        int size = nums.length;
        
        helper(nums, 0, size - 1, k - 1);
        
        return nums[k - 1];
    }
    
    private void helper(int[] nums, int left, int right, int k) {
        if (left >= right) return;
        
        int pos = quickSelect(nums, left, right);
        
        if (pos > k) {
            helper(nums, left, pos - 1, k);
        } else if (pos < k) {
            helper(nums, pos + 1, right, k);
        }
    }
    
    private int quickSelect(int[] nums, int left, int right) {
        int pivot = nums[right];
        int tmp = right;
        right = right - 1;
        // left side of left (exclude left) is larger than pivot
        // right side of right (exclude right) is smaller than pivot
        while (left <= right) {
            while (left <= right && nums[left] >= pivot) left++;
            while (left <= right && nums[right] <= pivot) right--;
            
            // Corner case: [99,99] 1
            if (left <= right) 
                swap(nums, left++, right--);
        }
        // left = right + 1
        swap(nums, right + 1, tmp);
        return right + 1;   // or left
    }
    
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
```
### Method 1 - PQ - 8ms (45.66%)
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);  // min heap
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        return pq.peek();
    }
}
```
