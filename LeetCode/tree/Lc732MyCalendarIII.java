// https://leetcode.com/problems/my-calendar-iii/

class MyCalendarThree {
    TreeMap<Integer, Integer> delta;    // <start/end, +1/-1>
    public MyCalendarThree() {
        delta = new TreeMap();
    }
    
    public int book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);
        
        int active = 0, ans = 0;
        
        // Use entrySet() causes : 135ms
        // Use values() causes: 192ms
        for (Map.Entry<Integer, Integer> entry : delta.entrySet()) {
            active += entry.getValue();
            ans = Math.max(ans, active);
        }
        
        return ans;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
