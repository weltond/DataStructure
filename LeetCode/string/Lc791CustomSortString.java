// https://leetcode.com/problems/custom-sort-string/

/**
Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
*/
class Solution {
    
    // Approach 2:
    // 0ms
    public String customSortString(String s, String t) {
        int[] cnts = new int[26];
        for (char c: t.toCharArray()) {
            cnts[c - 'a']++;
        }
        
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            while (cnts[c - 'a']-- > 0) {
                res.append(c);
            }
        }
        
        for (int i = 0; i < 26; i++) {
            while (cnts[i]-- > 0) {
                res.append((char)(i + 'a'));
            }
        }
        
        return res.toString();
    }
    // Approach 1:
    // 2ms (83.40%)
//     public String customSortString(String s, String t) {
//         Map<Character, Integer> map = new HashMap();
        
//         for (char c : t.toCharArray()) {
//             map.put(c, map.getOrDefault(c, 0) + 1);
//         }
        
//         char[] arr = new char[t.length()];
//         int j = 0;
//         for (int i = 0; i < s.length(); i++) {
//             char c = s.charAt(i);
//             if (map.containsKey(c)) {
//                 int freq = map.get(c);
//                 for (; freq-- > 0; j++) {
//                     arr[j] = c;
//                 }
//                 map.remove(c);
//             }
//         }
//         for (Map.Entry<Character, Integer> e : map.entrySet()) {
//             char c = e.getKey();
//             int freq = e.getValue();
//             for (int i = 0; i < freq; i++) {
//                 arr[j++] = c; 
//             }
//         }
        
//         return new String(arr);
//     }
}
