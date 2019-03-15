## Intuition
If we sort the intervals by their `start` value, then each set of intervals that can be merged will appear as a contiguous "run" in the sorted list.

- The situation when there is a conflict between (s1, e1) and (s2, e2):
```
e2 > s1 && e1 > s2
```
- The situation when there is no conflict between them:
```
e1 <= s2 || e2 <= s1
```

### 1. [Merge Intervals](https://github.com/weltond/DataStructure/blob/master/LeetCode/heap/Lc56MergeIntervals.java)
**Array / Sort**

- Approaches
  - PriorityQueue
  - Sort
  - Two Arrays to store starts and ends

### 2. [Partition Labels](https://github.com/weltond/DataStructure/blob/master/LeetCode/greedy/Lc763PartitionLabels.java)
**Two Pointers / Greedy**

- Approaches
  - Two Pointers
  
Use an array to store the last occurence of each distinct characters.
Then go over again with three index: 
1. `left`: start position of each intervals

2. `right`: end position of each intervals

3. `i`: current index

We update right when the current index is not the last occurrence one. And update the intervals when `right` meet `i`, which means that
there is no other character within the interval will occur in the remaining.

### 3. [Interval List Intersections](https://github.com/weltond/DataStructure/blob/master/LeetCode/array/Lc986IntervalListIntersections.java)
**Two Pointers**

- Approaches
  - Two Pointers
  
If A[0] has smallest endpoint, it can only intersect with B[0].

After that, we can discard A[0] since it **CANNOT** intersect anything.

Similar to B[0].

So we use **TWO POINTERs** to virtually manage "discarding" A[0] and B[0] repeatedly.

### 4. My Calendar
**Array / BST**
If we maintained our events in sorted order, 
we could check whether an event could be booked in O(\log N)O(logN) time.

We would also have to insert the event in our sorted structure.

SO-> We need a data structure that keeps elements sorted and supports fast insertion ---> **TreeMap** (BST)

### 4.1 [My Calendar I](https://github.com/weltond/DataStructure/blob/master/LeetCode/tree/Lc729MyCalendarI.java)

- Approaches
  - Brute Force
    - Use the [intuition](#intuition)
  - BST (TreeMap)
    - key: start
    - val: end
    use `floorKey(start)` and `ceilingKey(start)` to find the greatest key less than `start` and least key larger than `start`
    
### 4.2 [My Calendar II](https://github.com/weltond/DataStructure/blob/master/LeetCode/tree/Lc731MyCalendarII.java)

- Approaches
  - Brute Force
    - Use two lists that store all intervals and two-conflicted intervals. If there is an interval conflict with two-conflicted intervals, 
    it means there is triple booking. Otherwise, compare current with all intervals to find twice booking intervals and add it to the two-conflicted interval list
  - BST(TreeMap)
    - key : start / end
    - value : +1 / -1
    - If there are triple booking, their start1, start2, start3 will be all greater than min(end1, end2, end3). We can easily get their values by using BST(TreeMap)

### 4.3 [My Calendar III](https://github.com/weltond/DataStructure/blob/master/LeetCode/tree/Lc732MyCalendarIII.java)

- Approaches
  - BST(TreeMap): same as My Calendar II
  - Segment Tree: **TO DO**
