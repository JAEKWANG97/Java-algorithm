package swexpert.day5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2001_유재광 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc < T+1; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] arr = new int[n][n];

            // 배열입력 받는 구간
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] prefixSum = new int[n + 1][n + 1];

            // 구간 합 만드는 구간
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    prefixSum[i][j] =
                            arr[i - 1][j - 1] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
                }
            }
            int maxSum = 0;
            int curSum = 0;
            for (int i = m; i <= n; i++) {
                for (int j = m; j <= n; j++) {
                    curSum = prefixSum[i][j] - prefixSum[i - m][j] - prefixSum[i][j - m] + prefixSum[i - m][j - m];
                    maxSum = Math.max(maxSum, curSum);
                }
            }
            System.out.println("#"+tc+ " " + maxSum);
        }
    }
}
