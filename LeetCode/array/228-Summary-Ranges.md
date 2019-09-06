## [228. Summary Ranges](https://leetcode.com/problems/summary-ranges/)

Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:
```
Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
```
Example 2:
```
Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
```

## Answer
### Method 1 :rocket: 0ms
```java
class Solution {
    //0ms
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList();
        
        if (nums == null) return res;
        
        for (int i = 0; i < nums.length; i++) {
            int tmp = i;
            String s = Integer.toString(nums[i]);
            while (i + 1 < nums.length && nums[i + 1] == nums[i] + 1) {
                i++;
            }
            if (i != tmp)
                s = s + "->" + Integer.toString(nums[i]);
            res.add(s);
        }
        
        return res;
    }
}
```
