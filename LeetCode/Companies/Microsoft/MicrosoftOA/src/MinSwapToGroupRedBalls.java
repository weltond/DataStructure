/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * N balls. Given String S of length N built from "R" and "W", return minimum number of swaps needed to arrange all the
 * red balls into a consistent segment. If result exceeds 10^9, return -1/
 *
 * Input: "RRRWRR"
 * Output: 2
 *
 *
 * "WRRWWR"
 * 2
 * "WRRRWW"
 */
public class MinSwapToGroupRedBalls {

    /**
     * Idea is that min swap for each R is the distance to mid. minus the number of R's between them.
     * @param s
     * @return
     */
    public int minSwap(String s) {
        int red = 0;
        for (char c : s.toCharArray()) {
            if (c == 'R') red++;
        }
        int i = 0, j = s.length() - 1, res = 0;

        while (i < j) {
            if (s.charAt(i) == 'R' && s.charAt(j) == 'R') {
                red -= 2;
                res += j - i - 1 - red;
                i++;
                j--;
            } else if (s.charAt(i) != 'R') {
                i++;
            } else {
                j--;
            }
        }

        return res;
    }
}
