Table of Contents
=================

   * [Table of Contents](#table-of-contents)
   * [Array](#array)
   
   
# Array
- Arrays.asList([1,2,3])
- Partial sort: Arrays.sort(arr, 0, arr.length())
- Copy: Arrays.copyOf(arr, arr.length())
- Arrays.toString(int[] arr) ==> string representation: "[1,2,3,4]"
- Problem with arrays: can we sort it first?

## Honorable Problems
- Median of two sorted arrays: find kth element of two sorted array where k = n/2. Recursive findKth(A[], indexA, B[], indexB, k)

# String
## Functions
- s.toCharArray()
- String.valueOf(charArray)
- String.compareTo("XXX"): Sort by lexicographically
- Character.isDigit(str)
- str.split("\\") -> regular expression
- str.trim()
- count characters: int[256]. No need to `c - 'a'`

## StringBuffer
- sb.replace(i, j, replace_str)
- sb.reverse(), sb.append(), sb.length(), sb.setCharAt(index, char)
- sb.deleteCharAt(sb.length() - 1): usually used in recursion like subset problem
- sb.insert(idx, str)

## Others
- Problem with string: think about replicated string

# Hash
## HashMap
- Ex: when searching unsorted array(if you try to avoid sort O(nlogn)), probably can index with HashMap
- Can be used to store character/string frequency
- TreeMap/TreeSet: Balanced Binary Tree
- Use iterator: `Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();`
- `iter.hasNext()` and `iter.next()`

## HashSet
- contains: O(1)
- set.add(...) return false if there is duplicate. This operation won't change the existing set.
- Build HashSet<List> set, and the set will automatically compare the equivalence of the lists within at each list element level.

# Heap
- Min_Heap & Max_Heap has same concept
- Min_Heap is a complete binary tree
- each node is smaller (min heap) or greater (max heap) than its children
- Maintaining heap is about swaping node values
- Insert node / Delete root both take O(logn) 
- Build heap from a given array takes O(n) time
- PriorityQueue<> q = new PriorityQueue<>({(o1,o2) -> Integer.compare(o1.val, o2.val)});

## Insert
- insert at bottom right-most spot
- swap with parent if value not fitting min-heap (smaller than its parent value)
- swapping until min value reaches root
- O(logn) to bubble up to top

## Pop root
- get root value (minimum among min-heap)
- Set the root value to be bottom-right-most element, then remove that bottom element
- bubble down the root value if not fitting min-heap (greater than its left/right child, usually swap the max one)
- O(logn)

## When to use Min/Max Heap
- maitain a collection of min/max value
- **Median**
- K-th largest/smallest element
- Given an unsorted array, sort it and then use heap

## Example
- Given n items, find first k smallest items (`k closest point to the origin`):
  - Solution1: putting all n items into a min-heap(priorityQueue), and output top k by polling. That will be O(nlogn)
  - Better Solution: 1. use max-heap to store first k items; 2. if any value less than max, replace max with new value.
  - Result is: always keep the smaller items in the max-heap, and replace the head/max.
  - therefore, building the queue is like O(nlogk), saved space and time

# Stack
## Functions
- peek(), pop(), push()
- Use Deque to replace Stack in JAVA

## Basic 
- Temperatly store information
- Reverse stack
- Optimize DFS with stack
- Implement stack with LinkedList => adding/removing from same side of the list
- Monotonous stack

## Monotonous Stack
- 找每个元素左边或者右边 第一个比它自身小/大的元素
- 用单调栈来维护
- 维护monotonous stack 是题目需要, 而不是stack本身性质. 
- 比如, 题目需要stack.peek() O(1), 加上需要单调递增/递减的性质, 就用起来了stack.
```java
// Use monotonous stack to build minimum binary tree
// 1. Create that new node
TreeNode ndoe = new TreeNode(val); 
// 2. The stack is monotonous, so loop all items >= node.val, and set as left child.
// monotonous: continuous increasing or decreasing, so the loop will end at some point.
while (!stack.isEmpty() && node.val <= stack.peek().val) { 
    node.left = stack.pop();
}
// 3. The item left in stack is < node.val, so node should be a child.
if (!stack.isEmpty()) {//build right node of the tree
    stack.peek().right = node;
}
// 4. Every new node needs to be tested. push to stack.
stack.push(node);

// End result: parentheses will not be in the tree
             [ - ]
         /          \
    [ * ]              [ / ]
  /     \           /         \
[ 2 ]  [ 6 ]      [ + ]        [ + ]
                 /    \       /      \
               [ 23 ][ 7 ] [ 1 ]   [ 2 ] .
```
```java
// Plain monotonous stack template:
item = someItem; // ex: item in for loop
while (!stack.isEmpty() && (item.property compareTo stack.peek().property)) {
    topItem = stack.pop();
    // Do something with the topItem
}
stack.push(item);
```
## Example
- Maximum Binary Tree, Largest Rectable in Histogram, Maximal Rectangle (in 2D array)
- Expression Tree (Minimum Binary Tree)

# Queue



