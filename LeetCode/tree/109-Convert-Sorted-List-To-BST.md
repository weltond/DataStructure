## [637. Valid Word Abbreviation](https://www.lintcode.com/problem/valid-word-abbreviation/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a non-empty string word and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as `"word"` contains only the following valid abbreviations:

`["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]`

Example
Example 1:

```
Input : s = "internationalization", abbr = "i12iz4n"
Output : true
```

Example 2:

```
Input : s = "apple", abbr = "a2e"
Output : false
```

Notice
- Notice that only the above abbreviations are valid abbreviations of the string word. Any other string is not a valid abbreviation of word.

## Answer
### Method 1 - Two Pointer - :rabbit: 330ms (70%)

```java
public class Solution {
    /**
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        // write your code here
        if (word == null || word.length() == 0) return false;
        
        int i = 0, j = 0;
        int lenw = word.length(), lena = abbr.length();
        
        while (i < lena) {
            char c = abbr.charAt(i);
            if (c > '0' && c <= '9') {     // ignore num start with `0`
                int res = 0;
                while (i < lena && Character.isDigit(abbr.charAt(i))) {
                    res = res * 10 + abbr.charAt(i) - '0';
                    i++;
                }
                j = j + res;
            } else {
                if (j >= lenw || c != word.charAt(j++)) {
                    //System.out.println(i+","+j);
                    return false;
                }
                i++;
            }
        }
        
        return i == lena && j == lenw;
    }
}
```

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
