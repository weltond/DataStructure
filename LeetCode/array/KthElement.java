package com.weltond.array;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/11/2019
 */
public class KthElement {
    /**
     * Method 1: Sort Array and then get the Kth element
     *
     * Time = O(nlogn)
     */

    /**
     * Method 2: Create a Min-Heap of n elements and call pop() k times
     *
     * Time = O(n + klogn)
     */

    /**
     * Method 3: Use Max-Heap
     * 1. Build a max-heap of the first k elements
     * 2. after k-th element, compare it with the top of max-heap
     * a. if element is less than top, make it top and heapify
     * b. else ignore
     * Time = O((n-k)logk)
     * 3. Finally, the top of max-heap is the k-th element
     * <p>
     * Timme = O(k + (n-k)logk)
     */

    /**
     * Method 4: quick partition
     *
     * In quick sort, we pick a pivot element, then move the pivot element to its correct position and parition the array around it.
     * The idea is:
     *      1. not to do complete quick sort, but stop at the point where pivot itself is k-th smallest element.
     *      2. not to recur for both left and right sides of pivot
     *      3. but recur for one of them according to the position of pivot.
     *
     * Time = O(n^2) <- worst
     * Time = O(n)   <- average
     *
     * @param arr input array
     * @param l left
     * @param r right
     * @param k required k-th element
     * @return
     */
    // ================== WRONG ===========================
    public static int kthSmallest2(Integer[] arr, int l, int r, int k) {
        if (arr == null || arr.length == 0) return 0;

        if (k > 0 && k <= r - l + 1) {
            // Partition the array around last element and
            // get position of pivot element in sorted array
            int pos = partition2(arr, l, r);
            if (pos - l < k - 1) return kthSmallest2(arr, pos + 1, r, k - pos + l - 1);
            else if (pos - l > k - 1) return kthSmallest2(arr, l, pos - 1, k);

            return arr[pos];
        }

        return Integer.MAX_VALUE;
    }

    // Standard partition process of QuickSort
    // It considers the last element as pivot
    // and moves all smaller element to left of it and greater elements to right
    public static int partition2(Integer[] arr, int l, int r) {
        int pivotVal = arr[r];
        int left = l, right = r - 1;

        while (left <= right) {
            if (arr[left] > pivotVal) {
                swap(arr, left, right);
                right--;
            } else {
                left++;
            }
        }

        swap(arr, right++, r);
        System.out.println(right);
        return right;
    }
    // ================== END ======================


    public static int kthSmallest(Integer[] arr, int l,
                                  int r, int k)
    {
        // If k is smaller than number of elements
        // in array
        if (k > 0 && k <= r - l + 1)
        {
            // Partition the array around last
            // element and get position of pivot
            // element in sorted array
            int pos = partition(arr, l, r);

            // If position is same as k
            if (pos-l == k-1)
                return arr[pos];

            // If position is more, recur for
            // left subarray
            if (pos-l > k-1)
                return kthSmallest(arr, l, pos-1, k);

            // Else recur for right subarray
            return kthSmallest(arr, pos+1, r, k-pos+l-1);
        }

        // If k is more than number of elements
        // in array
        return Integer.MAX_VALUE;
    }

    public static int partition(Integer [] arr, int l,
                                int r)
    {
        int x = arr[r], i = l;
        for (int j = l; j <= r - 1; j++)
        {
            if (arr[j] <= x)
            {
                //Swapping arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
            }
        }

        //Swapping arr[i] and arr[r]
        int temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;

        return i;
    }

    public static void swap(Integer[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }

    public static void test() {
        Integer[] arr = new Integer[]{12, 3, 5, 7, 4, 19, 6};
        int k = 3;
        System.out.print( "K'th smallest element is " +
                kthSmallest2(arr, 0, arr.length - 1, k) );
    }
}
