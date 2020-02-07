import java.util.List;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 */
public class MaxLengthOfConcatenatedStringWithUniqueChars {
    int len = 0, res = 0;
    public int maxLength(List<String> arr) {
        len = arr.size();

        dfs(arr, 0, "");

        return res;
    }

    private void dfs(List<String> arr, int lvl, String path) {
        // if (lvl == len) {
        //     res = Math.max(res, path.length());
        //     return;
        // }

        //if (!isUnique(path)) return;

        for (int i = lvl; i < len; i++) {
            String s = arr.get(i) + path;
            if (!isUnique(s)) continue;

            res = Math.max(res, s.length());
            dfs(arr, i + 1, s);
        }
    }

    private boolean isUnique(String s) {
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            if (arr[c - 'a'] != 0) return false;
            arr[c - 'a']++;
        }
        return true;
    }
}
