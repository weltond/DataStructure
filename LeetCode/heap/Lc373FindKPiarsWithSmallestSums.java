/**
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]] 
Explanation: The first 3 pairs are returned from the sequence: 
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence: 
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
*/

// Approach 1:
class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // min heap
        // a[0] is nums1 value, a[1] is nums2 value
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] + a[1] - b[0] - b[1]));   
        
        List<int[]> res = new ArrayList<>();
        
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return res;
        
        for (int i = 0; i < nums1.length && i < k; i++) {
            // nums1 value, nums2 value, nums2 current index
            pq.offer(new int[] {nums1[i], nums2[0], 0});
        }
        
        while (k-- > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            
            // get i, j
            res.add(new int[]{cur[0], cur[1]});
            
            if (cur[2] == nums2.length - 1) continue;
            
            // offer i, j+1 because they are sorted
            pq.offer(new int[]{cur[0], nums2[cur[2] + 1], cur[2] + 1});
        }
        
        return res;
    }
}

// Approach 2: 33ms(55.28%)
class Solution {
    // 1. put k pair of (nums1[0], nums2[0..k-1]) into pq.
    // 2. poll from pq. offer(nums1[i+1],nums1[j]) into pq
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null) return new ArrayList();
        
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return new ArrayList();
        
        // min heap
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(k + 1, (o1, o2) -> {
            return (nums1[o1.v1] + nums2[o1.v2]) - (nums1[o2.v1] + nums2[o2.v2]); 
        });
        
        // offer k pairs into pq.
        for (int j = 0; j < nums2.length && j <= k; j++) {
            pq.offer(new Pair(0, j));
        }
        
        
        List<int[]> res = new ArrayList();
        
        while (k-- > 0 && !pq.isEmpty()) {
            Pair cur = pq.poll();
            int idx1 = cur.v1;
            int idx2 = cur.v2;
            res.add(new int[]{nums1[idx1], nums2[idx2]});
            if (idx1 + 1 == nums1.length) continue;
            
            pq.offer(new Pair(idx1 + 1, idx2));
        }
        
        return res;
        
    }
    
    class Pair{
        int v1;
        int v2;
        // index pair
        Pair(int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }
}
