## [637. Valid Word Abbreviation](https://www.lintcode.com/problem/valid-word-abbreviation/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given a non-empty string word and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as `"word"` contains only the following valid abbreviations:

`["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]`

Example
Example 1:

```
Input : s = "internationalization", abbr = "i12iz4n"
Output : true
```

Example 2:

```
Input : s = "apple", abbr = "a2e"
Output : false
```

Notice
- Notice that only the above abbreviations are valid abbreviations of the string word. Any other string is not a valid abbreviation of word.

## Answer
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
