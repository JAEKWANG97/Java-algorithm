package backjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_22871 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static long[] dp;
    private static int[] arr;
    private static int n;

    public static void main(String[] args) throws IOException {
        init();
        dp();
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void dp() {
        dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long power = cal(j, i, arr[j], arr[i]);
                dp[i] = Math.min(dp[i], Math.max(dp[j], power));
            }
        }

        System.out.println(dp[n - 1]);
    }

    private static long cal(int from, int to, int fromValue, int toValue) {
        return (long) (to - from) * (1 + Math.abs(fromValue - toValue));
    }

}
