package com.weltond.trie;

/** https://www.geeksforgeeks.org/trie-insert-and-search/
 *
 * @author weltond
 * @project LeetCode
 * @date 2/20/2019
 */
public class Trie {
    // Alphabet size (# of symbols)
    static final int ALPHABET_SIZE = 26;

    // trie node
    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // is true if the node represents end of a word
        boolean isEndOfWord;

        // we can also store the String word here.
        String word;

        public TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
            word = null;
        }
    }

    static TrieNode root;

    // If not present, inserts key into Trie
    // If the key is prefix of Trie node, just marks leaf node
    static void insert(String key) {
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null) {
                pCrawl.children[index] = new TrieNode();
            }
            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isEndOfWord = true;

        pCrawl.word = key;
    }

    // Return true if key presents in Trie, else false
    static boolean search(String key) {
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null) {
                return false;
            }
            pCrawl = pCrawl.children[index];
        }

        return pCrawl != null && pCrawl.isEndOfWord;
    }

    // return true if root has no children, else false
    static boolean isEmpty(TrieNode root) {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (root.children[i] == null) {
                return false;
            }
        }
        return true;
    }

    // Recursive function to delete a key from given Trie
    static TrieNode delete(TrieNode root, String key, int depth) {
        // if tree is empty
        if (root == null) return null;


        // If last character of key is being processed
        if (depth == key.length()) {
            // This node is no more end of word after removal of given key
            if (root.isEndOfWord) {
                root.isEndOfWord = false;
                root.word = null;
            }

            // If given is not prefix of any other word
            if (isEmpty(root)) {
                root = null;
            }
            return root;
        }

        // If not last character, recur for the child obtained using ASCII value
        int index = key.charAt(depth) - 'a';
        root.children[index] = delete(root.children[index], key, depth + 1);

        // If root deos not have any child (its only child got deleted),
        // and it is not end of another word
        if (isEmpty(root) && !root.isEndOfWord) {
            root = null;
        }

        return root;
    }

    public static void test() {
        // Input keys (use only lower case)
        String[] keys = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String[] output = {"Not present in trie", "Present in trie"};

        root = new TrieNode();

        // Construct Trie
        int i;
        for (i = 0; i < keys.length; i++) {
            insert(keys[i]);
        }

        // Search for different keys
        if(search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if(search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if(search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);

        // remove 'the'
        System.out.println("Remove \"there\" :");
        delete(root, "there", 0);
        if(search("there") == true)
            System.out.println("there --- " + output[1]);
        else System.out.println("there --- " + output[0]);

        if(search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);


        /** Output:
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
