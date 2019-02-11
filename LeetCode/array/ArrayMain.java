package com.weltond.array;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/11/2019
 */
public class ArrayMain {
    public static void main(String[] args) {
        KthElement.test();

        int[] arr = new int[]{12, 3, 5, 7, 4, 19, 6};
        int k = 3;
        System.out.println();
        System.out.print( " WANG 's K'th smallest element is " +
                findKthSmallest(arr, k));
    }

    public static int findKthSmallest(int[] array, int k) {

        return quickPartition(array, 0, array.length - 1, k);
    }

    private static int quickPartition(int[] arr, int left, int right, int k) {
        if(left >= right) return left;

        int pivot = arr[right];
        int l = left;
        int r = right - 1;
        while(l < r) {
            while(l < r && arr[l] < pivot) {
                l++;
            }
            while(l < r && arr[r] > pivot) {
                r--;
            }

            swap(arr, l, r);
        }
        swap(arr, l, right);
        if(l == k) return arr[l];
        else if(l > k) return quickPartition(arr, left, l, k);
        else return quickPartition(arr, l, right, k);

    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
