// https://leetcode.com/problems/3sum/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        
        return dfs(nums, 3, 0, 0);
    }
    
    private List<List<Integer>> dfs(int[] nums, int k, int start, int target) {
        List<List<Integer>> res = new ArrayList();
        if (start >= nums.length) return res;
        if (k == 2) {
            int i = start, j = nums.length - 1;
            while (i < j) {
                if (nums[i] + nums[j] == target) {
                    List<Integer> list = new ArrayList();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    res.add(new ArrayList(list));
                    
                    while (i < j && nums[i] == nums[i + 1]) i++;
                    while (j > i && nums[j] == nums[j - 1]) j--;
                    
                    i++; j--;
                } else if (nums[i] + nums[j] < target) i++;
                else j--;
            }
            
            return res;
        }
        
        for (int i = start; i <= nums.length - k; i++) {
            if (i != start && nums[i] == nums[i - 1]) continue;
            
            List<List<Integer>> ret = dfs(nums, k - 1, i + 1, target - nums[i]);
            
            if (ret == null || ret.size() == 0) continue;
            
            for (List<Integer> l : ret) {
                l.add(nums[i]);
                res.add(l);
            }
        }
        
        return res;
    }
}
class Solution {
    // ============= Method : Like two sum using two pointers ============
    // 33ms (97.60%)
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList();
        
        List<List<Integer>> res = new ArrayList();
        
        Arrays.sort(nums);  // have to sort before processing
        
        for (int i = 0; i < nums.length; i++) {
            // if same value, skip to avoid duplicate res.
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
            
            int twoSum = 0 - nums[i];
            int left = i + 1, right = nums.length - 1;
            
            // two pointers to get the sum
            while (left < right) {
                if (nums[left] + nums[right] == twoSum) {
                    List<Integer> list = new ArrayList();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    
                    res.add(new ArrayList(list));
                    
                    while (left + 1 < nums.length && nums[left] == nums[left + 1]) left++;
                    while (right - 1 >= 0 && nums[right] == nums[right - 1]) right--;
                    
                    left++; right--;
                } else if (nums[left] + nums[right] < twoSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return res;
//         while (left < right) {
//             List<Integer> list = new ArrayList();
//             Map<Integer, Integer> map = new HashMap();
            
//             int twoSum = nums[left] + nums[right];
//             int third = binarySearch(nums, left + 1, right - 1, 0 - twoSum);
//             if (third != -1) {
//                 list.add(nums[left]);
//                 list.add(nums[third]);
//                 list.add(nums[right]);
//                 res.add(new ArrayList(list));
//             }
            
//             if (twoSum >= 0) {
//                 int tmp = nums[right];
//                 while (--right >= 0 && tmp == nums[right]);
//             }
//             else {
//                 int tmp = nums[left];
//                 while (++left < nums.length && tmp == nums[left]);
//             }
//         }
        
//        return res;
    }
    
//     private int binarySearch(int[] nums, int left, int right, int target) {
//         while (left <= right) {
//             int mid = left + (right - left) / 2;

//             if (nums[mid] == target) return mid;
//             else if (nums[mid] > target) right--;
//             else left++;
//         }
        
//         return -1;
//     }
}
