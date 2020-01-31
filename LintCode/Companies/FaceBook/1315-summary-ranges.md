## [1315. Summary Ranges](https://www.lintcode.com/problem/summary-ranges/description?_from=ladder&&fromId=130)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a sorted integer array without duplicates, return the summary of its ranges.


Example1

```
Input: [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
```

Example2

```
Input: [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
```

Notice
- The result is in ascending order

## Answer
### Method 1 - Two Pointer - :turtle: 252ms (17.00%)

```java
public class Solution {
    /**
     * @param nums:  a sorted integer array without duplicates
     * @return: the summary of its ranges
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList();
        
        if (nums == null) return res;
        int start = 0, end = 1;
        
        while (end < nums.length) {
            while (end < nums.length && nums[end] == nums[end - 1] + 1) {
                end++;
            }
            
            StringBuilder sb = new StringBuilder();
            if (end - 1 != start) {
                sb.append(nums[start]).append("->").append(nums[end - 1]);
            } else {
                sb.append(nums[start]);
            }
            res.add(sb.toString());

            start = end;
            end++;
        }
        
        if (start == nums.length - 1) {
            res.add(Integer.toString(nums[start]));
        }
        
        return res;
    }
}
```
