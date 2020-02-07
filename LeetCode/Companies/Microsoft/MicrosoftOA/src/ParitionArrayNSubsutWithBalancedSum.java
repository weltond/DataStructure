import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Give you one sorted array, please put them into n buckets, we need to ensure we get n sub array with approximately equal weights.
 * Example;
 * input {1, 2, 3, 4, 5} n = 3
 * output [[[5],[1,4],[2,3]];
 */
public class ParitionArrayNSubsutWithBalancedSum {
    /**
     * Wrong solution when [10,10,9,9,2] n = 2
     * @param nums
     * @param n
     * @return
     */
    public static List<List<Integer>> solution(int[] nums, int n) {
        int[] sum = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> sum[o1] - sum[o2]);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
            pq.add(i);
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            int c = pq.poll();
            res.get(c).add(nums[i]);
            sum[c] += nums[i];
            pq.add(c);
        }
        System.out.println(res);
        return res;
    }

    public static List<List<Integer>> sol(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int each = sum / k;
        List<List<Integer>> res = new ArrayList<>();

        dfs(nums, each, 0, k, each, new boolean[nums.length], new ArrayList<>(), res);
        System.out.println(res);
        return res;
    }
    private static void dfs(int[] nums, int subSum, int start, int k, int sum, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        if (k == 0) return;

        if (subSum == 0) {
            res.add(new ArrayList(list));
            System.out.println(list);
            System.out.println(k);
            System.out.println("=====");
            dfs(nums, sum, 0, k - 1, sum, used, new ArrayList(), res);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (used[i] || subSum < nums[i]) continue;
            used[i] = true;
            list.add(nums[i]);
            dfs(nums, subSum - nums[i], i + 1, k, sum, used, list, res);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 9, 9, 10, 10};
        //solution(nums, 2);
        sol(nums, 2);
    }
}
