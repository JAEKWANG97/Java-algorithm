import java.io.*;
import java.util.*;

public class BOJ_17485 {
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] space = new int[N][M];
        int[][][] dp = new int[N][M][3];

        // 우주 공간 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째 행 초기화
        for (int j = 0; j < M; j++) {
            for (int d = 0; d < 3; d++) {
                dp[0][j][d] = space[0][j];
            }
        }

        // DP 계산
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 왼쪽 대각선 (0)
                if (j == 0)
                    dp[i][j][0] = INF;
                else
                    dp[i][j][0] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + space[i][j];

                // 아래 (1)
                dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + space[i][j];

                // 오른쪽 대각선 (2)
                if (j == M - 1)
                    dp[i][j][2] = INF;
                else
                    dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + space[i][j];
            }
        }

        // 최소 연료 계산
        int minFuel = INF;
        for (int j = 0; j < M; j++) {
            for (int d = 0; d < 3; d++) {
                minFuel = Math.min(minFuel, dp[N - 1][j][d]);
            }
        }

        System.out.println(minFuel);
    }
}