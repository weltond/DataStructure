// https://leetcode.com/problems/next-greater-element-i/

class Solution {
    // ============== Method 1: Monotonic Stack ===============
    // 3ms (92.00%)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        if (nums1 == null || nums2 == null) return res;
        
        Map<Integer, Integer> map = new HashMap();  // <num, res>
        Deque<Integer> stack = new LinkedList();
        for (int len = nums2.length, i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                stack.pop();
            }
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }
        
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        
        return res;
    }
}
