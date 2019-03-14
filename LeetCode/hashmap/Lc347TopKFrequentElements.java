// https://leetcode.com/problems/top-k-frequent-elements/

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null) return new ArrayList();
        
        Map<Integer, Integer> map = new HashMap();  // <val, freq>
        
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // ======== Method 2: Bucket ========
        // 10ms
        List[] bucketFreq = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (bucketFreq[freq] == null) bucketFreq[freq] = new ArrayList();
            bucketFreq[freq].add(key);
        }
        
        List<Integer> res = new ArrayList();
        
        for (int i = bucketFreq.length - 1; i >= 0 && res.size() < k; i--) {
            if (bucketFreq[i] != null) {
                for (int val : (ArrayList<Integer>)bucketFreq[i]) {
                    res.add(val);
                }
            }
        }
        
        return res;
        // ===========================================================================
        
        // ======== Method 1: PQ =========
        // 42ms
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k + 1, (o1, o2) -> map.get(o1) - map.get(o2));   // min heap
        
        int cnt = 0;
        for (int val : map.keySet()) {
            pq.add(val);
            cnt++;
            if (cnt > k) {
                pq.poll();
            }
        }
        
        List<Integer> res = new LinkedList();
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        Collections.reverse(res);
        return res;
    }
    // ===========================================================================
}
