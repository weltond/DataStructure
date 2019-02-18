class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        /* Method 1 : recursion - Top Down
        //return rec(s.toCharArray(), 0);
        
        
        int[] res = new int[]{0};
        rec2(s.toCharArray(), 0, res);
        return res[0];
        */
        
        /* Method 2: reursion + memoization
        int[] mem = new int[s.length()];
        Arrays.fill(mem, -1);
        return memoization(s.toCharArray(), 0, mem);
        */
        
        /* Method 3: dp - Bottom Up*/
        return dp(s.toCharArray());
    }
    
    /*method 1: recursion*/
    private int rec(char[] arr, int start) {
        if (start == arr.length) return 1;
        
        int ways = 0;
        
        // one letter
        if (arr[start] != '0') {
            ways += rec(arr, start + 1);
        }
        
        // two letters
        if (isValid(arr, start)) {
            ways += rec(arr, start + 2);
        }
        
        return ways;
    }
    
    private void rec2(char[] arr, int start, int[] res) {
        if (start == arr.length) {
            res[0] += 1;
            return;
        } 
        
        // one letter
        if (arr[start] != '0') {
            rec2(arr, start + 1, res);
        }
        
        // two letters
        if (isValid(arr, start)) {
            rec2(arr, start + 2, res);
        }

    }
    
    /*Method Recursion + Memoization*/
    private int memoization(char[] arr, int start, int[] mem) {
        if (start == arr.length) return 1;
        
        if (mem[start] != -1) return mem[start];
        
        
        int ways = 0;

        // one letter
        if (arr[start] != '0') {
            ways += memoization(arr, start + 1, mem);
        }

        // two letters
        if (isValid(arr, start)) {
            ways += memoization(arr, start + 2, mem);
        }
    
        mem[start] = ways;
        
        return ways;
    }
    
    private boolean isValid(char[] arr, int start) {
        if (start + 1 >= arr.length) return false;
        
        // if start from 1
        if (arr[start] == '1') return true;
        // if start from 2
        if (arr[start] == '2' && arr[start + 1] - '6' <= 0) return true;
        
        return false;
    }
    
	/*Method: DP*/
    private int dp(char[] arr) {
        int[] res = new int[arr.length + 1];
        int n = arr.length;
        
        // base case
        res[n] = 1;
        //System.out.println(res[0]);
        for (int i = n - 1; i >= 0; i--) {
            // 1 letter
            if (arr[i] != '0') {
                res[i] += res[i + 1];
                //System.out.println("in 1 while i: " + i + ", res[i]: "+res[i] );
            }
            
            // 2 letters
            if (isValid(arr, i)) {
                res[i] += res[i + 2];
                //System.out.println("in 2 while i: " + i + ", res[i]: "+res[i] );
            }
        }
        
        return res[0];
    }
}
