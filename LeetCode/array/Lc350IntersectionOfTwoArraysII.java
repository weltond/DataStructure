// https://leetcode.com/problems/intersection-of-two-arrays-ii/

class Solution {
    // ========= Method 2: Two Pointer ==========
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 > len2) return intersect(nums2, nums1);
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        List<Integer> res = new ArrayList();
        int l = 0, r = 0;
        
        while (l < len1 && r < len2) {
            if (nums1[l] == nums2[r]) {
                res.add(nums1[l]);
                l++; r++;
            } else if (nums1[l] > nums2[r]) {
                r++;
            } else {
                l++;
            }
        }
        
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        
        return arr;
        //return res.stream().mapToInt(x->x).toArray(); // SUPER SLOW
    }
    
    // ========= Method 1: Hash Map ===========
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        
        // <value, freq>
        Map<Integer, Integer> map = new HashMap();
        
        if (nums1.length > nums2.length) return intersect(nums2, nums1);
        
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }
        
        List<Integer> res = new ArrayList();
        
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                int frq = map.get(nums2[i]);
                if (frq <= 0) continue;
                res.add(nums2[i]);
                map.put(nums2[i], frq - 1);
            }
        }
        
        return res.stream().mapToInt(x -> x).toArray();
    }
}
