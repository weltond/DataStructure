## [179. Largest Number](https://leetcode.com/problems/largest-number/)

Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:
```
Input: [10,2]
Output: "210"
```
Example 2:
```
Input: [3,30,34,5,9]
Output: "9534330"
```
Note: The result may be very large, so you need to return a string instead of an integer.

## Answer
### Method 1 - Sort - :turtle: 35ms (23.38%)
```java
class Solution {
    // 35ms (23.38%)
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        
        Arrays.sort(strs, (o1, o2) -> {
           String a = o1 + o2;
            String b = o2 + o1;
            return b.compareTo(a);
        });
        if (strs[0].equals("0")) return "0";
        
        String ret = new String();
        for (String s : strs) {
            ret += s;
        }
        
        return ret;
    }
}
```
