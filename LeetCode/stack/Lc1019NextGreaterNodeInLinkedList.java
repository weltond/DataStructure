// https://leetcode.com/problems/next-greater-node-in-linked-list/

/**
Example 1:

Input: [2,1,5]
Output: [5,5,0]
Example 2:

Input: [2,7,4,3,5]
Output: [7,0,5,5,0]
Example 3:

Input: [1,7,5,1,9,2,5,1]
Output: [7,9,9,9,0,5,0,0]
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    int[] res;
    // =========== Method 2: Recursion ===========
    // 3ms (100%)
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) return new int[0];
        helper(head, 0);  
        return res;
    }
    
    private ListNode helper(ListNode head, int length) {
        if (head == null) {
            res = new int[length];
            return null;
        }
        
        ListNode next = helper(head.next, length + 1);
        
        while (next != null && head.val >= next.val) {
            next = next.next;
        }
        if (next != null) {
            res[length] = next.val;
        }
        
        head.next = next;     // without this line, runtime is 1390ms (5.05%)
        return head;
    }
    // =========== Method 1: Iteration ===========
    // 15ms (91.90%)
//     public int[] nextLargerNodes(ListNode head) {
//         int[] tmp = new int[10001];
//         int i = 0;
//         while (head != null) {
//             tmp[i++] = head.val;
//             head = head.next;
//         }
        
//         int[] res = new int[i];
        
//         Deque<Integer> stack = new LinkedList();
//         for (int j = i - 1; j >= 0; j--) {
//             while (!stack.isEmpty() && tmp[j] >= stack.peek()) {
//                 stack.pop();
//             }
//             res[j] = stack.isEmpty() ? 0 : stack.peek();
//             stack.push(tmp[j]);
//         }
        
//         return res;
//     }
}
