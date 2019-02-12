## What is Binary Heap?
A Complete Binary Tree where items are sorted in a special order such that value in a parent node is greater(smaller) than the values in its two children nodes.

## Why array based representation for Binary Heap?
It can be easily represented as array and array based representation in space efficient.

If parent node is stored at index **i**
  - left child: **2 x i + 1**
  - right child: **2 x i + 2**

## Heap Sort Algorithm for sorting in increasing order
- Build a max heap from the input data.
- At this point, the largest item is stored at the root of the heap. 
  - Replace it with the last item of the heap followed by reducing the size of heap by 1
  - Finally, heapify the root of tree
- Repeat above steps while size of heap is greater then 1.

## How to build the heap?
Heapify procedure can be applied to a node only if its children nodes are heapified. 

So the heapification must be performed in the bottom up order.

```
Example:
Input data: 4, 10, 3, 5, 1
         4(0)
        /   \
     10(1)   3(2)
    /   \
 5(3)    1(4)

The numbers in bracket represent the indices in the array 
representation of data.

Applying heapify procedure to index 1:
         4(0)
        /   \
    10(1)    3(2)
    /   \
5(3)    1(4)

Applying heapify procedure to index 0:
        10(0)
        /  \
     5(1)  3(2)
    /   \
 4(3)    1(4)
The heapify procedure calls itself recursively to build heap
 in top down manner.
```
**Sudo Code**
```java
void heapSort(int[] arr, int n) {
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i); // create max heap
    }
    
    for (int i = n - 1; i >= 0; i--) {
        swap(arr[0], arr[i]); // swap first and last node
       
        heapify(arr, i, 0);   // create max heap on reduced array
    }
}
```
