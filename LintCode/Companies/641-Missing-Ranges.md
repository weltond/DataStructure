## [641. Missing Ranges](https://www.lintcode.com/problem/missing-ranges/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example 1

```
Input:
nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99
Output:
["2", "4->49", "51->74", "76->99"]
Explanation:
in range[0,99],the missing range includes:range[2,2],range[4,49],range[51,74] and range[76,99]
```

Example 2

```
Input:
nums = [0, 1, 2, 3, 7], lower = 0 and upper = 7
Output:
["4->6"]
Explanation:
in range[0,7],the missing range include range[4,6]
```

## Answer
### Method 1 - :turtle: 4524ms (20.40%)

- Class Solution

```java
public class Solution {
    /*
     * @param nums: a sorted integer array
     * @param lower: An integer
     * @param upper: An integer
     * @return: a list of its missing ranges
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // write your code here
        List<String> res = new LinkedList();
        
        if (nums == null || nums.length == 0) {
            add(lower, upper, res);
            return res;
        }
        
        add(lower, (long) nums[0] - 1, res);
        
        for (int i = 1; i < nums.length; i++) {
            add((long) nums[i - 1] + 1, (long) nums[i] - 1, res);
        }
        
        add((long)nums[nums.length - 1] + 1, upper, res);
        
        return res;
    }
    
    private void add(long from, long to, List<String> res) {
        if (from > to) return;
        
        if (from == to) {
            res.add(from + "");
        } else {
            res.add(from + "->" + to);
        }
        
    }
}
```
