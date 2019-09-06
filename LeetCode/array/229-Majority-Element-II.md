## [229. Majority Element II](https://leetcode.com/problems/majority-element-ii/)

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:
```
Input: [3,2,3]
Output: [3]
```
Example 2:
```
Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
```
## Answer
### Method 1 - Moore Voting - :rocket: 1ms (100%)
```java
class Solution {
    // ============ Moore Voting ============
    // max number of result is 2.
    // 1ms
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList();
        if (nums == null || nums.length == 0) return res;
        
        int num1 = nums[0], num2 = nums[0], cnt1 = 0, cnt2 = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1) {
                cnt1++;
            } else if (nums[i] == num2) {
                cnt2++;
            } else if (cnt1 == 0) {
                num1 = nums[i];
                cnt1 = 1;
            } else if (cnt2 == 0){
                num2 = nums[i];
                cnt2 = 1;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        
        cnt1 = 0;
        cnt2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1) cnt1++;
            else if (nums[i] == num2) cnt2++;
        }
        
        if (cnt1 > nums.length / 3) res.add(num1);
        if (cnt2 > nums.length / 3) res.add(num2);
        
        return res;
    }
}
```
