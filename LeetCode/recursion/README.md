## Recurrence Relation
There are two important things that one needs to figure out before implementing a recursive function:
- `recurrence relation`: the relationship between the result of a problem and the result of its subproblems.
- `base case`: the case where one can compute the answer directly without any further recursion calls.
  - Sometimes, the base case are also called **bottom** case, since they are often the cases where the problem has been reduced to the 
  minimal scale, i.e. the bottom, if we consider that dividing the problem into subproblems is in a **TOP-DOWN** manner.
  

> Once we figure out the above two elements, to implement a recursive function we simply call the function itself according to the 
**recurrence relation** until we reach the **base case**.

## Memoization
### Duplicate Calculation in Recursion
Recursion is often an intuitive and powerful way to implement an algorithm. However, it might bring some undesired penalty to the performance, 
e.g. duplicate calculations, if we do not use it wisely.

### Memoization
To eliminate the duplicate calculation, one of the ideas would be to store the intermediate results in the cache so that we could reuse them
later without re-calculation.

The idea is also known as **memoization**, which is a technique that is frequently used together with recursion.

> **Memoization** is an optimization technique used primarily to speed up computer programs by
**sorting** the results of expensive function calls and returning the cached result when the
same inputs occur again.

We could use a hash table to keep track of the result of each F(n) with n as the key. 

The hash table serves as a cache that saves us from duplicate calculations.

The memoization technique is a good example that demonstrates how one can reduce compute time in exchange for some additional space.

