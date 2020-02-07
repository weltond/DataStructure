import java.util.HashSet;
import java.util.Set;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Given a string, what is the minimum number of adjacent swaps required to convert a string into a palindrome. If not possible, return -1.
 *
 * "mamad"
 *  3
 *
 *  "aabb"
 *  2
 *
 *  "ntiin"
 *  1
 */
public class MinSwapMakePalindrome {
    public boolean canFormPalindrome(char[] s) {
        Set<Character> set = new HashSet<>();
        for (char c : s) {
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }

        return set.size() < 2;
    }

    public void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public int minSwap(char[] s) {
        int res = 0;
        if (!canFormPalindrome(s)) return -1;
        int i = 0, j = s.length - 1;

        while (i < j) {
            int k = j;
            while (s[i] != s[k] && i != k) {
                k--;
            }
            if (s[i] == s[k] && i != k) {
                while (k < j) {
                    swap(s, k, k + 1);
                    res++;
                    k++;
                }
                i++;
                j--;
            }
            // consider "cbb"
            else {
                swap(s, i, i+1);
                res++;
            }
        }

        return res;
    }
}
