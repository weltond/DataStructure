[source](https://leetcode.com/explore/learn/card/hash-table/185/hash_table_design_the_key/1128/)

## Design the Key Summary
Here are some takeaways about how to design the key for you.

### Sorted String / Array
When the order of each element in the string/array doesn't matter, you can use the **sorted string/array** as the key.

### Offset
If you only care about the offset of each value, usually the offset from the first value, you can use the **offset** as the key.

### Tree
In a tree, you might want to directly use the `TreeNode` as key sometimes. But in most cases, the **serialization of the subtree** might be
a better idea.

### Matrix
In a matrix, you might want to use the **row index** or **column index** as key.

### Sudoku
In a sudoku, you can combine the row index and the column index to identify which **block** this element belongs to.
```
(i, j) -> (i / 3) * 3 + j / 3
```

### Diagonal
Sometimes, in a matrix, you might want to aggregate the values in the same **diagonal line**.
```
Anti-Diagonal Order: (i, j) -> i + j

Diagnoal Order: (i, j) -> i - j
```
