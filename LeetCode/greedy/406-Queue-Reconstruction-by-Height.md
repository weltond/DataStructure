## [406. Queue Reconstruction by Height](https://leetcode.com/problems/queue-reconstruction-by-height/)

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

 
Example

```
Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
```

## Answer
### Method 1 :rocket: 5ms (99.35%)

```java
class Solution {
    // 5ms (99.35%)
    public int[][] reconstructQueue(int[][] people) {
        //pick up the tallest guy first
        //when insert the next tall guy, just need to insert him into kth position
        //repeat until all people are inserted into list
        
        // Arrays.sort(people, new Comparator<int[]>() {
        //    public int compare(int[] o1, int[] o2) {
        //        return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
        //    } 
        // });
        
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        
        List<int[]> res = new ArrayList();
        
        for (int[] cur : people) {
            res.add(cur[1], cur);
        }
        
        return res.toArray(new int[people.length][]);
    }
}
'''
