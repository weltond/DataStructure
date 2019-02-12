package com.weltond.heap;

import java.util.PriorityQueue;

/** Time for heapify = O(logn)
 *  Time for createAndBuildHeap is O(n)
 *  Time = O(nlogn)
 *
 * @author weltond
 * @project LeetCode
 * @date 2/12/2019
 */
public class HeapSort {
    public void sort(int[] arr) {
        int n = arr.length;
        //PriorityQueue
        //Build max heap. only start from index: i * 2 + 2 = n =====> i = n / 2 - 1
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        printArray(arr);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // move current root (largest) to end
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // to heapify a subtree rooted with node i which is an index in arr[]. n is size of heap
    public void heapify(int[] arr, int n, int i) {
        int largest = i; // initialize largest as root
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        // if left child is larger than root
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        // if right is larger than largest so far
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        // if largest is not the root
        if (largest != i) {
            int tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void test() {
        int arr[] = {3, 11, 13, 5, 6, 8};
        int n = arr.length;

        HeapSort ob = new HeapSort();
        ob.sort(arr);

        System.out.println("Sorted array is");
        printArray(arr);
    }
}
