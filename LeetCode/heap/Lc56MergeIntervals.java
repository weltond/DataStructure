// https://leetcode.com/problems/merge-intervals/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    // ========== Method 3: Two Arrays =============
    // 7ms
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return new ArrayList();
        
        LinkedList<Interval> res = new LinkedList();
        
        int[] start = new int[intervals.size()];
        int[] end = new int[intervals.size()];
        
        int idx = 0;
        for (Interval i : intervals) {
            start[idx] = i.start;
            end[idx++] = i.end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        
        // [1,2,8,15]
        // [3,6,10,18]
        // j is the start pos, i is end pos
        for (int i = 0, j = 0; i < intervals.size(); i++) {
            if (i + 1 == intervals.size() || start[i + 1] > end[i]) {
                res.add(new Interval(start[j], end[i]));
                j = i + 1;
            }
        }
        
        return res;
    }
    
    // Idea: Sort interval by start. Then we take the first interval and 
    //       compare its end with the next interval start.
    //       As long as overlap, we update the end to be the max end of the overlapping intervals. Once find non-overlap, update res.
    // O(nlogn)
    
    // =========== Method 2: Sort ============
    // 46ms
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return new ArrayList();
        
        LinkedList<Interval> res = new LinkedList();
        
        Collections.sort(intervals, (o1, o2) -> o1.start - o2.start);
        
        for (Interval i : intervals) {
            // if list is empty or
            // if the current interval does not overlap with previou
            if (res.isEmpty() || res.getLast().end < i.start) {
                res.add(i);
            } 
            // otherwise, there is overlap, merge the current and previous intervals
            else {
                res.getLast().end = Math.max(res.getLast().end, i.end);  
            }
        }
        
        
        return res;
    }
    
    // =========== Method 1: PQ =============
    // 45ms
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return new ArrayList();
        
        List<Interval> res = new ArrayList();
        
        PriorityQueue<Interval> pq = new PriorityQueue<Interval>(intervals.size(), (o1, o2) -> (o1.start - o2.start));  // min heap
        
        for (Interval i : intervals) {
            pq.offer(i);
        }

        Interval first = pq.poll();
        int start = first.start;
        int end = first.end;
        while (!pq.isEmpty()) {
            Interval i = pq.poll();
            if (i.start <= end) {
                end = Math.max(i.end, end);
            } else {
                res.add(new Interval(start, end));
                start = i.start;
                end = i.end;
            }
        }
        res.add(new Interval(start, end)); // post processing
        return res;
    }
}
