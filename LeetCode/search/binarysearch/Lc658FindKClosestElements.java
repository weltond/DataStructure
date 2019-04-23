// https://leetcode.com/problems/find-k-closest-elements/

/**
Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
*/

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList();
        if (arr == null || arr.length == 0) return res;
        
        int left = 0, right = arr.length - k;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) left = mid + 1;
            else right = mid;
        }

        for (int i = left; i < left + k && i < arr.length; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList();
        if (arr == null || arr.length == 0) return res;
        
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while(left < right - 1) {
            mid = left + (right - left) / 2;
            
            if (arr[mid] == x) break;
            else if (arr[mid] > x) right = mid;
            else left = mid;
        }

        int start = right - left > 1 ? mid : Math.abs(x - arr[left]) <= Math.abs(x - arr[right]) ? left : right;
        left = start - 1;
        right = start + 1;
        res.add(arr[start]);
        while (k >= 2) {
            if (right >= arr.length || left >= 0 && Math.abs(x - arr[left]) <= Math.abs(x - arr[right])) {
                res.add(arr[left--]);
            } else if (left < 0 || right < arr.length && Math.abs(x - arr[left]) > Math.abs(x - arr[right])) {
                res.add(arr[right++]);
            }
            k--;
        }
        Collections.sort(res);
        return res;
    }
}
