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
    
    // Idea: Sort interval by start. Then we take the first interval and 
    //       compare its end with the next interval start.
    //       As long as overlap, we update the end to be the max end of the overlapping intervals. Once find non-overlap, update res.
    // O(nlogn)
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
