## [1060. Missing Element In Sorted Array]()

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.

Example 1: 

```
Input: A = [4,7,9,10], K = 1 
Output: 5 
Explanation: The first missing number is 5.
```

Example 2: 

```
Input: A = [4,7,9,10], K = 3 
Output: 8 
Explanation: The missing numbers are [5,6,8,…], hence the third missing number is 8. Example 3:
```

Example 3:

```
Input: A = [1,2,4], K = 3 
Output: 6 
Explanation: The missing numbers are [3,5,6,7,…], hence the third missing number is 6.
```

Note: 
- `1 <= A.length <= 50000`
- `1 <= A[i] <= 1e7` 
- `1 <= K <= 1e8`

## Answer
### Method 1 - Binary Search

- It is easy to get how many cnt of missing numbers between each element in the array.
```
nums =	[4,7,9,10,13,15,16]
cnt  =	[0,2,3,3, 5, 6, 6]
```
- If k = 3, we will stop at number 9. If K = 4, we will stop at number 13.
- Then it is clear that we should find the **index** of the **last upper-bound greater than k**. 

```java
class Solution {
	public int missingElement(int[] nums, int k) {
		// assume nums is not empty
		int left = 0, right = nums.length - 1;
		int cnt = missingCnt(nums, right);
		
		if (k > cnt) {
			return nums[right] + k - cnt;
		}
		
		while (left < right) {
			int mid = left + (right - left) / 2;
			cnt = missingCnt(nums, mid);
			
			if (cnt >= k) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return nums[left - 1] + k - missingCnt(nums, left - 1);
	}
	
	// 	nums =	[4,7,9,10,13,15,16]
	//->		[0,2,3,3, 5, 6, 6]
	public int missingCnt(int[] nums, int idx) {
		return nums[idx] - nums[0] - idx;
	}
}
```
