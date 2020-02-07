import java.util.*;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Given a string s consisting of n lowercase letters, you have to delete the minimum number of characters from s so that every letter in s appears a unique number of times. We only care about the occurrences of letters that appear at least once in result.
 *
 * Input: "eeeeffff"
 * Output: 1
 * Explanation:
 * We can delete one occurence of 'e' or one occurence of 'f'. Then one letter will occur four times and the other three times.
 *
 * Input: "aabbffddeaee"
 * Output: 6
 * Explanation:
 * For example, we can delete all occurences of 'e' and 'f' and one occurence of 'd' to obtain the word "aabbda".
 * Note that both 'e' and 'f' will occur zero times in the new word, but that's fine, since we only care about the letter that appear at least once.
 *
 * Input: "llll"
 * Output: 0
 * Explanation:
 * There is no need to delete any character.
 */
public class MinDeleteFreqOfLetterUnique {
    public int minDelete(String s) {
        if (s == null || s.length() < 1) return 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Integer> freq = new ArrayList<>(map.values());
        Collections.sort(freq, Collections.reverseOrder());

        int res = 0;
        Set<Integer> set = new HashSet();
        for (int f : freq) {
            if (!set.contains(f)) {
                set.add(f);
                continue;
            }

            while (set.contains(f)) {
                f--;
                res++;
            }

            if (f != 0) {
                set.add(f);
            }
        }

        return res;
    }
}