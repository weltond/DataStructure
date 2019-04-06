// https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/


class Solution {
  /**
    Case 1: Empty list -> We just make the node points to itself.
    Case 2: insertVal is greater than maxVal or smaller than minVal.
    Case 3: insertVal is smaller than maxVal and greater than minVal
  */
  public Node insert(Node head, int insertVal) {
    // case 1
    if (head == null) {
      head = new Node(insertVal);
      head.next = head;
      return head;
    }
    
    Node pre = head, cur = pre.next;
    
    while (cur != null) {
      // Case 2
      if (pre.val > cur.val && (pre.val <= insertVal || cur.val >= insertVal) break;  
      // Case 3
      if (pre.val <= insertVal && cur.val >= insertVal) break;  
      
      pre = cur;
      cur = cur.next;
    }
    
    // connect them
    Node n = new Node(insertVal);
    prev.next = n;
    n.next = cur;

    return head;
  }
}
