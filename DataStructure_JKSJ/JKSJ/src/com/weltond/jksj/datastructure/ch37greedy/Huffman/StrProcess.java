package com.weltond.jksj.datastructure.ch37greedy.Huffman;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weltond
 * @project JKSJ
 * @date 1/24/2019
 */
public class StrProcess {
    public static Map<Character, Integer> countCharset(String str) {
        if (null != str && str.length() > 0) {
            Map<Character, Integer> result = new HashMap<>();
            char[] strChars = str.toCharArray();

            Integer value = null;
            for (int i = 0; i < strChars.length; ++i) {
                value = result.get(strChars[i]);

                if (value == null) {
                    result.put(strChars[i], 1);
                } else {
                    result.put(strChars[i], value + 1);
                }
            }

            return result;
        }
        return null;
    }
}
