# "K" related problems

- [PriorityQueue(Heap)](#priorityqueue)
- [Sliding Window](#sliding-window)
- [DFS/BFS](#dfs/bfs)
- [Divide and Conquer](#divide-and-conquer)

## PriorityQueue
### [358. Rearrange String k Distance Apart](https://github.com/weltond/DataStructure/blob/master/LeetCode/heap/358-Rearrage-String-K-Distance-Apart.md)
Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:

```
str = "aabbcc", k = 3
Result: "abcabc"
The same letters are at least distance 3 from each other.
```

#### Solution
- PriorityQueue
  - **HashMap** to store `<char, freq>`
  - **PriorityQueue** to store `char`, sorted by **freq** then **char**.
  - For a `k` loop, poll a char from priority queue.
    - If queue empty, return `""`.
    - Else, if current `char` freq is still greater than `1`, save it to a tmp **List** that will be pushed into the priorityqueue after `k` loops.



## [1296. Divide Array in Sets of K Consecutive Numbers](https://github.com/weltond/DataStructure/blob/master/LeetCode/greedy/1296-divide-array-in-sets-of-k-consecutive-numbers.md)

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
Return True if its possible otherwise return False.


Example 1:

```
Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
```

#### Solution
- PriorityQueue
  - **HashMap** store `<element, freq>`. **PriorityQueue** store element.
  - For each `poll`, get its freq, then for `k` loop, increase the polled element by 1 and get each freq. If the element doesn't contain or its freq is not equal polled freq. return false.


## Sliding Window
### [340. Longest Substring with At Most K Distinct Characters](https://github.com/weltond/DataStructure/blob/master/LeetCode/hashmap/340-longest-substring-with-at-most-k-distinct-characters.md)

Given a string S, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

```
Input: S = "eceba" and k = 3
Output: 4
Explanation: T = "eceb"
```

#### Solution
- Sliding Window
  - **HashMap** or use **array** to store `<char, freq>` .
  - `start` and `end` pointers. Move `end` pointer. Add `cnt` when seeing distinct char.
  - we can update `res` when 'cnt <= k'.
  - Once 'cnt > k`, move `start` pointer and update `cnt` when `start` is the only char in the window.
  
  
## DFS/BFS

## [787. Cheapest Flights Within K Stops](https://github.com/weltond/DataStructure/blob/master/LeetCode/graph/787-Cheapest-Flights-Within-K-Stops.md)

There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, 
your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:

```
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:

The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
```

#### Solution
- DFS (slow)
  - Try every connected city and calculate result when meet destination.
- BFS
- Dijkstra
  - PriorityQueue, sort based on total cost from start point.

### [698. Partition to K Equal Sum Subsets](https://github.com/weltond/DataStructure/blob/master/LeetCode/recursion/698-Partition-to-K-Equal-Sum-Subsets.md)

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:

```
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
```

#### Solution
- DFS
  - Try every possible subset.
  - pruning when visited or current sum is less than requirement.

## Divide and Conquer
### [395. Longest Substring with At Least K Repeating Characters](https://github.com/weltond/DataStructure/blob/master/LeetCode/string/395-Longest-Substring-with-At-Least%20K-Repeating-Characters.md)

Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

```
Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
```

#### Solution
- Divide and Conquer
  - Basic idea is that the substring must be divided by those chars that appears less than `k` times in the string.
  - First find the freq for every char in the string. (**SHOULD CALCULATE FOR EVERY NEW SUBSTRING**)
  - Iterate the string and find the first char that appears less than `k` times.
  - Divide and conquer
  



