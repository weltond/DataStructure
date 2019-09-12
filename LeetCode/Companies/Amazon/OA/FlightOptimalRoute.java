package amazonOA;

import java.util.*;

/**
 * Return a list of pairs of integers representing the pairs of IDs of foward and return shipping routes that
 * optimally utilize the given aircraft. If no route, return an empty list.
  Input:
    maxTravelDist = 9000
    forwardRouteList = [[4, 1000], [2, 3000], [3, 4000], [5, 4000], [1, 5000]]
    returnRouteList = [[1, 2000], [3, 5000], [2, 5000], [4, 6000]]
  Output:
    [[2, 4], [3, 2], [3, 3], [5, 3], [5, 2]]
 */

public class FlightOptimalRoute {
    public List<List<Integer>> PrimeMaxProfit(int maxTravelDist, List<List<Integer>> forwardRouteList, List<List<Integer>> returnRouteList) {
        List<List<Integer>> res = new ArrayList<>();
        if (forwardRouteList == null || returnRouteList == null) return res;

        int forLen = forwardRouteList.size(), retLen = returnRouteList.size() ;
        if (maxTravelDist == 0 || forLen == 0 || retLen == 0) {
            return res;
        }

        Collections.sort(forwardRouteList, ((o1, o2) -> o1.get(1) - o2.get(1)));
        Collections.sort(returnRouteList, ((o1, o2) -> o1.get(1) - o2.get(1)));


        // ============== Method 2 BS ================
        int l = 0, r = retLen - 1, diff = Integer.MAX_VALUE, sum;
        while (l < forLen && r >= 0) {
            sum = forwardRouteList.get(l).get(1) + returnRouteList.get(r).get(1);

            if (maxTravelDist - sum >= 0 && maxTravelDist - sum <= diff) {
                if (maxTravelDist - sum < diff) {
                    diff = maxTravelDist - sum;
                    res = new ArrayList<>();
                }
                res.add(Arrays.asList(forwardRouteList.get(l).get(0), returnRouteList.get(r).get(0)));
            }

            if (sum >= maxTravelDist) {
                int tmp = l;
                while (tmp + 1 < forLen && forwardRouteList.get(tmp).get(1).compareTo(forwardRouteList.get(tmp + 1).get(1)) == 0) {
                    res.add(Arrays.asList(forwardRouteList.get(tmp + 1).get(0), returnRouteList.get(r).get(0)));
                    tmp++;
                }
                r--;
            } else {
                l++;
            }
        }

        // =========== Method 1 ==========
//        int l = 0, r = retLen - 1, target = Integer.MIN_VALUE;
//        for (int i = 0; i < forLen; i++) {
//            r = retLen - 1;
//            while (r >= 0 && forwardRouteList.get(i).get(1) + returnRouteList.get(r).get(1) > maxTravelDist) {
//                r--;
//            }
//            if (r < 0) continue;
//
//            int tmp = forwardRouteList.get(i).get(1) + returnRouteList.get(r).get(1);
//            if (target < tmp) {
//                target = tmp;
//                System.out.println("< " + tmp + ", " + i + " - " + r);
//                if (res.size() > 0) res.clear();
//                res.add(Arrays.asList(forwardRouteList.get(i).get(0), returnRouteList.get(r).get(0)));
//            } else if (target == tmp) {
//                System.out.println("= " + tmp + ", " + i + " - " + r);
//                res.add(Arrays.asList(forwardRouteList.get(i).get(0), returnRouteList.get(r).get(0)));
//            }
//
//            while (r - 1 >= 0 && returnRouteList.get(r).get(1).compareTo(returnRouteList.get(r - 1).get(1)) == 0) {
//                System.out.println("E " + tmp + ", " + i + " - " + (r - 1));
//                res.add(Arrays.asList(forwardRouteList.get(i).get(0), returnRouteList.get(r - 1).get(0)));
//                r--;
//            }
//        }

        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> forward = new ArrayList<>();
        List<List<Integer>> returnL = new ArrayList<>();

        int max = 9000;
        forward.add(Arrays.asList(1, 5000));
        forward.add(Arrays.asList(3, 4000));
        forward.add(Arrays.asList(2, 3000));
        forward.add(Arrays.asList(4, 1000));
        forward.add(Arrays.asList(5, 4000));

        returnL.add(Arrays.asList(1, 2000));
        returnL.add(Arrays.asList(3, 5000));
        returnL.add(Arrays.asList(2, 5000));
        returnL.add(Arrays.asList(4, 6000));
        //returnL.add(Arrays.asList(5, 4000));
        //returnL.add(Arrays.asList(6, 4000));

//        int max = 7000;
//        forward.add(Arrays.asList(1, 2000));
//        forward.add(Arrays.asList(2, 4000));
//        forward.add(Arrays.asList(3, 6000));
//
//        returnL.add(Arrays.asList(1, 2000));  // return [[2,1]]

//        int max = 10000;
//        forward.add(Arrays.asList(1, 3000));
//        forward.add(Arrays.asList(2, 5000));
//        forward.add(Arrays.asList(3, 7000));
//        forward.add(Arrays.asList(4, 10000));
//        returnL.add(Arrays.asList(1, 2000));
//        returnL.add(Arrays.asList(2, 3000));
//        returnL.add(Arrays.asList(3, 4000));
//        returnL.add(Arrays.asList(4, 5000));    // return [[2,4],[3,2]]


        List<List<Integer>> res = new FlightOptimalRoute().PrimeMaxProfit(max, forward, returnL);
        System.out.println(res);
    /*Output
    [[4, 1000], [2, 3000], [3, 4000], [5, 4000], [1, 5000]]
    [[1, 2000], [3, 5000], [2, 5000], [4, 6000]]
    should be [[2, 4], [3, 2], [3, 3], [5, 3], [5, 2]]
    */
    }
}
