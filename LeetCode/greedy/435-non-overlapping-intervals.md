## [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.


Example 1:

```
Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
```

Example 2:

```
Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
```

Example 3:

```
Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
```

Note:

- You may assume the interval's end point is always bigger than its start point.
- Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

## Answer
### Method 1 - Greedy - :rocke: 1ms (100%)
#### Approach 2 - Sort by end
- the interval with early start might be very long and incompatible with many intervals. But if we choose the interval that ends early, we'll have more space left to accommodate more intervals.

```java

```

#### Approach 1 - Sort by start

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        Arrays.sort(intervals, (o1, o2) -> {
            return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
        });
        int i = 1, len = intervals.length, cnt = 1;
        int pre = intervals[0][1];
        
        while (i < len) {
            int[] cur = intervals[i++];
            int st = cur[0], end = cur[1];
            if (pre <= st) {
                cnt++;
                pre = end;
            } else {
                pre = Math.min(pre, end);   // should use the min end for next comparison.
            }
        } 
        return len - cnt;
    }
}
```
