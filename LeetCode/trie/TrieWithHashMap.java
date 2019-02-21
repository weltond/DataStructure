package com.weltond.trie;

import java.util.Map;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/21/2019
 */
public class TrieWithHashMap {
    static class TrieNode {
        boolean isEndOfWord;

        Map<Character, TrieNode> map;
    }

    static void insert(TrieNode root, String key) {
        if (root == null) {
            root = new TrieNode();
        }

        TrieNode dummy = root;

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);

            // make a new node if there is no path
            if (!dummy.map.containsKey(c)) {
                dummy.map.put(c, new TrieNode());
            }

            dummy = dummy.map.get(c);
        }

        dummy.isEndOfWord = true;
    }

    static boolean search(TrieNode root, String key) {
        if (root == null) return false;

        TrieNode dummy = root;

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!dummy.map.containsKey(c)) {
                return false;
            }

            dummy = dummy.map.get(c);
        }

        return dummy != null && dummy.isEndOfWord;
    }

    public static void test() {
        Trie.test();
        /**
         * Output:
         *
         * the --- Present in trie
         * these --- Not present in trie
         * their --- Present in trie
         * thaw --- Not present in trie
         * Remove "there" :
         * there --- Not present in trie
         * the --- Present in trie
         * their --- Present in trie
         */
    }
}
