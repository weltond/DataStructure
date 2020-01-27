# LinkedList Related Problems
*

## 
### [19. Remove Nth Node From End of List](https://github.com/weltond/DataStructure/blob/master/LeetCode/linkedlist/19-Remove-Nth-Node-From-End-of-List.md)
- We can use dummy node to deliminate null pointers when **head** could be changed.

### [25. Reverse Nodes in K Group](https://github.com/weltond/DataStructure/blob/master/LeetCode/linkedlist/25-Reverse-Nodes-in-K-Group.md)
#### Recursion
- Base case is list length less than `k`.
- dfs to get the next new head.
- reverse current `k` group and connect it to the new head.

#### Iteration

### [61. Rotate List](https://github.com/weltond/DataStructure/blob/master/LeetCode/linkedlist/Lc61RotateList.java) 
#### Iteration
- Get the list length, mod `k` to make sure we don't have to repeat rotating.
- Get the new head and connect its end to old head.
- Take care of edge case. `[1] 0`, `[1] 1`. This can be solved either
  - checking new head next is null or not
  - `k % len == 0`. (**Preferred**).
  
### [82.](https://github.com/weltond/DataStructure/blob/master/LeetCode/linkedlist/Lc82RemoveDupfromSortedListII.java) 
#### Iteration
- Take care of edge case like `[1,1,2]` or `[1,2,2]`.
- Use dummy node
#### Recursion
- Less code than iteration
