/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Return the maximum number of leters 'a' that can be inserted into S(include start and end) so that the resulting
 * string doesn't contain 3 consecutive letter 'a'. If already exists, return -1;
 *
 * "aabab"
 * 3
 * "aabaabaa"
 *
 * "aa"
 * 0
 */
public class MaxInsertMakeStringNo3ConsecutiveA {
    public int maxA(String s ) {
        int cnt = 0, res = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                cnt++;
            } else {
                res += 2 - cnt;
                cnt = 0;
            }
            if (cnt == 3) return -1;
        }

        if (s.charAt(s.length() - 1) != 'a') res += 2;
        else res += 2 - cnt;

        return res;
    }
}
