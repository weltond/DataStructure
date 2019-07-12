## [312. Burst Balloons](https://leetcode.com/problems/burst-balloons/)

Given n balloons, indexed from `0` to `n-1`. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get `nums[left] * nums[i] * nums[right]` coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

- You may imagine `nums[-1] = nums[n] = 1`. They are not real therefore you can not burst them.
- `0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100`
Example:
```
Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
```

## Answer
We then think can we apply the divide and conquer technique? After all there seems to be many self similar sub problems from the previous analysis.

- Well, the nature way to divide the problem is burst one balloon and separate the balloons into 2 sub sections one on the left and one one the right. However, in this problem the left and right become adjacent and have effects on the maxCoins in the future.
- Then another interesting idea come up. Which is quite often seen in dp problem analysis. That is **reverse thinking**. Like I said **the coins you get for a balloon does not depend on the balloons already burst**. Therefore instead of divide the problem by the first balloon to burst, we divide the problem by the last balloon to burst. Why is that? **Because only the first and last balloons we are sure of their adjacent balloons before hand!**

### Method 2 - DP
```java
```
### Method 1 - DFS + Memo 

#### Approach 1 : - TLE(34 / 70 test cases passed)
- Time: O(n!) (?)
```java
class Solution {
    // ======== Method 1: DFS + Memo =========
    // TLE (34 of 70 passed)
    Map<List<Integer>, Integer> map;    // <list of remaining, max>
    List<Integer> arr;
    public int maxCoins(int[] nums) {
        if (nums == null) return 0;
        
        arr = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            arr.add(nums[i]);
        }
        map = new HashMap();
        
        return dfs();
    }
    
    private int dfs() {
        if (arr.size() == 1) return arr.get(0);
        
        if (map.containsKey(arr)) return map.get(arr);
        
        int sum = 0;
        for (int i = 0, len = arr.size(); i < len; i++) {
            int value = arr.get(i);
            int cur = (i == 0 ? 1 : arr.get(i - 1)) * value * (i == len - 1 ? 1 : arr.get(i + 1));
            
            arr.remove(i);
            
            sum = Math.max(sum, dfs() + cur);
            
            arr.add(i, value);
        }
        
        map.put(arr, sum);
        
        return sum;
        
    }
}
```
