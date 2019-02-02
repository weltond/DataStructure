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



# String

## 5 popular problems 
(like array,usually need 2 pointers)

1. Removal 
  a. Remove some particular chars from a string
  b. Remove all leading/trailing/duplicated empty spaces from a string

2. De-deplication `aaaabbb_ccc ----> ab_c`

3. Replace empty space `" "` with `"20%"`

4. Reversal (swap) `I love yahoo -> yahoo love I`

5. Substring -> strstr
  a. Regular method
  b. Robin-Carp (hash based string matching) & KMP(Knuth-Morris-Pratt)

## Advanced operations
1. Move letters around `ABCD1234 -> A1B2C3D4`

2. Permutation (DFS)

3. Decoding/encoding `aaaabcc -> a4b1c2`

4. Longest sub-string that contains only unique chars

5. Matching (?, *)

## KEY: **HOW SLOW POINTER is MOVED**


## Problems

### 1. Char Removal
#### Q1.1 Remove a/some particular chars from a string.
`Example: string input = "Student", remove "u" and "n" -> output : "stdet"`
**Mistakes**

When 1st 'U' (u1) is removed (by calling expensive API str.erase()), all the rest chars after it are shifted left by one. So the consequence is the chars after it will not be removed after iteration.

- Solution: Two pointers. Time = O(2n)
```
- i: slow pointer starts from 0
  - All letters to the left-hand side of i (**not including i**) are all processed letters that should not be removed.

- j: fast pointer starts from 0
  - j is the current index to move.
  
- All letters in [i, j] are all area that we do not care (empty space)

- (j, size - 1] are unknown area to explore
```
- Code
```java
private String RemoveChar(String input) {
  if (input.length() != 0) return input;
  int i = 0, j = 0;
  char[] arr = input.toCharArray();
  while (j < input.length()) {
    if (arr[j] == 'u' || arr[j] == 'n') ++j;
    else arr[i++] = arr[j++];
  }
  return new String(arr, 0, i);
}
```
#### Q1.2 Remove all leading/trailing and duplicate empty spaces (only leave one empty space if duplicated spaces happen) from the input string.
`Input = "   abc  ed  ef  " ---> Output = "abc ed ef"`
- Solution
```
- i: slow pointer starts from 0
  - All letters to the left-hand side of i (not including i) are all proccessed letters that should not be removed

- j: fast pointer starts from 0
  - j is the current index to move
  
- All letters in [i, j] are all area that we do not care (empty space)

- (j, size - 1] are unknown area to explore

- For each word in the original string:
  - Skipping all leading/duplicate empty space
  - Add only one empty space in front of each word (except for the 1st word in the sentence)
```

### 2. Char De-duplication
#### Q2.1 Remove duplicate and adjacent letters (leave only one letter in each duplicated section) in a string
`E.g. Input = "ab ccccc" ---> Output = "ab c"`
- Solution
```
- slow pointer starts from 0
  - slow index all letters to left of string (including current) should be the final answer
- fast pointer starts from 0
  - current index to scan the string from left to right
```
