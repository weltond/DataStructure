## [315. Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/)

You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

## Answer
### Method 1 - Naive - :turtle: 484ms (8.04%)
```java
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> l = new ArrayList();
        if (nums == null || nums.length == 0) return l;
        
        int[] a = new int[nums.length];
        
        for (int i = nums.length - 2; i >= 0; i--) {
            int cnt = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) cnt++;
            }
            a[i] = cnt;
        }
        
        
        
        for (int i = 0; i < nums.length; i++) 
            l.add(a[i]);
        
        return l;
    }
}
```
