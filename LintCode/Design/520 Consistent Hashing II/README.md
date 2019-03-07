## Description
In Consistent Hashing I we introduced a relatively simple consistency hashing algorithm. This simple version has two defects：

1. After adding a machine, the data comes from one of the machines. The read load of this machine is too large, which will affect the normal service.

2. When adding to 3 machines, the load of each server is not balanced, it is 1:1:2

In order to solve this problem, the concept of micro-shards was introduced, and a better algorithm is like this：

1. From 0~359 to a range of 0 ~ n-1, the interval is connected end to end and connected into a circle.
2. When joining a new machine, randomly choose to sprinkle k points in the circle, representing the k micro-shards of the machine.
3. Each data also corresponds to a point on the circumference, which is calculated by a hash function.
4. Which machine belongs to which data is to be managed is determined by the machine to which the first micro-shard point that is clockwise touched on the circle is corresponding to the point on the circumference of the data.

n and k are typically 2^64 and 1000 in a real NoSQL database.

Implement these methods of introducing consistent hashing of micro-shard.

1. `create(int n, int k)`
2. `addMachine(int machine_id)` // add a new machine, return a list of shard ids.
3. `getMachineIdByHashCode(int hashcode)` // return machine id

```
When n is 2^64, there is almost no repetition in the interval within this interval.

However, in order to test the correctness of your program, n may be small in the data, so you must ensure that the k random numbers you generate will not be duplicated.

LintCode does not judge the correctness of your returnMachine's return (because it is a random number), it will only judge the correctness of your getMachineIdByHashCode result based on the result of the addMachine you return.
```

Example
Example 1:
```
Input:
  create(100, 3)
  addMachine(1)
  getMachineIdByHashCode(4)
  addMachine(2)
  getMachineIdByHashCode(61)
  getMachineIdByHashCode(91)
Output:
  [77,83,86]
  1
  [15,35,93]
  1
  2
```

Example 2:
```
Input:
  create(10, 5)
  addMachine(1)
  getMachineIdByHashCode(4)
  addMachine(2)
  getMachineIdByHashCode(0)
  getMachineIdByHashCode(1)
  getMachineIdByHashCode(2)
  getMachineIdByHashCode(3)
  getMachineIdByHashCode(4)
  getMachineIdByHashCode(5)
Output:
  [2,3,5,6,7]
  1
  [0,1,4,8,9]
  2
  2
  1
  1
  2
  1
```

## TO DO
```java
public class Solution {
    /*
     * @param n: a positive integer
     * @param k: a positive integer
     * @return: a Solution object
     */
    public static Solution create(int n, int k) {
        // Write your code here
    }

    /*
     * @param machine_id: An integer
     * @return: a list of shard ids
     */
    public List<Integer> addMachine(int machine_id) {
        // write your code here
    }

    /*
     * @param hashcode: An integer
     * @return: A machine id
     */
    public int getMachineIdByHashCode(int hashcode) {
        // write your code here
    }
}
```

