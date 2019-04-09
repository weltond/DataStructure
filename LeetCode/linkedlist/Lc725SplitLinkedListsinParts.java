// https://leetcode.com/problems/split-linked-list-in-parts/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
Input: 
root = [1, 2, 3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:
The input and each element of the output are ListNodes, not arrays.
For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but it's string representation as a ListNode is [].

Input: 
root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
Explanation:
The input has been split into consecutive parts with size difference at most 1, 
and earlier parts are a larger size than the later parts.
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
