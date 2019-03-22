package com.weltond.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weltond
 * @project LeetCode
 * @date 3/22/2019
 */
public class Lc340LongestSubstringWithAtMostKDistinctChar {
    public static void main(String[] args) {
        String s = "eceeba";
        System.out.println(solution(s, 3));
    }

    public static int solution(String s, int k) {
        if (s == null) return 0;

        int res = 0, left = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            // 1. add cur char to map
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);

            // 2. if map size greater than k. remove left val and update
            while (map.size() > k) {
                char leftVal = s.charAt(left);
                int freq = map.get(leftVal);

                if (freq == 1) map.remove(leftVal);
                else map.put(leftVal, freq - 1);

                left += 1;
            }

            res = Math.max(res, i - left + 1);
        }

        return res;
    }
}
