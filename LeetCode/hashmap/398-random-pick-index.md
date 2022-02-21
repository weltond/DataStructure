## [398. Random Pick Index](https://leetcode.com/problems/random-pick-index/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Implement the Solution class:

- `Solution(int[] nums)` Initializes the object with the array nums.
- `int pick(int target)` Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.
 

Example 1:

```
Input
["Solution", "pick", "pick", "pick"]
[[[1, 2, 3, 3, 3]], [3], [1], [3]]
Output
[null, 4, 0, 2]

Explanation
Solution solution = new Solution([1, 2, 3, 3, 3]);
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
``` 

**Constraints**:

- 1 <= nums.length <= 2 * 104
- -231 <= nums[i] <= 231 - 1
- target is an integer from nums.
- At most 104 calls will be made to pick.

## Answers
### Method 2 - Reservoir Sampling
Reservoir sampling is a technique which is used to generate numbers randomly when we have a large pool of numbers. As mentioned in the note for this question, the array size can be large, hence it is a reasonable choice to use Reservoir Sampling. Consider an array of size nn from which we need to chose a number randomly. Consider these numbers to be coming in the form of a stream, hence at each step, we have to take the decision of whether or not to choose a given number, such that the overall probability of each number being chosen is same (1/n in this case). If we have a total of nn numbers and we pick the ith number, this implies that we do not pick any number further from index (i + 1) to n. In terms of probability, this can be represented as
 <img width="183" alt="image" src="https://user-images.githubusercontent.com/9000286/154871988-cb1c188c-9b6a-450a-ac15-4c0e821ea4f6.png">

This can be interpreted as

- Pick the ith number from the list of i numbers
- Not picking the (i+1)th number from the list of (i+1) numbers. Hence picking any of the remaining i number
- And so on
- Not picking the nth number from the list of (n) numbers. Hence picking any of the remaining (n-1) numbers.

Time: O(N), Space: O(1)

```java
class Solution {

    private int[] nums;
    private Random rand;
    
    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    
    public int pick(int target) {
        int n = this.nums.length;
        int count = 0;
        int idx = 0;
        for (int i = 0; i < n; ++i) {
            // if nums[i] is equal to target, i is a potential candidate
            // which needs to be chosen uniformly at random
            if (this.nums[i] == target) {
                // increment the count of total candidates
                // available to be chosen uniformly at random
                count++;
                // we pick the current number with probability 1 / count (reservoir sampling)
                if (rand.nextInt(count) == 0) {
                    idx = i;
                }
            }
        }
        return idx;
    }
}
```

#### Reservoir Sampling
给定一个数据流，数据流长度N很大，且N直到处理完所有数据之前都不可知，请问如何在只遍历一遍数据（O(N)）的情况下，能够随机选取出m个不重复的数据。

这个场景强调了3件事：

- 数据流长度N很大且不可知，所以不能一次性存入内存。
- 时间复杂度为O(N)。
- 随机选取m个数，每个数被选中的概率为m/N。

```java
int[] reservoir = new int[m];

// init
for (int i = 0; i < reservoir.length; i++)
{
    reservoir[i] = dataStream[i];
}

for (int i = m; i < dataStream.length; i++)
{
    // 随机获得一个[0, i]内的随机整数
    int d = rand.nextInt(i + 1);
    // 如果随机整数落在[0, m-1]范围内，则替换蓄水池中的元素
    if (d < m)
    {
        reservoir[d] = dataStream[i];
    }
}
```
  
### Method 1 - HashMap - 106ms (50.95%)

Cache result using hashmap.

Time: 
- Building indices: O(N)
- Pick O(1)

Space: O(N)

```java
class Solution {

    Map<Integer, List<Integer>> map = new HashMap(); // <val, indices>
    Random rd = new Random();
    
    public Solution(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], o -> new ArrayList()).add(i);
        }
    }
    
    public int pick(int target) {
        if (!map.containsKey(target)) return -1;
        
        List<Integer> indices = map.get(target);
        
        int size = indices.size();
        
        return indices.get(rd.nextInt(size));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
```
