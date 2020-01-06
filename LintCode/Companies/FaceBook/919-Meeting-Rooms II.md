## [919. Meeting Rooms II](https://www.lintcode.com/problem/meeting-rooms-ii/description?_from=ladder&&fromId=130)

Given an array of meeting time intervals consisting of start and end times `[[s1,e1],[s2,e2],...]` (si < ei), find the minimum number of conference rooms required.

Example1

```
Input: intervals = [(0,30),(5,10),(15,20)]
Output: 2
Explanation:
We need two meeting rooms
room1: (0,30)
room2: (5,10),(15,20)
```

Example2

```
Input: intervals = [(2,7)]
Output: 1
Explanation: 
Only need one meeting room
```

## Answer
### Method 1 - PriorityQueue - :rabbit: 244ms (70.60%)

```java
/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return 0;
        }
        
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        PriorityQueue<Integer> pq = new PriorityQueue();    // offer end into pq
        pq.offer(intervals.get(0).end);
        
        for (int i = 1, len = intervals.size(); i < len; i++) {
            Interval cur = intervals.get(i);
            if (pq.peek() < cur.start) {
                pq.poll();
            }
            pq.offer(cur.end);
        }
        
        return pq.size();
    }
}
```
