## [Minimum jumps with boosters](https://leetcode.com/discuss/interview-question/446026/Microsoft-or-Onsite-or-Minimum-jumps-with-boosters)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Givent an array containing numbers 1 to n where `array[i] = i+1` find the minimum number of jumps required from `arr[0]` to `arr[n-1]`.

Constraints:

- From current position i, we can jump to positions `i+1`, `i+2`, `i+3` and `i+4`.

- There are optional boosters given in the form of an object like - `{1: 15, 5: 10}` which means, if we reached at index 1 we can directly jump to index 15 similarly, if we reached at index 5, we can directly jump to index 10.

Find the minimum number of jumps required?

Example 1:

```
arr = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]

boosters  = {3: 8, 5:10}

The minimum number of jumps is : 4 

(1 -> 5 -> 10 -> 14 -> 15)
```


Notice
- Notice that only the above abbreviations are valid abbreviations of the string word. Any other string is not a valid abbreviation of word.

## Answer
### Method 1 - DP

```java
public static void main(String[] args) {
	int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
	int[][] boosters = { { 3, 8 }, { 5, 10 }};
	System.out.println(minJump(nums, boosters));

}

private static int minJump(int[] nums, int[][] boosters) {
	int[] dp = new int[nums.length + 1];
	Arrays.fill(dp, Integer.MAX_VALUE);
	dp[0] = 0;
	Map<Integer, Integer> bMap = new HashMap<>();
	for(int[] b : boosters) {
		bMap.put(b[1], b[0]);
	}
	for(int i=1;i<dp.length;i++) {
		if(i < 5) {
			dp[i] = 1;
		}else {
			for(int j=1;j<=4;j++) {
				dp[i] = Math.min(dp[i], dp[i-j] + 1);
			}
		}
		if(bMap.containsKey(i)) {
			dp[i] = Math.min(dp[i], dp[bMap.get(i) - 1] + 1);
		}
	}
	return dp[nums.length];
}
```
