import java.util.Arrays;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Alexa is given n piles of equal or unequal heights. In one step, Alexa can remove any number of boxes from the pile which has the maximum height and try to make it equal to the one which is just lower than the maximum height of the stack. Determine the minimum number of steps required to make all of the piles equal in height.
 *
 * Input: piles = [5, 2, 1]
 * Output: 3
 * Explanation:
 * Step 1: reducing 5 -> 2 [2, 2, 1]
 * Step 2: reducing 2 -> 1 [2, 1, 1]
 * Step 3: reducing 2 -> 1 [1, 1, 1]
 * So final number of steps required is 3.
 */
public class MinStepToMakePilesEqualHeight {
    /**
     * O(NlogN)
     *
     * For piles = [5, 2, 1], 5 needs 2 steps to come to 1(5 -> 2 -> 1) and 2 needs 1 step to 1(2 -> 1) and 1 for 0 step. We just need to sort the array and record how many different numbers appeared before and sum them up.
     *
     * @param piles
     * @return
     */
    public int minStep(int[] piles) {
        if (piles.length <= 1) return 0;
        Arrays.sort(piles);
        int res = 0, distinct = 0;
        for (int i = 1; i < piles.length; i++) {
            if (piles[i] != piles[i - 1]) {
                distinct++;
            }
            res += distinct;
        }

        return res;
    }
}