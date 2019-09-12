## [981. Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/)

Create a timebased key-value store class `TimeMap`, that supports two operations.

1. `set(string key, string value, int timestamp)`

- Stores the key and value, along with the given timestamp.

2. `get(string key, int timestamp)`

- Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
- If there are multiple such values, it returns the one with the largest timestamp_prev.
- If there are no values, it returns the empty string ("").
 

Example 1:
```
Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
Output: [null,null,"bar","bar",null,"bar2","bar2"]
Explanation:   
TimeMap kv;   
kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
kv.get("foo", 1);  // output "bar"   
kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
kv.set("foo", "bar2", 4);   
kv.get("foo", 4); // output "bar2"   
kv.get("foo", 5); //output "bar2"   
```
Example 2:
```
Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
Output: [null,null,null,"","high","high","low","low"]
```

Note:

1. All key/value strings are lowercase.
2. All key/value strings have length in the range [1, 100]
3. The timestamps for all TimeMap.set operations are strictly increasing.
4. 1 <= timestamp <= 10^7
5. TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.

## Answer
### Method 1 - TreeMap - :turtle: 265ms (19.42%)
```java
class TimeMap {

    Map<String, TreeMap<Integer, String>> map;
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap();
    }
    
    public void set(String key, String value, int timestamp) {        
        map.computeIfAbsent(key, o -> new TreeMap()).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        
        TreeMap<Integer, String> tree = map.get(key);
        Integer time = tree.floorKey(timestamp);
        return time != null ? tree.get(time) : "";
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */


// ============ Below pass 44/45 but TLE ==========
/**
class TimeMap {

    Map<String, LinkedList<ValTime>> map;
    public TimeMap() {
        map = new HashMap();
    }
    
    public void set(String key, String value, int timestamp) {
        
        ValTime vt = new ValTime(value, timestamp);
        
        map.computeIfAbsent(key, o -> new LinkedList<ValTime>()).add(vt);
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        LinkedList<ValTime> list = map.get(key);
        int size = list.size();
        
        int firstTime = list.get(0).getT();
        if (timestamp < firstTime) return "";
        
        int l = 0, r = size - 1;
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            ValTime vt = list.get(mid);
            if (vt.getT() == timestamp) return vt.getV();
            else if (vt.getT() < timestamp) l = mid;
            else r = mid - 1;
        }
        
        int lt = list.get(l).getT(), rt = list.get(r).getT();
        return lt <= timestamp && timestamp < rt ? list.get(l).getV() : list.get(r).getV();
    }
}

class ValTime {
    String val;
    int t;
    public ValTime(String val, int t) {
        this.val = val;
        this.t = t;
    }
    public int getT() {
        return this.t;
    }
    public String getV() {
        return this.val;
    }
}
*/


```
