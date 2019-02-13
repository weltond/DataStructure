package com.weltond.heap;

/** Given the level order traversal of a Complete Binary Tree, determine whether the Binary Tree is a valid Min-Heap
 *
 *
 * @author weltond
 * @project LeetCode
 * @date 2/13/2019
 */
public class isMinHeapWithLevelOrder {
    private boolean isMinHeap(int[] level) {
        int n = level.length - 1;

        // first non leaf node is at index (n/2-1)
        // check whether each parent is greater than child
        for (int i = n / 2 - 1; i >= 0; i--) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if (left < n && level[i] > level[left]) return false;
            if (right < n && level[i] > level[right]) return false;
        }
        return true;
    }

    public static void test() {
        // Level order traversal
        int[] level = new int[]{10, 15, 14, 25, 30};
        isMinHeapWithLevelOrder is = new isMinHeapWithLevelOrder();
        if  (is.isMinHeap(level))
            System.out.println("True");
        else
            System.out.println("False");
    }
}
