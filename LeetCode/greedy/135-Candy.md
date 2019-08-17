## [135. Candy](https://leetcode.com/problems/candy/)

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1:

Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.

## Answer
### Method 1 - Greedy - :rocket: 2ms (100%)
**Scan for two directions**
```java
class Solution {
    // 2ms (100%)
    public int candy(int[] ratings) {
        if (ratings == null) return 0;
        
        int[] arr = new int[ratings.length];
        Arrays.fill(arr, 1);
        
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) arr[i] = arr[i - 1] + 1;
        }
        
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) arr[i] = Math.max(arr[i], arr[i + 1] + 1);
        }
        
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
        }
        
        return res;
    }
}
```
