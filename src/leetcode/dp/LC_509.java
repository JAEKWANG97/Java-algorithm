package leetcode.dp;

public class LC_509 {

}

class Solution {
    public int fib(int n) {
        int[] dp = new int[n + 1];
        if (n <= 2) {
            return 1;
        }
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}