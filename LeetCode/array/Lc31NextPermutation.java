// https://leetcode.com/problems/next-permutation/

// 1ms (76.70%)
class Solution {
    public void nextPermutation(int[] nums) {
        // fing the first decreasing
        int idx = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                idx = i;
                break;
            }
        }
        
        if (idx == -1) {
            Arrays.sort(nums);
            return;
        }
        
        int cur = nums[idx];
        int k = nums.length - 1;
        // find the one that is just larger than it
        for (; k >= idx; k--) {
            if (nums[k] > cur) {
                break;
            }
        }
        
        swap(nums, idx, k);
        
        Arrays.sort(nums, idx + 1, nums.length);
        
    }
    
    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}

public void reverse(int[] num, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }
    
    public void nextPermutation(int[] num) {
        // find the last increase index
        int index = -1;
        for (int i = num.length - 2; i >= 0; i--) {
            if (num[i] < num[i + 1]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            reverse(num, 0, num.length - 1);
            return;
        }
        
        // find the first bigger one
        int biggerIndex = index + 1;
        for (int i = num.length - 1; i > index; i--) {
            if (num[i] > num[index]) {
                biggerIndex = i;
                break;
            }
        }

        // swap them to make the permutation bigger
        int temp = num[index];
        num[index] = num[biggerIndex];
        num[biggerIndex] = temp;
        
        // reverse the last part
        reverse(num, index + 1, num.length - 1);
    }

public class Solution {
    /**
     * @param nums: An array of integers
     * @return: nothing
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        
        // 1. find pivot from right to left that is smaller than its right
        int pivot = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }
        
        if (pivot == -1) {
            Arrays.sort(nums);
            return;
        }
        
        // 2. from pivot's right, find the one that is the smallest greater than pivot
        int candidate = -1, minMax = Integer.MAX_VALUE;
        for (int i = pivot + 1; i < nums.length; i++) {
            if (nums[i] <= nums[pivot]) break;
            if (nums[i] < minMax) {
                minMax = nums[i];
                candidate = i;
            }
        }

        // 3. swap their value
        swap(nums, pivot, candidate);
        
        // 4. sort the rest 
        Arrays.sort(nums, pivot + 1, nums.length);
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}

class Solution {
    // 1ms (99.81%)
    public void nextPermutation(int[] nums) {
        if (nums == null) return;
        // 1. find the first element that is smaller than its right from right to left
        int i;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) break;
        }
        
        // if not found, sort in ascending order and return
        if (i == -1) {
            Arrays.sort(nums);
            return;
        }
        
        // 2. find the smallest element that is larger than i-th element
        int tmp = nums[i], smallest = i + 1;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > tmp && nums[j] <= nums[smallest]) {
                smallest = j;
            }
        }
        
        // swap
        int t = nums[i];
        nums[i] = nums[smallest];
        nums[smallest] = t;
        
        // sort the rest from i+1
        Arrays.sort(nums, i + 1, nums.length);
    }
}
