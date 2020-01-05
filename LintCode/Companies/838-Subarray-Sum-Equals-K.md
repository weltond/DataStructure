## [838. Subarray Sum Equals K](https://www.lintcode.com/problem/subarray-sum-equals-k/description?_from=ladder&&fromId=130)

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example1

```
Input: nums = [1,1,1] and k = 2
Output: 2
Explanation:
subarray [0,1] and [1,2]
```

Example2

```
Input: nums = [2,1,-1,1,2] and k = 3
Output: 4
Explanation:
subarray [0,1], [1,4], [0,3] and [3,4]
```

## Answer
### Method 1 - Prefix Sum - :rabbit: 485ms (84.20%)

```java
public class Solution {
    /**
     * @param nums: a list of integer
     * @param k: an integer
     * @return: return an integer, denote the number of continuous subarrays whose sum equals to k
     */
    public int subarraySumEqualsK(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap();  // val, freq
        map.put(0, 1);  // init
        
        // prefix sum
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        
        int res = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] - k)) {
                res += map.get(nums[i] - k);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        
        return res;
    }
}
```
