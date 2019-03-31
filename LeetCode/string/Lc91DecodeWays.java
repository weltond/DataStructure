// https://leetcode.com/problems/decode-ways/

class Solution {
	// ======================= DP ===========================
	// 1ms(84.40%)
    // ======== DP 2 with O(1) space ==========
    public int numDecodings(String s) {
        if (s == null || s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;
        
        int len = s.length();
        
        // dp[i] is ways to decode s[0..i]
        // dp[i] = dp[i-1] + dp[i-2]
        // int[] dp = new int[len];
        int w1 = 1; //dp[0] = 1;  // first letter is covered in base case if is '0'
        int w2 = 1;
        
        char[] arr = s.toCharArray();
        for (int i = 1; i < len; i++) {
            int w = 0;
            // 1 letter
            if (isValid(arr, i)) {
                w += w1; //dp[i] += dp[i - 1]; 
            }
            // 2 letters
            if (isValid(arr, i, i - 1)) {
                w += w2;
                // if (i - 2 <= 0) {
                //     dp[i] += 1;
                // } else {
                //     dp[i] += dp[i - 2];
                // }
            }
            w2 = w1;
            w1 = w;
        }
        
        return w1; //return dp[len - 1];
    }
    
    // ========= DP 2 with O(n) Space ==========
    public int numDecodings(String s) {
        if (s == null || s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;
        
        int len = s.length();
        // dp[i] is ways to decode s[0..i]
        // dp[i] = dp[i-1] + dp[i-2]
        int[] dp = new int[len];
        dp[0] = 1;  // first letter is covered in base case if is '0'
        
        char[] arr = s.toCharArray();
        for (int i = 1; i < len; i++) {
            // 1 letter
            if (isValid(arr, i)) {
                dp[i] += dp[i - 1];
            }
            // 2 letters
            if (isValid(arr, i, i - 1)) {
                if (i - 2 <= 0) {
                    dp[i] += 1;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
        }
        
        return dp[len - 1];
    }
    
    private boolean isValid(char[] arr, int start, int end) {
        int prefix = (arr[start - 1] - '0') * 10 + (arr[start] - '0');
        
        return prefix >= 10 && prefix <= 26;
    }
    
    private boolean isValid(char[] arr, int start) {
        return arr[start] != '0';
    }
}

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
