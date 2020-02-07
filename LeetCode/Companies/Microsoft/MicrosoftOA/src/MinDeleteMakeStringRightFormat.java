/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * String s only has "A" and "B", return min to make s in format "A...AB...B" by deleting some letters.
 *
 * "BAAABAB"
 * 2
 * "AAABB"
 *
 * "BBABAA"
 * 3
 * "AAA" or "BBB"
 *
 * "AABBB"
 * 0
 */
public class MinDeleteMakeStringRightFormat {
    /**
     * Idea is using rightA as number of A after index i (inclusive)
     *               leftB as number of B before index i (exclusive)
     *  res would be number of ra + lb as they have to be deleted to make S right format
     * @param s
     * @return
     */
    public int minDelete(String s) {
        int ra = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') ra++;
        }
        int lb = 0, res = Integer.MAX_VALUE;
        for (char c : s.toCharArray()) {
            if (c == 'A') ra--;
            else lb++;
            res = Math.min(res, lb + ra);
        }

        return res;
    }
}
