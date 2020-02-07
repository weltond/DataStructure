/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Stable if velocity is same(greater than 3 element)
 * return number of period of time when the movement is stable (same velocity)
 *
 * [-1,1,3,3,3,2,3,2,1,0]
 * 5
 * (0,2),(2,4),(6,9),(6,8),(7,9)
 */
public class ParticleVelocity {
    public int solution(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int cnt = 0; i + 2 < arr.length && arr[i + 1] - arr[i] == arr[i + 2] - arr[i + 1]; i++) {
                cnt++;
                res += cnt;
            }
        }

        return res < 1_000_000_000 ? res : -1;
    }
}
