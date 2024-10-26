package backjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2293 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] arr;
    static int count;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(solution(0, K));
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        count = 0;
        dp = new int[N][K + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            Arrays.fill(dp[i], -1);
        }
        Arrays.sort(arr);
    }

    private static int solution(int i, int remaining) {
        // 경우의 수 1
        if (remaining == 0) {
            return 1;
        }

        // 경우의 수 없다.
        if (i >= N || remaining < 0) {
            return 0;
        }

        if (dp[i][remaining] != -1) {
            return dp[i][remaining];
        }

        dp[i][remaining] = solution(i + 1, remaining) + solution(i, remaining - arr[i]);

        return dp[i][remaining];
    }
}
