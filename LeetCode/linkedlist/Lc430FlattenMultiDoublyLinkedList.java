// https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/

/**
Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    // =========== Method 1 ===========
    // 0ms
    public Node flatten(Node head) {
        Node cur = head;
        helper(head);
        return head;
    }
    
    private Node helper(Node head) {
        Node cur = head;
        Node prev = null;
        while (cur != null) {
            prev = cur;
            if (cur.child != null) {
                Node next = cur.next;
                cur.next = cur.child;
                cur.child.prev = cur;   // connect cur's child's prev to cur
                
                Node last = helper(cur.child);  // get child's last node
                
                // find the last node of new linkedlist.
                while (last.next != null) {
                    last = last.next;
                }
                
                last.next = next;   // connect child's last to cur's next
                
                if (next != null) next.prev = last;   // connect cur's prev to child's last
                cur.child = null;   // set child to null
                cur = next;
            } else {
                cur = cur.next;
            }
        }
        return prev;
    }
}
