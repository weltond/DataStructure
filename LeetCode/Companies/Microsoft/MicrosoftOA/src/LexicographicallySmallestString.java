/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Lexicographically smallest string formed by removing at most one character
 *
 * Input: "abczd"
 * Output: "abcd"
 */
public class LexicographicallySmallestString {
    public static String smallestString(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                break;
            }
        }

        return sb.deleteCharAt(i).toString();
    }
}
