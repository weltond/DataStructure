// https://leetcode.com/problems/random-pick-with-weight/

/**
Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
*/

class Solution {
    // ======== Method 2 TreeMap==========
    // 90ms (24.57%)
    /**
    E.g.
    [1,2,3]->{1:0, 3:1, 6:2}
    Say if rnd=4, it should return 2, because ceiling(4)=6 in our map, whose corresponding index is 2.
    */
    int cnt=0;
    TreeMap<Integer, Integer> map= new TreeMap<>();
    Random rnd= new Random();
    public Solution(int[] w) {
        for (int idx=0; idx<w.length; idx++){
            cnt+=w[idx];
            map.put(cnt, idx);
        }
    }
    
    public int pickIndex() {
        // int key= map.ceilingKey(rnd.nextInt(cnt)+1); don't forget to +1, because rand.nextInt(cnt) generate random integer in range [0,cnt-1]
        int key= map.higherKey(rnd.nextInt(cnt));
        return map.get(key);
    }
    
    // ======== Method 1 ==========
    // 71ms (71.97%)
    /**
    Use accumulated freq array to get idx.
    w[] = {2,5,3,4} => wsum[] = {2,7,10,14}
    then get random val random.nextInt(14)+1, idx is in range [1,14]

    idx in [1,2] return 0
    idx in [3,7] return 1
    idx in [8,10] return 2
    idx in [11,14] return 3
    then become LeetCode 35. Search Insert Position
    Time: O(n) to init, O(logn) for one pick
    Space: O(n)
    */
    int[] wsum;
    public Solution(int[] w) {
        for (int i = 1; i < w.length; i++) {
            w[i] += w[i - 1];
        }
        this.wsum = w;
    }
    
    public int pickIndex() {
        int val = new Random().nextInt(wsum[wsum.length - 1]) + 1;
        int left = 0, right = wsum.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (wsum[mid] == val) return mid;
            else if (wsum[mid] < val) left = mid + 1;
            else right = mid;
        }
        
        return left;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
