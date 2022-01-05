// https://leetcode.com/problems/implement-strstr/

public int strStr(String haystack, String needle) {
        if (needle.isBlank()) return 0;

        int idx = 0, hsize = haystack.length(), nsize = needle.length();
        char nstart = needle.charAt(0);

        while (idx < hsize - nsize + 1) {
            char h = haystack.charAt(idx++);
            if (nstart == h) {
                if (compare(haystack, needle, idx)) {
                    return idx - 1;
                }
            }
        }

        return -1;
    }

    private boolean compare(String h, String n, int idx) {
        int hsize = h.length(), nsize = n.length();
        int nidx = 1;

        while (idx < hsize && nidx < nsize) {
            char ch = h.charAt(idx);
            char cn = n.charAt(nidx);

            if (ch != cn) break;

            idx++;
            nidx++;
        }

        return nidx == nsize;
    }

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        if (haystack == null || haystack.length() == 0) return -1;
        
        int hsize = haystack.length();
        int nsize = needle.length();
        int maxSize = hsize - nsize + 1;
        for (int i = 0; i < maxSize; i++) {
            // Look for the first matching character
            while (i < maxSize && haystack.charAt(i) != needle.charAt(0)) {
                i++;
            }
            
            // Found first character, compare the rest
            if (i < maxSize) {
                int j = i + 1;  // j is the next char of matching in haystack
                int k = 1;
                for (; k < nsize && needle.charAt(k) == haystack.charAt(j); j++, k++);
                
                if (k == nsize) return i;
            }
        }
        
        return -1;
    }
}
