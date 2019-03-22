// https://leetcode.com/problems/reorder-log-files/

class Solution {
    // rules are:
    //  1. Letter-logs come before digit-logs
    //  2. Letter-logs are sorted alphanumerically, by content then identifier
    //  3. Digit-logs remain in the same order
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;   // not tied
                return split1[0].compareTo(split2[0]);  // tied. then compare identifier.
            }
            
            // if log1 is not digit (meaning log2 is digit) put log1 ahead of log2
            // if log1 is digit (meaning log2 might be digit or alpha):
            //      1) if log2 is also digit, they are tied. keep origin order
            //      2) if log2 is not digit, put log1 after log2
            return isDigit1 ? (isDigit2 ? 0 : 1): -1;
        });
        
        return logs;
    }
}
