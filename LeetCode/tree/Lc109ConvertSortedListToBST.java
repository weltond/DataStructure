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
