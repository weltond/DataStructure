## [124. Longest Consecutive Sequence](https://www.lintcode.com/problem/longest-consecutive-sequence/description)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.


Example 1

```
Input : [100, 4, 200, 1, 3, 2]
Output : 4
Explanation : The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length:4
```

- Clarification: Your algorithm should run in O(n) complexity.

## Answer
### Method 1 - HashSet - :rabbit: 261ms (97.60%)

```java
public class Solution {
    /**
     * @param num: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] num) {
        // write your code here
        Set<Integer> set = new HashSet();
        for (int i : num) {
            set.add(i);
        }
        
        int res = 0;
        
        for (int i : num) {
            if (!set.contains(i - 1)) {
                int cur = i;
                int len = 1;
                
                while (set.contains(cur + 1)) {
                    len += 1;
                    cur += 1;
                }
                
                res = Math.max(res, len);
            }
        }
        
        return res;
    }
}
```
