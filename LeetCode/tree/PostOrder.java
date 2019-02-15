package com.weltond.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/** Steps for TWO STACK implementation
 *      1. Push root to first stack
 *      2. Loop while first stack is not empty
 *          2.1 Pop a node from first stack and push it to second stack
 *          2.2 Push left and right children of the popped node to first stack
 *      3. Print contents of second stack
 *
 *  Steps for ONE STACK implementation
 *      1. Create an empty stack
 *      2. Do the following while root is not null
 *          a. Push root's right child and then root to stack
 *          b. Set root as root's left child
 *      3. Pop an item from stack and set it as root
 *          a. If the popped item has a right child and the right child is at top of stack,
 *              then remove the right child from the stack, push the root back and set root as
 *              root's right child
 *          b. Else print root's data and set root as NULL
 *      4. Repeat step 2 and 3 while stack is not empty
 *
 * @author weltond
 * @project LeetCode
 * @date 2/14/2019
 */
public class PostOrder {
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    static LinkedList<Node> s1, s2;

    // Two stack
    static void postIter1(Node root) {
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();

        if (root == null) return;

        s1.offerFirst(root);

        while (!s1.isEmpty()) {
            Node tmp = s1.pollFirst();
            s2.offerFirst(tmp);

            if (tmp.left != null) {
                s1.offerFirst(tmp.left);
            }
            if (tmp.right != null) {
                s1.offerFirst(tmp.right);
            }
        }

        // print all elements
        while (!s2.isEmpty()) {
            System.out.print(s2.pollFirst().data + " ");
        }
    }

    // One Stack: like pre-order but right child is first
    static LinkedList<Node> s3;
    static ArrayList<Integer> res;
    static void postIter2(Node root) {
        s3 = new LinkedList<>();
        res = new ArrayList<>();
        if (root == null) return;

        while (!s3.isEmpty() || root != null) {
            while (root!= null) {
                res.add(root.data);
                s3.offerFirst(root);
                root = root.right;
            }
            root = s3.pollFirst();

            root = root.left;

        }

        Collections.reverse(res);

        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public static void test() {
        Node root = null;
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        postIter1(root);
        System.out.println();

        postIter2(root);
    }
}
