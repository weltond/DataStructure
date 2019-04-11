// https://leetcode.com/problems/insert-delete-getrandom-o1/

class RandomizedSet {
    Map<Integer, Integer> map;  // map is used by insert() and remove(). <val, idx of arr>
    List<Integer> arr;  // arr is used by getRandom()
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap();
        arr = new ArrayList();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        
        map.put(val, arr.size());   // map stores value's index of arrList
        arr.add(val);   // arrList stores the value. 
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        
        int idx = map.get(val);
        // swap cur idx and the last one of ArrayList
        int lastVal = arr.get(arr.size() - 1);
        map.put(lastVal, idx);
        arr.set(idx, lastVal);
        arr.remove(arr.size() - 1); // remove the old last one
        map.remove(val);    // IMPORTANT! Remove at last step because the val and last val maybe the same.
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int randomIdx = new Random().nextInt();

        return arr.get(Math.abs(randomIdx) % arr.size());
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
