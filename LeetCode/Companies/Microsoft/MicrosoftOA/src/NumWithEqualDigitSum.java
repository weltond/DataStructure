import java.util.HashMap;
import java.util.Map;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Given array of N integers, return the maximum sum of two numbers whose digits add up to an equal sum. If not exists,
 *  return -1.
 *
 *  [51,71,17,42]. return 93. (51,42) (17,71)
 */
public class NumWithEqualDigitSum {
    public int computeDigitSum(int n) {
        n = Math.abs(n);
        int res = 0;
        while (n != 0) {
            res += n % 10;
            n /= 10;
        }

        return res;
    }

    /**
     * O(Nlogk), O(N)
     * @param a
     * @return
     */
    private int maxSum(int[] a) {
        if (a == null || a.length <= 1) return -1;
        Map<Integer, Integer> map = new HashMap<>();    // digitSum, max element
        int res = -1;
        for (int i = 0; i < a.length; i++) {
            int digitSum = computeDigitSum(a[i]);
            if (!map.containsKey(digitSum)) {
                map.put(digitSum, a[i]);
            } else {
                int pre = map.get(digitSum);
                res = Math.max(res, pre + a[i]);
                map.put(digitSum, Math.max(pre, a[i]));
            }
        }

        return res;
    }
}
