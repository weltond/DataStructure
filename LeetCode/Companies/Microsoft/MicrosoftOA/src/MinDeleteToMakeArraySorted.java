/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 */
public class MinDeleteToMakeArraySorted {
    // Driver Code
    public static void main (String[] args)
    {
        int arr[] = {30, 40, 2, 5, 1,
                7, 45, 50, 8};
        int n = arr.length;
        System.out.println("Minimum number of" +
                " deletions = " +
                GFG.minimumNumberOfDeletions(arr, n));
    }
}
// Java implementation to find
// minimum number of deletions
// to make a sorted sequence

class GFG
{
    /* lis() returns the length
    of the longest increasing
    subsequence in arr[] of size n */
    static int lis( int arr[], int n )
    {
        int result = 0;
        int[] lis = new int[n];

		/* Initialize LIS values
		for all indexes */
        for (int i = 0; i < n; i++ )
            lis[i] = 1;

		/* Compute optimized LIS
		values in bottom up manner */
        for (int i = 1; i < n; i++ )
            for (int j = 0; j < i; j++ )
                if ( arr[i] > arr[j] &&
                        lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;

		/* Pick resultimum of
		all LIS values */
        for (int i = 0; i < n; i++ ) {
            if (result < lis[i])
                result = lis[i];
            System.out.print(lis[i] + " ");
        }



        return result;
    }

    // function to calculate minimum
    // number of deletions
    static int minimumNumberOfDeletions(int arr[],
                                        int n)
    {
        // Find longest
        // increasing subsequence
        int len = lis(arr, n);

        // After removing elements
        // other than the lis, we get
        // sorted sequence.
        return (n - len);
    }


}

/* This code is contributed by Harsh Agarwal */
