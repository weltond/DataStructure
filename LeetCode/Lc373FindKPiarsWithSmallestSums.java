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
