//https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) return new int[]{-1, -1};
        
        return hash(numbers, target);
    }
    
    // ===== Method 1: Two Pointers -> 0ms
    private int[] twoPointer(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            
            if (sum < target) l++;
            else if (sum > target) r--;
            else return new int[]{l + 1, r + 1};
            
        }
        
        return new int[]{0,0};
    }
    
    // ===== Method 2: Hash -> 2ms
    public int[] hash(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < numbers.length; i++) {
            int rem = target - numbers[i];
           
            if (map.containsKey(rem)) {
                return new int[]{map.get(rem) + 1, i + 1};
            }
            
            map.put(numbers[i], i);
        }
        
        return new int[]{-1, -1};
    }
}
