package backjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] w;
    static int[] v;

    public static void main(String[] args) throws IOException {
        init();
        dp();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        w = new int[N];
        v = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void dp() {
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (j < w[i - 1]) { // 물건을 담을 수 없는 경우
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + v[i - 1]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }

}
