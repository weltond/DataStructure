import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 * There are N bulbs, numbered from 1 to N, arranged in a row. Initially, all the bulbs are turned off. At moment K (for K from 0 to N - 1), we turn on the A[K]-th bulb. A bulb shines if it is on and all the previous bulbs are turned on too.
 *
 * Return the number of moments for which every turned on bulb shines.
 *
 * e.g. A = [2,1,3,5,4]
 * There are three moments (1st, 2nd and 4th) when every turned on bulb shines.
 *
 * e.g. A = [2,3,4,1,5]
 * There are two moments (3rd and 4th) when every turned on bulb shines.
 *
 * e.g. A = [5,4,3,2,1]
 * There is one moment (4th) when every turned on bulb shines.
 */
public class LightBulbSwitcher {
    public static void main(String[] args) {
        int[] nums1 = {2,1,3,5,4};
        int[] nums2 = {2,3,4,1,5};
        int[] nums3 = {5,4,3,2,1};
        int[] nums4 = {3,2,4,6,5};
        System.out.println(getNumOfMoments(nums1));
        System.out.println(getNumOfMoments(nums2));
        System.out.println(getNumOfMoments(nums3));
        System.out.println(getNumOfMoments(nums4));
        m2(nums1);
    }

    private static int getNumOfMoments(int[] nums) {
        int[] preSumNums = new int[nums.length + 1];
        int[] preSumNumsSorted = new int[nums.length + 1];
        int[] numsSorted = new int[nums.length];
        for(int i=0;i<nums.length;i++)
            numsSorted[i] = nums[i];
        Arrays.sort(numsSorted);
        int res = 0;
        for(int i=0;i<nums.length;i++) {
            preSumNums[i+1] = preSumNums[i] + nums[i];
            preSumNumsSorted[i+1] = preSumNumsSorted[i] + numsSorted[i];
            if(preSumNums[i+1] == preSumNumsSorted[i+1])
                res++;
        }
        return res;
    }

    /**
     * At the ith moment, there are i+1 turned-on bulbs. For all of them to shine, turned-on bulbs must be 1,2,3,.....,i+1.
     * In addition, under the conditions of "there are i+1 turned-on bulbs" and "there are N bulbs, numbered from 1 to N",
     * "turned-on bulbs must be 1,2,3,.....,i+1" is equivalent to "max number of the turned-on bulbs is i+1."
     * @param A
     */
    public static void m2(int[] A) {
        int max = 0;
        List result = new LinkedList<>();
        for(int i=0;i<A.length;i++){
            int val = A[i];
            max = Math.max(max,val);
            if(max == i+1) result.add(i);
        }
        System.out.println(result);
    }

}
