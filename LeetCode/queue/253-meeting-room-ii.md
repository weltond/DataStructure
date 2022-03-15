## 🔒[253. Meeting Room II](https://leetcode.com/problems/meeting-rooms-ii)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)


## Answers
### Method 1 - PQ - 9ms (65.68%)
Time: O(NlogN), Space: O(N)

```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        
        // min heap, store end time
        PriorityQueue<Integer> pq = new PriorityQueue();
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        pq.offer(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            // if the room due to free up the earliest is free, assign that room to this meeting
            if (intervals[i][0] >= pq.peek()) {
                pq.poll();
            }
            
            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            pq.offer(intervals[i][1]);
        }
        
        // the size of heap is the min rooms required for all meetings
        return pq.size();
    }
}
```
