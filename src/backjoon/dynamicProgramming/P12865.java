package backjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12865 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K; // 클래스 레벨 변수로 선언된 N과 K

    static int[][] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][2]; // arr[N][0] --> 무게 , arr[N][1] --> 가치

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 무게
            arr[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }

        System.out.println(dp());
    }

    static int dp() {
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (arr[i - 1][0] <= j) {
                    // i번째 물건을 포함시키는 경우와 포함시키지 않는 경우 중 최대값 선택
                    dp[i][j] = Math.max(arr[i - 1][1] + dp[i - 1][j - arr[i - 1][0]], dp[i - 1][j]);
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][K];
    }
}
