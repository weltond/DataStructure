import java.util.HashMap;
import java.util.Map;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Network rank is total number of roads that are connected to either of two cities.
 *
 * Given two arrays A, B consisting of M integers each and an integer N, where
 * A[i] and B[i] are cities at the two ends of the i-th road.
 * return the maximal network rank.
 *
 * [1,2,3,4], [2,3,1,4] N = 4
 * Return 4.
 * The chosen city may be 2 and 3.
 */
public class MaxNetworkRank {
    public int cntEdges(int[] a, int[] b, int n) {
        Map<Integer, Integer> map = new HashMap();  //city, cnt
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
            map.put(b[i], map.getOrDefault(b[i], 0) + 1);
        }

        int res = 0;
        for (int i = 0; i < a.length; i++) {
            // extra -1 as the road is counted twice
            res = Math.max(res, map.get(a[i]) + map.get(b[i]) - 1); // a[i] and b[i] are garuateed connected.
        }

        return res;
    }
}
