// https://leetcode.com/problems/subarray-sum-equals-k/
class Solution {
    public int subarraySum(int[] nums, int k) {
        // <presum, freq>
        Map<Integer, Integer> presum = new HashMap<>();
        int res = 0, sum_i = 0;
        
        // base case
        presum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum_i += nums[i];

            // presum of nums[0..j]
            // presum[j] = presum[i] - k
            int sum_j = sum_i - k;

            // update if sum_j already exists
            if (presum.containsKey(sum_j)) {
                res += presum.get(sum_j);
            }

            // freq for presum of nums[0..i]
            presum.put(sum_i, presum.getOrDefault(sum_i, 0) + 1);
        }

        return res;
    }
}

class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null) return 0;
        
        int cnt = 0, sum = 0;
        
        // <sum, freq>
        Map<Integer, Integer> map = new HashMap();
        map.put(0, 1);  // in case the first k is missed
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            
            // if map contains sum - k, which means there is one match found
            if (map.containsKey(sum - k)) {
                cnt += map.get(sum - k);    // NOT simply add 1 because there may exist multiple
            }
            
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return cnt;
    }
}
