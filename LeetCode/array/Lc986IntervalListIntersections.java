// https://leetcode.com/problems/interval-list-intersections/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

// 6ms (28.08%)
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int idx1 = 0, idx2 = 0, idx = 0;
        int[][] res = new int[firstList.length + secondList.length][2];
        

        while (idx1 < firstList.length && idx2 < secondList.length) {
            int[] a = firstList[idx1];
            int[] b = secondList[idx2];

            // no overlap: a[1] < b[0] || b[1] < a[0]
            // overlap: 
            if (a[1] >= b[0] && b[1] >= a[0]) {
                res[idx++] = new int[]{Math.max(a[0], b[0]), Math.min(a[1], b[1])};
            }

            // move to next
            if (a[1] < b[1]) {
                idx1++;
            } else {
                idx2++;
            }
        }

        return Arrays.copyOf(res, idx);
    }
}

class Solution {
    public int[][] intervalIntersection(int[][] a, int[][] b) {
        int i = 0, j = 0;
        
        int[][] res = new int[a.length + b.length][];
        int idx = 0;
        while (i < a.length && j < b.length) {
            int start = Math.max(a[i][0], b[j][0]);
            if (a[i][1] == b[j][1]) {
                res[idx++] = new int[]{start, a[i][1]};
                i++;
                j++;
            } else if (a[i][1] > b[j][1]) {
                if (start <= b[j][1])
                    res[idx++] = new int[]{start, b[j][1]};
                j++;
            } else {
                if (start <= a[i][1])
                    res[idx++] = new int[]{start, a[i][1]};
                i++;
            }
        }
        
        return Arrays.copyOf(res, idx);
    }
}
class Solution {
    public int[][] intervalIntersection(int[][] a, int[][] b) {
        List<int[]> ans = new ArrayList();
        
        int i = 0, j = 0;
        
        while (i < a.length && j < b.length) {
            // Let's check if A[i] intersects B[j].
            // lo - the startpoint of the intersection
            // hi - the endpoint of the intersection
            int lo = Math.max(a[i][0], b[j][0]);
            int hi = Math.min(a[i][1], b[j][1]);
            // System.out.println(lo+","+hi);
            if (lo <= hi) {
                ans.add(new int[]{lo, hi});
            }
            
            if (a[i][1] < b[j][1])
                i++;
            else 
                j++;
        }
        
        int len = ans.size();
        int[][] res = new int[len][];
        
        for (int k = 0; k < len; k++) {
            res[k] = ans.get(k);
        }
        
        return res;
    }
}

class Solution {
    // Idea:
    //      If A[0] has smallest endpoint, it can only intersect B[0]. After, we can discard A[0] since it cannot intersect anything.
    //      Similar to B[0].
    //      So we use two pointers i & j to virtually manage "discarding" A[0] or B[0] repeatedly
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        if (A == null || B == null) return new Interval[]{};
        
        int i = 0, j = 0;   // index to pick which one to compare
        
        List<Interval> res = new ArrayList();
        while (i < A.length && j < B.length) {
            int lo = Math.max(A[i].start, B[j].start);
            int hi = Math.min(A[i].end, B[j].end);
            
            if (lo <= hi) {
                res.add(new Interval(lo, hi));
            }
            
            // smallest end should not be involved in next round of compare
            if (A[i].end < B[j].end) {
                i++;
            } else {
                j++;
            }
        }
        
        return res.toArray(new Interval[0]);
    }
}
