## [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a non-empty array of integers, return the k most frequent elements.

Example 1:

```
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```

Example 2:

```
Input: nums = [1], k = 1
Output: [1]
```

Note:

- You may assume k is always valid, `1 ≤ k ≤ number of unique elements`.
- Your algorithm's time complexity **must be** better than `O(n log n)`, where n is the array's size.

## Answer
### Method 2 - Bucket Sort - :rocket: 11ms (90.84%)

```java
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new LinkedList();
        Map<Integer, Integer> map = new HashMap();
        
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1); 
        }
        
        List[] bucket = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList();
            }
            
            bucket[freq].add(key);
        }
        
        int cnt = 0;
        for (int i = bucket.length - 1; i >= 0 && cnt < k; i--) {
            if (bucket[i] != null) {
                List<Integer> list = bucket[i];
                for (int num : list) {
                    cnt += 1;
                    res.add(num);
                }
            }
        }
        
        return res;
    }
    
}
```

### Method 1 - PriorityQueue - :rabbit: 14ms (54.92%)

```java
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new LinkedList();
        Map<Integer, Integer> map = new HashMap();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        // min heap
        PriorityQueue<Integer> pq = new PriorityQueue((o1, o2) -> (map.get(o1) - map.get(o2)));
        
        for (int i : map.keySet()) {
            pq.offer(i);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        return res;
    }
    
}
```
