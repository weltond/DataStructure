## [410. Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/)

Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

- 1 ≤ n ≤ 1000
- 1 ≤ m ≤ min(50, n)
Examples:
```
Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
```

## Answer
### Method 2 - DP -
```java
// TO DO...
```

### Method 1 - Binary Search -
Idea:
- If m = 1, the res is the sum of the array.
- If m = n, the res is the max num of the array.
- If 1 < m < n, we can use Binary Search to find the result:
  - 
```java

```
