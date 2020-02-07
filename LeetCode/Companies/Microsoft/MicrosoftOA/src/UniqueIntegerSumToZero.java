/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
 */
public class UniqueIntegerSumToZero {
    public int[] sumZero(int n) {
        int sum = 0;
        int[] res = new int[n];
        for (int i = 0; i < n - 1; i++) {
            res[i] = i + 1;
            sum += res[i];
        }

        res[n - 1] = -sum;
        return res;

    }
}
