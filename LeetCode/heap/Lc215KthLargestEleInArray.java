// https://leetcode.com/problems/kth-largest-element-in-an-array/

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
    
    // ===== Method 1: PQ ======
    // 6ms
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
