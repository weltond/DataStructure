## [109. Convert Sorted List to BST](https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

```
Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
```

## Answer

### Method 2 - In-order - :rocket: 0ms 

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    private ListNode current;

    private int getListLength(ListNode head) {
        int size = 0;

        while (head != null) {
            size++;
            head = head.next;
        }

        return size;
    }

    public TreeNode sortedListToBST(ListNode head) {
        int size;

        current = head;
        size = getListLength(head);

        return sortedListToBSTHelper(size);
    }

    public TreeNode sortedListToBSTHelper(int size) {
        if (size <= 0) {
            return null;
        }

        TreeNode left = sortedListToBSTHelper(size / 2);
        TreeNode root = new TreeNode(current.val);
        current = current.next;
        TreeNode right = sortedListToBSTHelper(size - 1 - size / 2);

        root.left = left;
        root.right = right;

        return root;
    }
}
```

### Method 1 - Find Mid - :rocket: 0ms

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        // write your code here
        if (head == null) return null;
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        
        ListNode slow = head, fast = head, pre = null;
        
        // slow is mid, prev is the node before mid.
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // disconnect mid and its left
        pre.next = null;
        
        TreeNode root = new TreeNode(slow.val);
        
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        
        return root;
    }
}
```

### Old Post

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        
        ListNode mid = findMid(head);
        
        TreeNode root = new TreeNode(mid.val);
        
        // base case, one element
        if (mid == head) {
            return root;
        }
        
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        
        return root;
    }
    
    private ListNode findMid(ListNode head) {
        ListNode pre = null, slow = head, fast = head;
        
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        if (pre != null) pre.next = null;
        
        return slow;
    }
}
class Solution {
    // 1ms (100%)
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        
        ListNode node = head;
        ListNode slow = node;
        ListNode fast = node, prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
            if (fast != null) fast = fast.next;
        }
        ListNode mid = prev.next;
        
        ListNode next = mid.next;
        //System.out.println(head.val + ","+mid.val +","+next.val);
        int val = mid.val;
        
        prev.next = null;
        
        TreeNode root = new TreeNode(val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(next);
        
        return root;
    }
}

class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        
        // return rec(head);
        return inorder(head);
    }
    /*Method 2: Inorder simulation*/
    ListNode head;
    private TreeNode inorder(ListNode head) {
        if (head == null) return null;
        ListNode tmp = head;
        this.head = head;
        int size = 0;
        while (tmp != null) {
            tmp = tmp.next;
            size++;
        }
        return helper(0, size - 1);
    }
    
    private TreeNode helper(int start, int end) {
        if (start > end) return null;
        
        //if (start == end) return new TreeNode(head.val);
        
        int mid = start + (end - start) / 2;
        TreeNode left = helper(start, mid - 1);
        
        // head is the left most child
        TreeNode root = new TreeNode(head.val);
        
        root.left = left;
    
        // global head
        head = head.next;
        
        root.right = helper(mid + 1, end);
        
        return root;
    }
    
    
    
    /*Method 1: recursion + conversion to array. time = O(n), space = O(n)*/
    private TreeNode rec(ListNode head) {
        if (head == null) return null;
        List<Integer> arr = new ArrayList();
        while (head != null) {
            arr.add(head.val);
            head = head.next;
        }
        int size = arr.size();
        
        int left = 0, right = size - 1;
        
        return dfs(arr, left, right);
    }
    private TreeNode dfs(List<Integer> arr, int left, int right) {
        if (left > right) return null;
        
        int mid = left + (right - left) / 2;
        
        TreeNode root = new TreeNode(arr.get(mid));
        
        if (left == right) return root;
        
        root.left = dfs(arr, left, mid - 1);
        root.right = dfs(arr, mid + 1, right);
        
        return root;
    }
}
```
