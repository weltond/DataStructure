/**
Given an array of n elements, where each element is at most k away from its target position, 
devise an algorithm that sorts in O(n log k) time. For example, 
let us consider k is 2, an element at index 7 in the sorted array, 
can be at indexes 5, 6, 7, 8, 9 in the given array.

Input : arr[] = {6, 5, 3, 2, 8, 10, 9}
            k = 3 
Output : arr[] = {2, 3, 5, 6, 8, 9, 10}

Input : arr[] = {10, 9, 8, 7, 4, 70, 60, 50}
         k = 4
Output : arr[] = {4, 7, 8, 9, 10, 50, 60, 70}
*/

public int solution(int[] arr, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue(k + 1);
    
    for (int i = 0; i <= k; i++) {
        pq.offer(arr[i]);
    }
    
    int index = 0;
    for (int i = k + 1; i < arr.length; i++) {
        arr[index++] = pq.poll();
        pq.offer(arr[i]);
    }
    
    while (!pq.isEmpty()) {
        arr[index++] = pq.poll();
    }
}
