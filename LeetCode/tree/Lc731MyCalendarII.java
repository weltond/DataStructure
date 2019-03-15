// https://leetcode.com/problems/my-calendar-ii/

// ========= Method 2: TreeMap =========
// 457ms
// Use of TreeMap will support ordered intervals and logn insert / delete
class MyCalendarTwo {
    TreeMap<Integer, Integer> delta;  // <start, +1> or <end, -1>
    public MyCalendarTwo() {
        delta = new TreeMap();
    }
    
    public boolean book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0;
        for (Map.Entry<Integer, Integer> del: delta.entrySet()) {
            int d = del.getValue();
            int k = del.getKey();
            // the key is in order.
            // If there are triple booking, their start time must be greater than min(end1,end2,end3).
            active += d;
            if (active >= 3) {
                // reset the start and end
                delta.put(start, delta.get(start) - 1);
                delta.put(end, delta.get(end) + 1);
                if (delta.get(start) == 0){
                    delta.remove(start);
                }
                if (delta.get(end) == 0) {
                    delta.remove(end);
                }   
            
                return false;
            }
        }
        return true;
    }
    
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
// ======== Method 1: Brute Force ==========
// 127ms
// Use two lists that store all intervals and two-conflicted intervals.
// When the third interval comes, we first compare with two-conflicted interval list
// If conflicts, return false directly. Otherwise compare with all interval to see if there is any conflict.
class MyCalendarTwo {
    List<Interval> all;
    List<Interval> conflicts;
    public MyCalendarTwo() {
        all = new ArrayList();
        conflicts = new ArrayList();
    }
    
    // As to (s1, e1) and (s2, e2)
    // the conflict situation is: e1 > s2 && e2 > s1
    // non-conflict situation is: e1 <= s2 || e2 <= s1
    public boolean book(int start, int end) {
        // check if there is triple conflict
        for (Interval conflict : conflicts) {
            if (conflict.end > start && end > conflict.start) 
                return false; // return false if this one conflict with double-booking
        }
        
        // check if there is any double conflict
        for (Interval i : all) {
            // add to double-conflict arraylist if there is a conflict
            // if conflict, add the conflict part to list. NOT the whole interval
            if (i.end > start && end > i.start) {
                conflicts.add(new Interval(Math.max(i.start, start), Math.min(i.end, end)));
            }
        }
        
        // add to the whole interval list
        Interval t = new Interval(start, end);
        all.add(t);
        
        return true;
    }
    
    class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start; this.end = end;
        }
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
