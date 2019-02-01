# Hash Table (General Term)
- Hash Map
- Hash Set
- Principle <key, value>
- Hash collision
  - Chaining
  - Open address (probe + rehash)
  
## Problems
#### Q1. For a composition with different kinds of words, try to find the top K frequent words from the composition.
Solution: Hash map + priority queue
```
<key = word_i, value = counter for word i>
- Step 1: read the composition, and count the freq of each word by using the hash map.
- Step 2a: insert each element from the hash map into the MAX_HEAP --> **Time = O(nlogn)**
- Step 2b: build a MIN_HEAP for the first k elements of the hash map. Iterate over each element from the (k+1)th element to the n-th element, and update the MIN_HEAP as follows:
  - If i-th element > MIN_HEAP.top(), then remove top and insert i-th element.
  - Otherwise, do nothing.
  - **Time = O(k + (n-k)logk)**
- Step 3: Pop k elements out of the MAX_HEAP then the problem is solved. **Time = O(klogn)**
```

#### Q2. If there is **only one** missing number from 1 to n in an unsorted array. How to find it in O(n) time? Size of the array is n-1.
- Solution 1: HashSet. **Time = O(n), Space = O(n)**
```
- Step 1: Use a hash set to store all elements.
- Step 2: Iterate all elements from 1 to n and check against the hash set
```
- Solution 2: Math. **Time = O(n), Space = O(1)**
```
- Get the sum for all elements = S. The missing number is n(n+1)/2 - S.
  - Maybe overflow if n is too large.
  - Follow up: for mathematical computation, when we are facing and what to worry?
    - Precision for integer
    - Denominator = 0
```
- Solution 3: XOR for all element in the input together with (1...n)
```
- Step 1: XOR all elements in the array -> result1
- Step 2: Start with result1 XOR 1 XOR 2 XOR 3... -> get the missing number
```

#### Q3. Find the common numbers between two sorted arrays a[N], b[M], N, M.
- Soltuion 1: Binary Search. **Time = O(mlogn) m<<n**
```
- Step 1: for each element X in the shorter array, we run a binary search in the longer array.
```
- Solution 2: Hash map. **Time = O(m+n), Space = O(min(m,n))**
```
- Step 1: store all elements from the shorter array, because we want to optimize the space.
- Step 2: for each element in the longer array, we check it against the hash map.
```
- Solution 3: 谁小移谁. **Time = O(m+n)**

