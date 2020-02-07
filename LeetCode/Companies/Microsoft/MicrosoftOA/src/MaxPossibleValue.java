import java.util.Deque;
import java.util.HashSet;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Insert "5" digit inside the N. Return the max possible value.
 *
 * 268
 * 5268
 *
 * 670
 * 6750
 *
 * 0
 * 50
 *
 * -999
 * -5999
 */
public class MaxPossibleValue {
    public int maxPosValue(int n) {
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        if (n >= 0) {
            int idx = 0;
            while (idx < sb.length() && (sb.charAt(idx++) - '0') >= 5) {}
            sb.insert(idx, 5);
        } else {
            int idx = 0;
            while (idx < sb.length() && (sb.charAt(idx++) - '0') <= 5) {}
            sb.insert(idx, 5);
        }

        int val = Integer.parseInt(sb.toString());
        return val;
    }

}
