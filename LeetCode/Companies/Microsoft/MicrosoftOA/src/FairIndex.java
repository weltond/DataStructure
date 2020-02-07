/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * Index k is named fair if the four sums (a[0]+..+a[k-1]), (a[k]+..+a[n-1]), (b[0]+..+b[k-1]), (b[k]+..+b[n-1]) are all
 * equal.
 *
 * [4,-1,0,3] [-2,5,0,3]
 * 2
 * Fair index are 2 and 3.
 *
 * [2,-2,3,3] [0,0,4,-4]
 * 1
 * Fair index is 2. Index 4 is not fair as k~n-1 is empty.
 *
 * [4,-1,0,3] [-2,6,0,4]
 * 0
 */
public class FairIndex {
    private static int getNumOfFairIndexes(int[] A, int[] B) {
        int res = 0, sumA = 0, sumB = 0;
        for(int i=0;i<A.length;i++) {
            sumA += A[i];
            sumB += B[i];
        }
        int tmpA = 0, tmpB = 0;
        for(int i=0;i<A.length-1;i++) {
            tmpA += A[i];
            tmpB += B[i];
            if(sumA == 2 * tmpA && sumB == 2 * tmpB && tmpA == tmpB) {
                res++;
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[] A1 = {4,-1,0,3}, B1 = {-2, 5, 0 ,3};  //2
        int[] A2 = {2,-2,-3,3}, B2 = {0,0,4,-4};    //1
        int[] A3 = {4,-1,0,3}, B3 = {-2,6,0,4};     //0
        int[] A4 = {3,2,6}, B4 = {4,1,6};           //0
        int[] A5 = {1,4,2,-2,5}, B5 = {7,-2,-2,2,5}; // 2
        int[] A6={0,0,0},B6 = {0,0,0};              // 2
        System.out.println(getNumOfFairIndexes(A1, B1));
        System.out.println(getNumOfFairIndexes(A2, B2));
        System.out.println(getNumOfFairIndexes(A3, B3));
        System.out.println(getNumOfFairIndexes(A4, B4));
        System.out.println(getNumOfFairIndexes(A5, B5));
        System.out.println(getNumOfFairIndexes(A6, B6));
    }
}
