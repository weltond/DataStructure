import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Write a function that, given an array A of N integers, returns the lagest integer K > 0 such that both values K and -K exisit in array A. If there is no such integer, the function should return 0.
 *
 * Input: [3, 2, -2, 5, -3]
 * Output: 3
 *
 */
public class LargestInteger {
    public int largestNum(int[] nums) {
        int res = 0;
        Set<Integer> set = new HashSet();
        for (int i : nums) {
            set.add(-i);
            if (set.contains(i)) {
                res = Math.max(res, Math.abs(i));
            }
        }
        return res;
    }
}
