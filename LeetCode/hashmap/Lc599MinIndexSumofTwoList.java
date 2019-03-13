// https://leetcode.com/problems/minimum-index-sum-of-two-lists/submissions/

// 10ms. 100%
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1 == null || list2 == null) return null;
        
        Map<String, Integer> map = new HashMap();
        
        int least = Integer.MAX_VALUE, sum = 0;
        
        for (int i = 0; i < list1.length; i++) {
            String s = list1[i];
            map.put(s, i);
        }
        
        List<String> res = new ArrayList();
        
        for (int i = 0; i < list2.length && i <= least; i++) {
            String s2 = list2[i];
            if (map.containsKey(s2)) {
                sum = map.get(s2) + i;
                
                if (sum < least) {
                    res.clear();
                    res.add(list2[i]);
                    least = sum;
                } else if (sum == least) {
                    res.add(list2[i]);
                }
            }
        }

        return res.toArray(new String[res.size()]);
        
    }
}
