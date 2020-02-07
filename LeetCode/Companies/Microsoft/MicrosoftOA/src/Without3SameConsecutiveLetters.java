/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Return a string with no instances of 3 identical consecutive letters, obtained from S by deleting the minimum
 * possible number of letters.
 *
 * "eedaaad"
 * "eedaad"
 *
 * "xxxtxxx"
 * "xxtxx"
 */
public class Without3SameConsecutiveLetters {
    public String getLongestString(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        int cnt = 1;
        for (int i = 1, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (c == s.charAt(i - 1)) {
                cnt++;
            } else {
                cnt = 1;
            }
            if (cnt < 3) sb.append(c);

        }
        return sb.toString();
    }
}
