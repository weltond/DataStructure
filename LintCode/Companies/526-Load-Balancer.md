## [526. Load Balancer](https://www.lintcode.com/problem/load-balancer/description?_from=ladder&&fromId=14)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Implement a load balancer for web servers. It provide the following functionality:

- Add a new server to the cluster => `add(server_id)`.
- Remove a bad server from the cluster => `remove(server_id)`.
- Pick a server in the cluster randomly with equal probability => `pick()`.
- At beginning, the cluster is empty. When `pick()` is called you need to randomly return a server_id in the cluster.


Example 1:

```
Input:
  add(1)
  add(2)
  add(3)
  pick()
  pick()
  pick()
  pick()
  remove(1)
  pick()
  pick()
  pick()
Output:
  1
  2
  1
  3
  2
  3
  3
Explanation: The return value of pick() is random, it can be either 2 3 3 1 3 2 2 or other.
```

## Answer
### Method 1 - HashTable - :rabbit: 5604(72.40%)

- [LeetCode 380 Insert, Delete, Get Random O(1)](https://github.com/weltond/DataStructure/blob/master/LeetCode/hashmap/Lc380InsertDeleteRandomO1.java) 

```java
public class LoadBalancer {
    private List<Integer> list;
    private Map<Integer, Integer> map;  // id, pos
    public LoadBalancer() {
        // do intialization if necessary
        list = new ArrayList();
        map = new HashMap();
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        list.add(server_id);
        map.put(server_id, list.size() - 1);
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        if (!map.containsKey(server_id)) return;
        
        int pos = map.get(server_id);
        
        int len = list.size();
        
        map.put(list.get(len - 1), pos);
        list.set(pos, list.get(len - 1));
        
        list.remove(len - 1);
        map.remove(server_id);
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        Random r = new Random();
        int p = Math.abs(r.nextInt()) % (list.size());
        
        return list.get(p);
    }
}
```
