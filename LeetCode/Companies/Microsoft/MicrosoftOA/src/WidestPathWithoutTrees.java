import java.util.Arrays;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Widest possible vertical path such that there is no tree on it. The path must be built somewhere between a leftmost
 * and a rightmost tree.
 *
 * X=[5,5,5,7,7,7],Y=[3,4,5,1,3,7]
 * 2
 */
public class WidestPathWithoutTrees {
    /**
     * Y is a misleading parameter
     * @param x
     * @param y
     * @return
     */
    public int solution(int[] x, int y) {
        Arrays.sort(x);
        int res = 0;
        for (int i = 1; i < x.length; i++) {
            res = Math.max(res, x[i] - x[i - 1]);
        }
        return res;
    }
}
