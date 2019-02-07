class Solution {
    // class Course {
    //     int start, end;
    //     Course(int s, int e) {
    //         start = s;
    //         end = e;
    //     }
    // }
    public int scheduleCourse(int[][] courses) {
        // PriorityQueue<Course> pq = new PriorityQueue<>(courses.length, (o1, o2) -> o1.end -  o2.end);
        Arrays.sort(courses, (a, b) -> (a[1] - b[1]));
        int cnt = 0, res = 0;
        // for (int i = 0; i < courses.length; i++) {
        //     pq.offer(new Course(courses[i][0], courses[i][1]));
        // }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for (int[] cur: courses) {
            // Course cur = pq.poll();
            // System.out.println(cur.start);
            // res += cur.start;
            // if (res <= cur.end) {
            //     cnt++;
            // } else {
            //     break;
            // }
            
            if (res + cur[0] <= cur[1]) {
                pq.offer(cur[0]);
                res += cur[0];
            } else if (!pq.isEmpty() && pq.peek() > cur[0]) {
                res = res + cur[0] - pq.poll();
                pq.offer(cur[0]);
            }
        }
        return pq.size();
    }
}