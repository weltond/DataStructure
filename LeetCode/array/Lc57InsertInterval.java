// https://leetcode.com/problems/insert-interval/

/**
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
*/

// 1ms (99.26%)
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        int[][] res = new int[intervals.length + 1][2];
        int idx = 0;    // length
        boolean added = false;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] < newInterval[0]) {  // cur end < new start  => add cur
                res[idx++] = intervals[i];
            } else if (intervals[i][0] > newInterval[1]) {// cur start > new end   => add new, then cur
                if (!added) {
                    res[idx++] = newInterval;
                    added = true;
                }
                
                res[idx++] = intervals[i];
            }else{    // cur end >= new start && cur end > new end => [1,5], [2,4]
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }
        
        if (!added) {
            res[idx++] = newInterval;
        }
        
        return Arrays.copyOf(res, idx);
    }
}

class Solution {
    // ========= Approach 2==========
    // 1ms (99.76%)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList();
        for (int i = 0; i < intervals.length; i++) {
            // new is behind current
            if (newInterval == null || intervals[i][1] < newInterval[0]) {
                res.add(intervals[i]);
            } 
            // new is before current
            else if (intervals[i][0]> newInterval[1]) {
                res.add(newInterval);
                res.add(intervals[i]);
                newInterval = null;
            }
            // new has interval with current
            else {
                newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            }
        }
        
        if (newInterval != null) {
            res.add(newInterval);
        }
        
        int size = res.size();
        int[][] ans = new int[size][];
        for (int i = 0; i < size; i++) {
            ans[i] = res.get(i);
        }
        
        return ans;
    }
    
    // ======= Approach 2 ========
    // 1ms (99.76%)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList();
        // interval before new
        int i = 0, len = intervals.length;
        while (i < len && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i++]);
        }
        
        // has interval
        while (i < len && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        res.add(newInterval);
        
        // rest
        while (i < len) {
            res.add(intervals[i++]);
        }
        
        int size = res.size();
        int[][] ans = new int[size][];
        for (i = 0; i < size; i++) {
            ans[i] = res.get(i);
        }
        
        return ans;
    }
}
