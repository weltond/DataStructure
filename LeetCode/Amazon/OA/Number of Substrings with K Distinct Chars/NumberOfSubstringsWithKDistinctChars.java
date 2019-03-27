package com.weltond.amazon;

import java.util.Arrays;

/**
 * //https://www.geeksforgeeks.org/count-number-of-substrings-with-exactly-k-distinct-characters/
 * @author weltond
 * @project LeetCode
 * @date 3/22/2019
 */
public class NumberOfSubstringsWithKDistinctChars {
    public static void main(String[] args) {
        String s = "abcbaa";
        int k = 3;
        System.out.println("Total substrings with exactly " +
                k + " distinct characters : "
                + solution(s, k));
    }

    public static int solution(String s, int k) {
        int res = 0, n = s.length();
        // To store count of characters from 'a' to 'z'
        int[] cnt = new int[26];

        for (int i = 0; i < n; i++) {
            int distCnt = 0;

            // Initializing cnt array with 0
            Arrays.fill(cnt, 0);

            // Consider all substrings between str[i..j]
            for (int j = i; j < n; j++) {
                // If this is a new character for this
                // substring, increment dist_count.
                if (cnt[s.charAt(j) - 'a'] == 0)
                    distCnt++;

                // Increment count of current character
                cnt[s.charAt(j) - 'a']++;

                // If distinct character count becomes k,
                // then increment result.
                if (distCnt == k)
                    res++;
            }

        }
        return res;
    }
}
