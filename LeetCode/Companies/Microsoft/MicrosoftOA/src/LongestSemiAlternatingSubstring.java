/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Semi-Alternating if a substring of S doesn't contain 3 identical consecutive chars.
 * Return the length of longest semi-alternating substring of S
 *
 * "baaabbabbb"
 * 7
 * "aabbabb"
 *
 * "babba"
 * 5
 *
 * "abaaaa"
 * 4
 */
public class LongestSemiAlternatingSubstring {
    public int getLongest(String s) {
        if (s == null || s.length() < 1) return 0;
        if (s.length() < 3) return s.length();

        int cnt = 1, l = 0, lastSeen = 0;
        int res = 0;

        for (int r = 1; r < s.length(); r++) {
            char c = s.charAt(r);
            if (s.charAt(r - 1) == c) cnt++;
            else {
                cnt = 1;
                lastSeen = r;
            }

            if (cnt > 2 && l < lastSeen) {
                l = lastSeen;
            }
            while (cnt > 2) {
                l++;
                cnt--;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
