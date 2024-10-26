package backjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[][] map;
    private static int[] maxDP, minDP;

    public static void main(String[] args) throws IOException {
        init();
        getMaxMin();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        maxDP = new int[3];
        minDP = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째 행의 값으로 초기화
        System.arraycopy(map[0], 0, maxDP, 0, 3);
        System.arraycopy(map[0], 0, minDP, 0, 3);
    }

    private static void getMaxMin() {
        int[] tempMax = new int[3];
        int[] tempMin = new int[3];

        for (int i = 1; i < N; i++) {
            tempMax[0] = Math.max(maxDP[0], maxDP[1]) + map[i][0];
            tempMax[1] = Math.max(Math.max(maxDP[0], maxDP[1]), maxDP[2]) + map[i][1];
            tempMax[2] = Math.max(maxDP[1], maxDP[2]) + map[i][2];

            tempMin[0] = Math.min(minDP[0], minDP[1]) + map[i][0];
            tempMin[1] = Math.min(Math.min(minDP[0], minDP[1]), minDP[2]) + map[i][1];
            tempMin[2] = Math.min(minDP[1], minDP[2]) + map[i][2];

            // 업데이트
            System.arraycopy(tempMax, 0, maxDP, 0, 3);
            System.arraycopy(tempMin, 0, minDP, 0, 3);
        }

        int maxResult = Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2]));
        int minResult = Math.min(minDP[0], Math.min(minDP[1], minDP[2]));

        System.out.println(maxResult + " " + minResult);
    }
}
