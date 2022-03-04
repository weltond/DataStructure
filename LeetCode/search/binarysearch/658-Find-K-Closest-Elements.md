## [658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
 

Example 1:

```
Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
```

Example 2:
```
Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
``` 

**Constraints**:

- 1 <= k <= arr.length
- 1 <= arr.length <= 104
- arr is sorted in ascending order.
- -104 <= arr[i], x <= 104

## Answers

### Method 2 - Binary Search

```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList();
        if (arr == null || arr.length == 0) return res;
        
        int left = 0, right = arr.length - k;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) left = mid + 1;
            else right = mid; // cannot be mid - 1. because current mid might be included?
        }

        for (int i = left; i < left + k && i < arr.length; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}
```

Corner cases: 
- [1], 1, 1
- [1,2,3,4,5], 4, 3
- [1,1,1,10,10,10], 1, 9
- [0,2,2,3,4,6,7,8,9,9], 4, 5
 
```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        
        int left = 0, right = arr.length - k;
        
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            
            if (x - arr[mid] > arr[mid + k] - x) left = mid;
            else right = mid;
        }

        
        int start = 0;
        if (left + k < arr.length && x - arr[left] > arr[left + k] - x) {
            start = right;
        } else {
            start = left;
        }
        
        List<Integer> res = new LinkedList();
        
        for (int i = start; i < start + k && i < arr.length; i++) {
            res.add(arr[i]);
        }
        return res;
    }

}
```

### Method 1 - Two Pointers - O(n) - 8ms (44.62%)

```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int low = 0, high = arr.length - 1;
        
        while (high - low >= k) {
            int v1 = Math.abs(arr[low] - x), v2 = Math.abs(arr[high] - x);
            
            if (v1 > v2) {
                low++;
            } else {
                high--;
            }
        }
        
        List<Integer> res = new ArrayList();
        
        for (int i = low; i <= high; i++) {
            res.add(arr[i]);
        }
        
        return res;
    }
}
```
```java
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
```
