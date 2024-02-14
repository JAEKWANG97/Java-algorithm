package backjoon.divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1992 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }

        compression(0, 0, N);
    }

    static void compression(int x, int y, int n) {
        sb.append("(");
        int flag = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    flag = arr[i][j];
                }

                if (arr[i][j] != flag) {
                    compression(i, j, n / 2);
                }

            }
        }
        sb.append(")");
    }
}
