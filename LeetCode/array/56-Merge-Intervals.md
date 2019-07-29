## [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)

Given a collection of intervals, merge all overlapping intervals.

Example 1:
```
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
```
Example 2:
```
Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
```
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

## Answer
### Method 2 - :rocket: 3ms (95.70%)
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[][]{};
        
        LinkedList<int[]> res = new LinkedList();
        
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        
        Arrays.sort(start);
        Arrays.sort(end);
        
        // [1,2,8,15]
        // [3,6,10,18]
        // j is the start pos, i is end pos
        for (int i = 0, j = 0; i < intervals.length; i++) {
            if (i + 1 == intervals.length || start[i + 1] > end[i]) {
                res.add(new int[]{start[j], end[i]});
                j = i + 1;
            }
        }
        
        
        int[][] ret = new int[res.size()][2];
        int i = 0;
        for (int[] arr : res) {
            ret[i++] = arr;
        }
        
        return ret;
    }
}
```
### Method 1 - Sorting - :rabbit: 37ms (35.82%)
- Time: O(nlogn)
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[][]{};
        
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        
        LinkedList<int[]> res = new LinkedList();
        
        for (int i = 0; i < intervals.length; i++) {
            if (res.isEmpty() || res.getLast()[1] < intervals[i][0]) {
                res.add(new int[]{intervals[i][0], intervals[i][1]});
            } else {
                res.getLast()[1] = Math.max(res.getLast()[1], intervals[i][1]);
            }
        }
        
        int[][] ret = new int[res.size()][2];
        int i = 0;
        for (int[] arr : res) {
            ret[i++] = arr;
        }
        
        return ret;
    }
}
```
### Method 0 - Priority Queue - [this](https://github.com/weltond/DataStructure/blob/master/LeetCode/heap/Lc56MergeIntervals.java) 
