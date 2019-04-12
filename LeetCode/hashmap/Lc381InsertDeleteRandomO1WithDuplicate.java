// https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/

/**
// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
*/
class RandomizedCollection {
    // 63ms (51.40%)
    Map<Integer, Set<Integer>> map; // set would be better than List?
    List<Integer> arr;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        arr = new ArrayList();  // stores val
        map = new HashMap();    // <val, list of position in arr>
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean flag = true;
        if (map.containsKey(val)) flag = false;
        
        map.computeIfAbsent(val, o -> new HashSet()).add(arr.size());
        
        arr.add(val);
        
        return flag;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    // ===== Approach 2: List =========
    public boolean remove(int val) {
        boolean contains = map.containsKey(val);
        if (!contains) {
            return false;
        }
        
        List<Integer> posList = map.get(val);
        int pos = posList.get(posList.size() -1);
        posList.remove(posList.size() -1);
        
        if (posList.size() ==0) {
            map.remove(val);
        }
        if (pos == list.size() -1) {
            list.remove(pos);
            return true;
        }
        
        int last = list.get(list.size() -1);
        List<Integer> lastList= map.get(last);
        lastList.remove(lastList.size() -1);
        lastList.add(0, pos);
        Collections.sort(lastList);
        list.set(pos, last);
        list.remove(list.size() -1);     
        
        return contains;
        
    }
    // ===== Approach 1: HashSet ==========
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        
        Set<Integer> positions = map.get(val);
        
        // if last val of arr is not the one we want to remove
        if (!map.get(val).contains(arr.size() - 1)) {
            // get one arbitrary position from its set.
            int valCurPos = positions.iterator().next();
            // remove it from position set.
            positions.remove(valCurPos); 
            // than add last pos of arr into it because we need to delete it anyway no matter what the last element is in arr
            positions.add(arr.size() - 1);
            
            // get last val of arr
            int lastVal = arr.get(arr.size() - 1);
            // remove its position in set
            map.get(lastVal).remove(arr.size() - 1);
            // swap 
            map.get(lastVal).add(valCurPos);
            
            arr.set(valCurPos, lastVal);
        }
        
        // else if last val of arr is the one we want to remove
        map.get(val).remove(arr.size() - 1);
        if (map.get(val).isEmpty()) map.remove(val);
        
        arr.remove(arr.size() - 1);
        
        return true;

    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int rn = new Random().nextInt(arr.size());
        
        return arr.get(rn);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
