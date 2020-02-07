/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 *  Given a string S consisting of N letters a and b. In one move you can replace one letter by the other (a by b or b by a).
 *
 * Write a function solution that given such a string S, returns the minimum number of moves required to obtain a string containing no instances of three identical consecutive letters.
 * "baaaaa" return 1. "baabaa"
 * "baabab" return 0.
 */

public class MinSwapWithoutThreeSameLetters {
    /**
     * Wherever there is 3 identical letters, it needs one replacement (aaa => aba, bbb => bab);
     * 4 identical letters, it needs one replacemment (aaaa => abaa, bbbb => babb);
     * 5 needs one replacemment (aaaaa => aabaa, bbbbb => bbabb);
     * 6 needs two replacement (aaaaaa => aabbaa, bbbbbb => bbaabb); ...
     *
     * This means for every 3 identical consecutive letters, we need one replacement. Just find how many 3 letters it has.
     * @param a
     * @return
     */
    public int minMoves(String a) {
        int as = 0, bs = 0, res = 0;
        for (int i = 0, len = a.length(); i < len; i++) {
            char c = a.charAt(i);
            if (c == 'a') {
                as++;
                bs = 0;
            } else {
                bs++;
                as = 0;
            }
            if (as == 3 || bs == 3) {
                as = 0;
                bs = 0;
                res++;
            }
        }
        return res;
    }
}
