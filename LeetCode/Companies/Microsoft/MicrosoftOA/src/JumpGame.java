import java.util.LinkedList;
import java.util.Queue;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * You are given an array of non-negative integers arr and a start index. When you are at an index i, you can move left or right by arr[i]. Your task is to figure out if you can reach value 0.
 *
 * Input: arr = [3, 4, 2, 3, 0, 3, 1, 2, 1], start = 7
 * Output: true
 * Explanation: left -> left -> right
 *
 * https://github.com/weltond/DataStructure/blob/master/LeetCode/dp/801-Minimum-Swaps-to-Make-Sequences-Increasing.md
 */
public class JumpGame {
    public boolean canReach(int[] arr, int start) {
        Queue<Integer> q = new LinkedList();
        q.offer(start);
        boolean[] seen = new boolean[arr.length];
        while (!q.isEmpty()) {
            int idx = q.poll();
            if (arr[idx] == 0) return true;
            seen[idx] = true;

            if (idx + arr[idx] < arr.length && !seen[idx + arr[idx]]) q.offer(idx + arr[idx]);
            if (idx - arr[idx] >= 0 && !seen[idx - arr[idx]]) q.offer(idx - arr[idx]);
        }

        return false;
    }
}
