package com.weltond.trie;

/**
 * https://www.geeksforgeeks.org/sorting-array-strings-words-using-trie/
 *
 * @author weltond
 * @project LeetCode
 * @date 2/21/2019
 */
public class SortArrayOfStrings {
    static final int ALPHABET_SIZE = 26;
    static class TrieNode{
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isEndOfWord;
        int index;  // index in string array

        public TrieNode() {
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
            index = -1;
            isEndOfWord = false;
        }
    }

    static void insert(TrieNode root, String key, int index) {
        if (root == null) return;

        TrieNode dummy = root;
        for (int i = 0; i < key.length(); i++) {
            int pos = key.charAt(i) - 'a';
            if (dummy.children[pos] == null) {
                dummy.children[pos] = new TrieNode();
            }
            dummy = dummy.children[pos];
        }
        dummy.isEndOfWord = true;
        // save its index in string array
        dummy.index = index;
    }
    /**
     * To print the string in alphabetical order:
     *  1. Insert in the trie
     *  2. Perform pre-order traversal to print in alphabetical order
     */
    public static void preOrder(TrieNode root, String[] words) {
        if (root == null) return;

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (root.children[i] != null) {
                // if end of word then print key
                if (root.children[i].isEndOfWord) {
                    System.out.println(words[root.children[i].index]);
                }

                preOrder(root.children[i], words);
            }
        }
    }

    static void printSorted(String[] arr) {
        TrieNode root = new TrieNode();

        int i = 0;
        for (String s : arr) {
            insert(root, s, i++);
        }

        preOrder(root, arr);
        System.out.println("===========");
        method2(root, new StringBuilder());
    }


    static boolean isLeaf(TrieNode root) {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (root.children[i] != null) {
                return false;
            }
        }
        return true;
    }

    static void method2(TrieNode root, StringBuilder sb) {
        if (root.isEndOfWord) {
            System.out.println(sb);
        }

        if (isLeaf(root)) {
            return;
        }

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (root.children[i] != null) {
                sb.append((char)(i + 'a'));
                method2(root.children[i], sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void test() {
        String[] arr = {"nhy", "hyn", "nhyyy", "abc", "xy", "bcd", "nhy", "nhywmc"};

        printSorted(arr);
    }

}
