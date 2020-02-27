## [480. Sliding Window Median](https://leetcode.com/problems/sliding-window-median/)

![](https://github.com/weltond/DataStructure/blob/master/hard.PNG)

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:

```
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5
```

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,

Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

```
Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
```

Therefore, return the median sliding window as `[1,-1,-1,3,5,6]`.

Note:
- You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
- Answers within 10^-5 of the actual value will be accepted as correct.

## Answer
### Method 1 - Heap - :turtle: 84ms (26.97%)

```java
class Solution {
    PriorityQueue<Integer> min = new PriorityQueue();
    PriorityQueue<Integer> max = new PriorityQueue<Integer>(Collections.reverseOrder());    // o2-o1 not working when input is Integer, because of java max min problem.
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) return new double[]{};
        
        double[] res = new double[nums.length - k + 1];
        
        int idx = 0;
        
        for (int i = 0; i < nums.length; i++) {
            Add(nums[i]);
            
            if (i < k - 1) continue;
            
            res[idx++] = getMedian();
            
            Remove(nums[i - k + 1]);
        }
        
        return res;
    }
    
    public void Balance() {
        if (min.size() > max.size()) {
            max.offer(min.poll());  // to make sure we retrieve from max pq or both.
        }
        
        // because we do have to do remove step so we need to rebalance
        if (max.size() - min.size() > 1) {
            min.offer(max.poll());
        }
    }
    
    public void Add(int val) {
        max.offer(val);
        min.offer(max.poll());
        
        Balance();
    }
    
    public void Remove(int val) {
        if (max.peek() < val) {
            min.remove(val);
        } else {
            max.remove(val);
        }
        
        Balance();
    }
    
    public double getMedian() {
        int cnt = max.size() + min.size();
        return cnt % 2 == 0 ? ((double) max.peek() + (double) min.peek()) / 2.0 : max.peek();
    }
}
```