> You can also try to make memoization more general and non-intrusive, i.e. applying memoization without changing the original function.
(Hint: one can refer to a design pattern called [DECORATOR](https://github.com/weltond/DataStructure/tree/master/Head-First-Design-Patterns-master/java/decorator))

## Complexity Analysis
### Time Complexity
> Given a recursion algorithm, its time complexity O(T) is typically the product of the **number of recursion invocations** (denoted as **R**)
 and the **time complexity of calculation** (denoted as O(s)) that incurs along with each recursion call:
 ```
 O(T) = R * O(s)
 ```
#### Execusion Tree
For recursive functions, it is rarely the case that the number of recursion calls happens to be linear to the size of input.

For example, one might recall the example of Fibonacci number, whose recurrence relation is defined as `f(n) = f(n-1) + f(n-2)`.
At first glance, it does not seem straitforward to calculate the number of recursion invocations during the execution of the function.

> In this case, it is better resort to the **execution tree**, which is a tree that is used to denote the execution flow of a recursive
function in particular. Each node in the tree represents an invocation of the recursive function. Therefore, the total number of nodes
in the tree corresponds to the number of recursion calls during the execution.

The execution tree of a recursive function would form an **n-ary tree**, with `n` as the number of times recursion appears in the 
recurrence relation. 

For instance, the execution of the Fibonacci function would perform a **binary tree**.

In a full binary tree with `n` levels, the total number of nodes would be `2^n - 1`. Therefore, the upper bound (though not tight)
for the number of recursion in `f(n)` would be `2^n - 1`, as well. As a result, we can estimate that the time complexity for f(n) would
be `O(2^n)`.

#### Memoization
It is often applied to optimize the time complexity of recursion algorithms. By caching and reusing the intermediate results, memoization
can greatly reduce the number of recursion calls, i.e. reducing the number of branches in the execution tree. **One should take this 
reduction into account when analyzing time complexity for recursion algorithms with memoization.**

For instance, Fibonacci number. With memoization, we save the result of Fibonacci number for each index `n`. We are assured that the 
calculation for each Fibonacci number would occur only once. And we know, from the recurrence relation, the `f(n)` would depend on all
`n-1` precedent numbers. As a result, the recursion to calculate f(n) would be invoked `n-1` times to calculate all the precedent numbers
that it depends on.

Now, we can simply apply the formula we introduced above to calculate the time complexity, which is `O(1) * n = O(n)`. Memoization not only
optimize the time complexity of algorithm, but also simplifies the calculation of time comlexity.

### Space Complexity
> There are mainly two parts of the space consumption that one should bear in mind when calculating the space complexity of a recursion
algorithm: **recursion related** and **non-recursion related space**.

#### Recursion Related Space
The recursion related space refers to memory cost that is incurred directly by the recursion, i.e. the stack to keep track of recursive
function calls.

In order to complete a typical function call, the system should allocate some space in the stack to hold 3 important pieces of information:
1. The returning address of the function call. Once the function call is completed, the program should know where to return to, i.e. the point
before the function call.
2. The parameters that are passed to the function call.
3. The local variables within the function call.

This space in the stack is the minimal cost that is incurred during a function call. However, once the function call is done, the space
would be freed.

For recursion algorithms, the function calls would chain up successively until they reach a **base case** (a.k.a bottom case). This implies
that the space that is used for each function call would also accumulate.

> For a recursion algorithm, if there is no other memory consumption, then this recursion incurred space would be the space upper-bound
of the algorithm.

It is due to these recursion related space consumption that sometimes one might run into a situation called **stack overflow**, where
the stack allocated for a program reaches its maximum space limit and the program ends up with failure.

Therefore, when designing a recursion algorithm, one should carefully evaluate if there is a possibility of stack overflow when the 
input scales up.

#### Non-Recursion Related Space
As suggested by the name, the non-recursion related space refers to the memory space that is not directly related to recursion, 
which typically includes the space (normally in heap) that is allocated for the global variables.

Recursion or not, you might need to store the input of the problem as global variables, before any subsequent function calls.

And you might need to save the intermediate results from the recursion calls as well. The latter is also known as **memoization**.

For example, in the recursion algorithm with memoization to solve the Fibonacci number problem, we used a map (or array) to keep track of 
all intermediate Fibonacci numbers that occurred during the recursion calls.

Therefore, in the space complexity analysis, we should take the space cost incurred by the memoization into consideration.  

## Tail Recursion
A tail recursion can be **exempted** from space overhead.
> **Tail recursion** is a recursion where the recursive call is the final instruction in the recursion function. And there should be
only **ONE** recursive call in the function.

Here is an example shows difference between non-tail and tail recursion. Notice that in the non-tail recursion example there is an 
extra computation after the very last recursive call.
```java
public class Main {
    
  private static int helper_non_tail_recursion(int start, int [] ls) {
    if (start >= ls.length) {
      return 0;
    }
    // not a tail recursion because it does some computation after the recursive call returned.
    return ls[start] + helper_non_tail_recursion(start+1, ls);
  }

  public static int sum_non_tail_recursion(int [] ls) {
    if (ls == null || ls.length == 0) {
      return 0;
    }
    return helper_non_tail_recursion(0, ls);
  }

  //---------------------------------------------

  private static int helper_tail_recursion(int start, int [] ls, int acc) {
    if (start >= ls.length) {
      return acc;
    }
    // this is a tail recursion because the final instruction is the recursive call.
    return helper_tail_recursion(start+1, ls, acc+ls[start]);
  }
    
  public static int sum_tail_recursion(int [] ls) {
    if (ls == null || ls.length == 0) {
      return 0;
    }
    return helper_tail_recursion(0, ls, 0);
  }
}
```
The benefit of having tail recursion is that it could avoid accumulation of stack overheads during the recursive calls, since the
system could reuse a fixed amount space in the stack for each recursive call.

Note that in the tail recursion, we know that as soon as we return from the recursive call, we are going to immediately return as well,
so we can skip the entire chain of recursive calls returning and return straight to original caller. That means we don't need a call
stack at all for all of the recursive calls, which saves us space.

A tail recursion function can be executed as non-tail-recursion functions, i.e. with piles of call stacks, without impact on the result. 
Often, the compiler recognizes tail recursion pattern,and optimizes its execution. However, not all programming languages support this optimization.

For instance, C, C++ support the optimization of tail recursion functions. 
On the other hand, Java and Python do not support tail recursion optimization.

Source: LeetCode Explore
