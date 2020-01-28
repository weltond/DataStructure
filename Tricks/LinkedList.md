# LinkedList Related Problems
* Recursion
* Iteration
* Two Pointers

## Find Mid

```java
private ListNode findMid(ListNode head) {
        ListNode pre = null, slow = head, fast = head;
        
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        if (pre != null) pre.next = null;
        
        return slow;
    }
```

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
  
### [82. Remove Duplicates from Sorted List II](https://github.com/weltond/DataStructure/blob/master/LeetCode/linkedlist/Lc82RemoveDupfromSortedListII.java) 
#### Iteration
- Take care of edge case like `[1,1,2]` or `[1,2,2]`.
- Use dummy node
#### Recursion
- Less code than iteration

### [109. Convert Sorted List to Binary Search Tree]()
#### Find Middle
#### Inorder Simulation

### [430. Flatten a Multilevel Doubly LinkedList](https://github.com/weltond/DataStructure/blob/master/LeetCode/linkedlist/430-Flatten-MultiDoubly-LinkedList.md)
#### Recursion
- Recursion should return the head.
- Because of this, when there is a node has child, recurse it and it will return a head.
- In order to connect the child to parent, we should iterate the child to find the last element and then connect it to cur's next.

### [445. Add Two Numbers II](https://github.com/weltond/DataStructure/blob/master/LeetCode/linkedlist/445-add-two-numbers-ii.md)
#### Recursion
- Get each list length
- Recursive calculate add when their length diff is `0`.
- Base case is reaching the end of the list. (It is guruanteed that both reach the end at the same time becuase we use `diff` to keep track of the length)

#### Stack
- Use two stacks to store each list value


