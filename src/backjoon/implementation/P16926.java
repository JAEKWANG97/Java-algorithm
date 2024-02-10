package backjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P16926 {

    static int M;
    static int N;
    static int R;
    static int[][] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < R; i++) {
            rotate();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }

    private static void rotate() {
        int nCopy = N;
        int mCopy = M;
        int numRectanglesToRotate = Math.min(N, M) / 2;

        //회전해야할 상자 수
        // i는 시작점
        for (int i = 0; i < numRectanglesToRotate; i++) {
            // 탑
            int outOfTop = arr[i][i];
            for (int j = i; j < mCopy - 1; j++) {
                arr[i][j] = arr[i][j + 1];
            }

            // 오른쪽
            for (int j = i; j < nCopy - 1; j++) {
                arr[j][mCopy - 1] = arr[j + 1][mCopy - 1];
            }

            // 바텀
            for (int j = mCopy - 1; j > i; j--) {
                arr[nCopy - 1][j] = arr[nCopy - 1][j - 1];
            }

            // 왼쪽
            for (int j = nCopy - 1; j > i; j--) {
                arr[j][i] = arr[j - 1][i];
            }
            arr[i + 1][i] = outOfTop;

            nCopy -= 1;
            mCopy -= 1;
        }
    }
}
