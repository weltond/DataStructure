// https://leetcode.com/problems/split-linked-list-in-parts/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // 0ms. Time = O(N + k), Space = O(k) or O(1)
    public ListNode[] splitListToParts(ListNode root, int k) {
        if (root == null) return new ListNode[k]; // [],3 => [[],[],[]]
        
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            cur = cur.next;
            N++;
        }
        
        int width = N / k, rem = N % k;
        
        ListNode[] res = new ListNode[k];
        cur = root;
        
        for (int i = 0; i < k; i++) {
            ListNode head = cur;
            // cur will be the last element of current part
            for (int j = 0; j < width + (i < rem ? 1 : 0) - 1; j++) {
                if (cur != null) cur = cur.next;
            }
            
            if (cur != null) {
                ListNode prev = cur;
                cur = cur.next;
                prev.next = null;
            }
            res[i] = head;
        }
        
        return res;
    }
}
