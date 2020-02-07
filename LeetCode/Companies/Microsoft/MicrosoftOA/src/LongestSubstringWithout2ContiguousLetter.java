/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Given a string s containing only a and b, find longest substring of s such that s does not contain more than two contiguous occurrences of a and b.
 *
 * Input: "aabbaaaaabb"
 * Output: "aabbaa"
 *
 * Input: "aabbaabbaabbaa"
 * Output: "aabbaabbaabbaa"
 */
public class LongestSubstringWithout2ContiguousLetter {
    public String validLongestSubstring(String s) {
        if (s.length() < 3) return s;
        int cur = 0, end = 1;
        char pre = s.charAt(0);
        int cnt = 1, maxLen = 1, start = 0;

        while (end < s.length()) {
            if (s.charAt(end) == pre) {
                cnt++;
                if (cnt == 2) {
                    if (end - cur + 1 > maxLen) {
                        maxLen = end - cur + 1;
                        start = cur;
                    }
                }else {
                    cur = end - 1;
                }
            } else {
                pre = s.charAt(end);
                cnt = 1;
                if (end - cur + 1 > maxLen) {
                    maxLen = end - cur + 1;
                    start = cur;
                }
            }
            end++;
        }

        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        LongestSubstringWithout2ContiguousLetter lsw = new LongestSubstringWithout2ContiguousLetter();
        String ret = lsw.validLongestSubstring("aaabaaa");
        System.out.println(ret);
        assert ret.equals("aabaa") : "wrong";
    }
}
