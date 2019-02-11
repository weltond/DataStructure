package com.weltond.linkedlist;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/11/2019
 */
public class MergeSort {
    Node head = null;

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    Node sortedMerge(Node a, Node b) {
        Node res = null;

        if (a == null) return b;
        if (b == null) return a;

        if (a.val <= b.val) {
            res = a;
            res.next = sortedMerge(a.next, b);
        } else {
            res = b;
            res.next = sortedMerge(a, b.next);
        }
        return res;
    }

    Node sort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;

        // set the next of middle node to null
        middle.next = null;

        // apple merge sort on left list
        Node left = sort(head);
        // apply merge sort on right list
        Node right = sort(nextOfMiddle);

        // merge the left and right list
        Node sortedList = sortedMerge(left, right);
        return sortedList;
    }

    // Utility function to get the middle of the Linked List
    Node getMiddle(Node head) {
        if (head == null) return head;

        Node fast = head.next;
        Node slow = head;

        // Move fast by two and slow by one
        // Finally slow will point to the middle node
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    void push(int newData) {
        Node newN = new Node(newData);

        // link the old list off the new node
        newN.next = head;

        // move the head to point to the new node
        head = newN;
    }

    // utility func to print
    void printList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void test() {
        MergeSort li = new MergeSort();

        /*
         * Let us create a unsorted linked lists to test the functions Created
         * lists shall be a: 2->3->20->5->10->15
         */
        li.push(15);
        li.push(10);
        li.push(5);
        li.push(20);
        li.push(3);
        li.push(2);
        System.out.println("Linked List without sorting is :");
        li.printList(li.head);

        // Apply merge Sort
        li.head = li.sort(li.head);
        System.out.print("\n Sorted Linked List is: \n");
        li.printList(li.head);
    }
}

