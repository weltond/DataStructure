// https://leetcode.com/problems/group-anagrams/

class Solution {
    // 38ms (15.02%)
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList();
        
        Map<String, List<String>> map = new HashMap();
        
        for (String str : strs) {
            char[] arr = new char[26];
            for (int i = 0, len = str.length(); i < len; i++) {
                arr[str.charAt(i) - 'a']++;
            }
            //System.out.println(new String(arr));
            
            String s = new String(arr);
            map.computeIfAbsent(s, o -> new ArrayList<String>()).add(str);
        }
        
        return new ArrayList<List<String>>(map.values());
//         for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//             res.add(entry.getValue());
//         }
        
//         return res;
    }
}
