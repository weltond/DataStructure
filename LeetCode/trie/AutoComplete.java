package com.weltond.trie;

/** https://www.geeksforgeeks.org/auto-complete-feature-using-trie/
 *
 * @author weltond
 * @project LeetCode
 * @date 2/21/2019
 */
public class AutoComplete {
    static final int ALPHABET_SIZE = 26;

    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        boolean isEndOfWord;

        String word;

        public TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
            word = null;
        }
    }

    static void insert(TrieNode root, String key) {
        TrieNode dummy = root;
        for (int level = 0; level < key.length(); level++) {
            int index = key.charAt(level) - 'a';
            if (dummy.children[index] == null) {
                dummy.children[index] = new TrieNode();
            }
            dummy = dummy.children[index];
        }

        dummy.isEndOfWord = true;
        dummy.word = key;
    }

    static boolean search(TrieNode root, String key) {
        TrieNode dummy = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (dummy.children[index] == null) {
                return false;
            }
            dummy = dummy.children[index];
        }
        return (dummy != null && dummy.isEndOfWord);
    }

    static boolean isLeaf(TrieNode root) {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (root.children[i] != null)
                return false;
        }
        return true;
    }

    /**
     * Recursive function to print auto-suggestions for given node.
     * @param root
     * @param currPrefix
     */
    static void suggestionRec(TrieNode root, StringBuilder currPrefix) {
        // found a string in Trie with the given prefix
        if (root.isEndOfWord) {
            System.out.println(currPrefix);
        }

        // No children
        if (isLeaf(root)) {
            return;
        }

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (root.children[i] != null) {
                // append current character to currentPrefix
                currPrefix.append((char) (i + 'a'));

                // recur over the rest
                suggestionRec(root.children[i], currPrefix);

                // back tracking
                currPrefix.deleteCharAt(currPrefix.length() - 1);
            }
        }
    }

    static int printAutoSuggestions(TrieNode root, String query) {
        TrieNode dummy = root;

        // Check if prefix is present and find the node (of last level) with last char
        // of given string
        int level;
        int n = query.length();

        for (level = 0; level < query.length(); level++) {
            int index = query.charAt(level) - 'a';
            // if no string in the Trie has same prefix
            if (dummy.children[index] == null) return 0;

            dummy = dummy.children[index];
        }

        // if prefix is present as a word
        boolean isWord = dummy.isEndOfWord;

        // if prefix is last node of tree (has no children)
        boolean isLast = isLeaf(dummy);

        // If prefix is present as a word, but there is no subtree after it
        if (isWord && isLast) {
            System.out.println(query);
            return -1;
        }

        // if there are nodes below last matching character
        StringBuilder prefix = new StringBuilder(query);
        suggestionRec(dummy, prefix);
        return 1;
    }

    public static void test() {
        TrieNode root = new TrieNode();
        insert(root, "hello");
        insert(root, "dog");
        insert(root, "hell");
        insert(root, "cat");
        insert(root, "a");
        insert(root, "hel");
        insert(root, "help");
        insert(root, "helps");
        insert(root, "helping");

        int res1 = printAutoSuggestions(root, "hel");
        if (res1 == -1) System.out.println("No other string found with prefix 'hel'");
        else if (res1 == 0) System.out.println("No string found with prefix 'hel'");

        int res2 = printAutoSuggestions(root, "b");
        if (res2 == -1) System.out.println("No other string found with prefix 'b'");
        else if (res2 == 0) System.out.println("No string found with prefix 'b'");

        int res3 = printAutoSuggestions(root, "dog");
        if (res3 == -1) System.out.println("No other string found with prefix 'dog'");
        else if (res3 == 0) System.out.println("No string found with prefix 'dog'");

        /**
         * output:
         *
         * hel
         * hell
         * hello
         * help
         * helping
         * helps
         * No string found with prefix 'b'
         * dog
         * No other string found with prefix 'dog'
         */
    }
}
