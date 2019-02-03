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

- Solution: Two pointers. **Time = O(2n)**
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
- Code
```java
pubic String deDuplicate(String s) {
    char[] str = s.toCharArray();
    if (str.length < 2) return s;
    
    int fast = 1;
    int slow = 0;
    while (fast < str.length) {
        if (str[slow] != str[fast]) str[++slow] = slow[fast++];
        else fast++;
    }
    
    return new String(str, 0, slow + 1);
}
```
#### Q2.2 De-duplicate adjacent letters repeatedly
`E.g. Input = "abbbaz", Output = "z". 

Explain: abbbaz -> aaz -> z
`
- Solution **Linear Scan 回头看** ----> Using Stack
  - Method 1: Explicitly maintain a stack. **Time = O(n^2), Space = O(n)**
  - Method 2: Implicitly use a stack **Time = O(n), Space =O(1)**
  ```
  - Step 1: On the left the slow pointer is the 'stack' (including slow)
  - Step 2: Slow = 0, fast = 1
  - Step 3: If fast = slow, slow--
  - Step 4: else put fast into slow position
  slow pointer is actually pointing to the top element of the stack!
  ```
- Code
```java
// Method 1. Notice we are only using stack concept instead of a real stack because we don't want to reverse the string in the stack.
public String removeDuplicate(String s) {
    char[] str = s.toCharArray();
    if (str.length <= 1) return s;
    
    List<Character> stack = new ArrayList();
    int i = 0;
    while (i < str.length) {
        char c = str[i];
        if (stack.size() > 0 && c == stack.get(stack.size() - 1) {  // stack.peek()
            while (i < str.length && c == str[i]) {
                i++;
            }
            stack.remove(stack.size() - 1); // stack.pop()
        } else {
            stack.add(c);
            i++;
        }
    }
    
    StringBuilder sb = new StringBuilder();
    for (char c: stack) {
        sb.append(c);
    }
    
    return sb.toString();
}
```
### 3. Sub-string Finding
#### Q3.1 How to determine whether a string is a substring of another string.
- Solution
  - Method 1: Robin-Karp
  - Method 2: Just compare **Time = O(m*n)**
- Code
```java
//Method 2
public int strmatch(String s1, String s2) {
    if (s1 == null || s2 == null) return -1;
    
    int i,j;
    int len1 = s1.length();
    int len2 = s2.length();
    
    if (len1 < len2) return strmatch(String s2, String s1);
    
    for (i = 0; i <= len1 - len2; i++) {
        for (j = 0; j < len2; j++) {
            if (s1.charAt(i+j) != s2.charAt(j)) break;
        }
        if (j == len2) return i;
    }
    
    return -1;
}
```

### 4. String Reversal
#### Q4.1 apple -> elppa
- Solution
  - Method 1: iterative with two pointer
  - Method 2: Recursion
#### Q4.3 I love yahoo -> yahoo love I
- Solution
  - Method 1: Use stack.
  - Method 2: 
  ```
    - step 1: reverse each word
    - step 2: reverse each sentence
  ```
#### Q4.4 `abcdef -> efabcd` shift the whole string to the right-hand side by k (k=2 here) positions.
- Solution: same as [Q4.3](####Q4.3) **Time = O(2n)**
```
  - step 1: fe | dcba
  - step 2: ef | abcd
```

**Discussion**
- The idea for 'I love yahoo' can be combined to from more complex problems. E.g. if we have empty/leading/trailing spaces in the input
- The idea can be extended to other problems as well
  - String(array) shifting by X chars to the right `abcdef -> cdefab`
    - step 1: reverse each block: ab | cdef -> ba | fedc
    - Step 2: reverse whole sentence: ba | fedc -> cdefab

### 5. Char Replacement
