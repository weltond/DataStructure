// https://leetcode.com/problems/longest-consecutive-sequence/

// UNION FIND????????
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet();
        int res = 0;
        for (int i : nums) {
            set.add(i);
        }
        
        for (int i = 0; i < nums.length; i++) {
            int cnt = 1;
            if (!set.contains(nums[i] - 1)) {
                int j = nums[i] + 1;
                while (set.contains(j)) {
                    j++;
                    cnt++;
                }
                
                res = Math.max(res, cnt);
            }
        }
        return res;
    }
}

class Solution {
    public int longestConsecutive(int[] nums) {
        return m3(nums);
    }
    
    // ===== Method 3: HashSet and Sequence Building =====
    // Time = O(n), Space = O(n)
    public int m3(int[] nums) {
        Set<Integer> set = new HashSet();
        for (int num : nums) {
            set.add(num);
        }
        
        int longest = 0;
        
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curNum = num;
                int curStreak = 1;
                
                while (set.contains(curNum + 1)) {
                    curNum += 1;
                    curStreak += 1;
                }
                
                longest = Math.max(longest, curStreak);
            }
        }
        
        return longest;
    }
    
    
    // ===== Method 2: Sort =====
    // Time = O(nlgn), Space = O(1)
//     public int m2(int[] nums) {
//         if (nums.length == 0) return 0;
        
//         Arrays.sort(nums);
        
//         int longestStreak = 1, currentStreak = 1;
        
//         for (int i = 1; i < nums.length; i++) {
//             if (nums[i] != nums[i - 1]) {
//                 if (nums[i] == nums[i-1] + 1) {
//                     currentStreak += 1;
//                 } else {
//                     longestStreak = Math.max(longestStreak, currentStreak);
//                     currentStreak = 1;
//                 }
//             }
//         }
        
//         return Math.max(longestStreak, currentStreak);
//     }
    
    
    // ===== Method 1: Brute Force =====
    // Time = O(n3), Space = O(1)
//     private boolean arrayContains(int[] arr, int num) {
//         for (int i = 0; i < arr.length; i++) {
//             if (arr[i] == num) return true;
//         }
//         return false;
//     }
//     public int m1(int[] nums) {
//         int longestStreak = 0;
//         for (int num : nums) {
//             int currentNum = num;
//             int currentStreak = 1;
            
//             while (arrayContains(nums, currentNum + 1)) {
//                 currentNum += 1;
//                 currentStreak += 1;
//             }
            
//             longestStreak = Math.max(longestStreak, currentStreak);
//         }
        
//         return longestStreak;
//     }
}
