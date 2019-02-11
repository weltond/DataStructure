package com.weltond.linkedlist;

import java.util.*;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/11/2019
 */
public class MergeKSortedList {

    /**
     * Method 4: Divide and Conquer
     * Idea:
     *      1. Pair up k lists and merge each pair
     *      2. After the first pairing, k lists are merged into k/2 lists with average 2N/k length, then k/4, k/8 and so on.
     *      3. Repeat the procedure until we get the final sorted linked list
     * Time = O(nlogk), Space = O(k)
     * @param lists input
     * @return output
     */
    public ListNode m4(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        int len = lists.length;
        int interval = 1;

        while (interval < len) {
            for (int i = 0; i < len - interval; i += interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }

        return lists[0];
    }

    // ==== Using recursion for method 4 ====
    public ListNode m42(ListNode[] lists, int left, int right) {
        if (lists == null || lists.length == 0) return null;

        helper(lists, 0, lists.length - 1);
        return lists[0];
    }

    public void helper(ListNode[] lists, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        helper(lists, left, mid);
        helper(lists, mid + 1, right);

        lists[left] = mergeTwoLists(lists[left], lists[mid + 1]);
    }
    // =============== end ==================

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;

        return dummy.next;
    }

    /**
     * Method 3: Priority Queue
     * Idea:
     *      1. Based on solution 2
     *      2. Use priorityQueue to get the smallest number every time
     *      3. PriorityQueue can return min/max number in O(logn) time
     *
     * Time = O(nlogk), Space = O(k)
     * @param lists input
     * @return output
     */
    public ListNode m3(ListNode[] lists) {
        class MyComparator implements Comparator<ListNode> {

            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        }

        if (lists == null || lists.length == 0) return null;

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, new MyComparator());

        //insert the head of all lists
        for (ListNode head : lists) {
            if (head != null) {
                heap.add(head);
            }
        }

        while (!heap.isEmpty()) {
            ListNode tmp = heap.poll();
            cur.next = tmp;
            cur = cur.next;
            if (tmp.next != null) {
                heap.add(tmp.next);
            }
        }

        return dummy.next;
    }

    /**
     * Method 2: Comparison / Pointers
     * Idea:
     *      1. Compare every k nodes (head of every linkedlist) and get the node with the smallest value
     *      2. Extend the final sorted linked list with the selected nodes.
     *
     * Time = O(nk), Space = O(1)
     * @param lists input
     * @return output
     */
    public ListNode m2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        ListNode tmp = null;    // used for checking whether all list nodes are traversed.

        do {
            tmp = findMinAndMove(lists);
            cur.next = tmp;
            tmp = tmp.next;
        } while (tmp != null);

        return dummy.next;

    }
    // find the smallest node in all of linked lists head/pointer
    private ListNode findMinAndMove(ListNode[] lists) {
        int min = Integer.MAX_VALUE;
        int idx = -1;

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) continue;

            if (lists[i].val < min) {
                min = lists[i].val;
                idx = i;
            }
        }

        ListNode ret = null;
        if (idx != -1) {
            ret = lists[idx];
            lists[idx] = lists[idx].next;
        }

        return ret;

    }

    /**
     * Method 1: Brute Force
     * Time = O(nklogk), Space = O(nk)
     *
     * @param lists input
     * @return output
     */
    public ListNode m1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        List<Integer> allNode = new ArrayList<>();
        for (ListNode node : lists) {
            while (node != null) {
                allNode.add(node.val);
                node = node.next;
            }
        }

        Collections.sort(allNode);

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        for (Integer nodeVal : allNode) {
            cur.next = new ListNode(nodeVal);

            cur = cur.next;
        }
        return dummy.next;
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public ListNode push(int data) {
        ListNode n = new ListNode(data);
        n.next = null;
        return n;
    }
    public static void test() {
        int k = 3;  // No. of linked lists
        int n = 4;  // No. of elements in each lsit

        ListNode[] arr = new ListNode[k];

        arr[0] = new ListNode(1);
        arr[0].next = new ListNode(3);
        arr[0].next.next = new ListNode(5);
        arr[0].next.next.next = new ListNode(7);

        arr[1] = new ListNode(2);
        arr[1].next = new ListNode(4);
        arr[1].next.next = new ListNode(6);
        arr[1].next.next.next = new ListNode(8);

        arr[2] = new ListNode(0);
        arr[2].next = new ListNode(9);
        arr[2].next.next = new ListNode(10);
        arr[2].next.next.next = new ListNode(11);

        MergeKSortedList m = new MergeKSortedList();
        //ListNode head = m.m4(arr);
        ListNode h2 = m.m42(arr, 0, arr.length - 1);

        //printList(head);
        printList(h2);
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
