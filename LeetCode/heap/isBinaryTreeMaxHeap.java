package com.weltond.heap;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/13/2019
 */
public class isBinaryTreeMaxHeap {
    static class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
            left = right = null;
        }
    }

    // this function counts the number of nodes in a binary tree
    int countNodes(Node root) {
        if (root == null) return 0;
        return (1 + countNodes(root.left) + countNodes(root.right));
    }

    // this function checks if the binary tree is complete or not
    boolean isCompleteUtil(Node root, int index, int numerNodes) {
        if (root == null) return true;

        // if index assigned to current node is more than nmber of nodes in tree
        // then it's not complete
        if (index >= numerNodes) return false;

        return isCompleteUtil(root.left, 2 * index + 1, numerNodes) &&
                isCompleteUtil(root.right, 2 * index + 2, numerNodes);
    }

    // this function checks the heap property in the tree
    boolean isHeapUtil(Node root) {
        // base case: single node satisfies property
        if (root.left == null && root.right == null) return true;

        // node will be in second last level
        if (root.right == null) {
            // check heap property at Node
            // No recursive call, because no need to check last
            return root.val >= root.left.val;
        } else {
            // check heap property at Node and
            // Recursive check heap property at left and right subtree
            if (root.val >= root.left.val && root.val >= root.right.val)
                return isHeapUtil(root.left) && isHeapUtil(root.right);
            else
                return false;
        }
    }

    boolean isHeap(Node root) {
        if (root == null) return true;

        int nodeCnt = countNodes(root);

        return isCompleteUtil(root, 0, nodeCnt) && isHeapUtil(root);
    }

    // ==== below my code ====
    public static boolean isMaxHeap(Node root) {
        if (root == null) return true;

        boolean flag = true;
        if (root.left != null) {
            flag = flag & (root.val > root.left.val);
        }
        if (root.right != null) {
            flag = flag & (root.val > root.right.val);
        }

        return isMaxHeap(root.left) && isMaxHeap(root.right) && flag;
    }

    public static void test() {
        Node root = new Node(10);
        root.left = new Node(9);
        root.right = new Node(8);
        root.left.left = new Node(7);
        root.left.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(4);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(2);
        root.left.right.left = new Node(1);

        if (isBinaryTreeMaxHeap.isMaxHeap(root)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
