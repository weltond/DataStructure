## [432. All O`one Data Structure](https://leetcode.com/problems/all-oone-data-structure/)

Implement a data structure supporting the following operations:

- **Inc(Key)** - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
- **Dec(Key)** - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
- **GetMaxKey()** - Returns one of the keys with maximal value. If no element exists, return an empty string "".
- **GetMinKey()** - Returns one of the keys with minimal value. If no element exists, return an empty string "".

Challenge: Perform all these in O(1) time complexity.


## Answer
### Method 1 :rocket: 20ms (93.84%)

```java
class AllOne {
    
    private class Bucket {
        int val;
        Set<String> keySet;
        Bucket next;
        Bucket pre;
        public Bucket(int val) {
            this.val = val;
            keySet = new HashSet();
        }
    }
    
    private Bucket head, tail;
    // accessing a specific Bucket in O(1)
    private Map<Integer, Bucket> valBucketMap;
    // keep track of value of each key in O(1)
    private Map<String, Integer> keyMap;
    
    /** Initialize your data structure here. */
    public AllOne() {
        head = new Bucket(Integer.MIN_VALUE); // tail is max
        tail = new Bucket(Integer.MAX_VALUE); // head is min
        head.next = tail;
        tail.pre = head;
        valBucketMap = new HashMap();
        keyMap = new HashMap();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (keyMap.containsKey(key)) {
            changeKey(key, 1);
        } else {
            keyMap.put(key, 1);
            if (head.next.val != 1) {
                addBucketAfter(new Bucket(1), head);
            }
            
            head.next.keySet.add(key);
            valBucketMap.put(1, head.next);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (keyMap.containsKey(key)) {
            int val = keyMap.get(key);
            if (val == 1) {
                keyMap.remove(key);
                removeKeyFromBucket(valBucketMap.get(val), key);
            } else {
                changeKey(key, -1);
            }
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail.pre == head ? "" : (String) tail.pre.keySet.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.next == tail ? "" : (String) head.next.keySet.iterator().next();
    }
    
    /*Help function to make change on given key according to offset*/
    private void changeKey(String key, int offset) {
        int cnt = keyMap.get(key);
        keyMap.put(key, cnt + offset);
        Bucket cur = valBucketMap.get(cnt);
        Bucket newBucket;
        
        if (valBucketMap.containsKey(cnt + offset)) {
            // target bucket exists
            newBucket = valBucketMap.get(cnt + offset);
        } else {
            // add new bucket
            newBucket = new Bucket(cnt + offset);
            valBucketMap.put(cnt + offset, newBucket);
            addBucketAfter(newBucket, offset == 1 ? cur : cur.pre);
        }
        
        newBucket.keySet.add(key);
        removeKeyFromBucket(cur, key);
    }
    
    private void removeKeyFromBucket(Bucket bucket, String key) {
        bucket.keySet.remove(key);
        if (bucket.keySet.size() == 0) {
            removeBucketFromList(bucket);
            valBucketMap.remove(bucket.val);
        }
    }
    private void removeBucketFromList(Bucket bucket) {
        bucket.pre.next = bucket.next;
        bucket.next.pre = bucket.pre;
        bucket.next = null;
        bucket.pre = null;
    }
    
    // add new bucket after preBukcet
    private void addBucketAfter(Bucket newBucket, Bucket preBucket) {
        newBucket.pre = preBucket;
        newBucket.next = preBucket.next;
        preBucket.next.pre = newBucket;
        preBucket.next = newBucket;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
```
