import java.util.Arrays;
import java.util.List;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 */
public class MeetingRoomII {
    public int minMeetingRooms(List<Interval> intervals) {
        // Write your code here
        if (intervals == null || intervals.size() == 0) return 0;

        int len = intervals.size();
        int[] start = new int[len];
        int[] end = new int[len];
        int t = 0;
        for (Interval i : intervals) {
            start[t] = i.start;
            end[t++] = i.end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int pre = 0, res = 0;

        for (int i = 0; i < len; i++) {
            res++;
            if (start[i] > end[pre]) {
                res--;
                pre++;
            }
        }

        return res;
    }
}

class Interval{
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}