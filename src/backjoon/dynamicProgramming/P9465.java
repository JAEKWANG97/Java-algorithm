package backjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;

public class P9465 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, N, answer;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        startCycle();
    }

    static void startCycle() throws IOException {
        for (int tc = 0; tc < T; tc++) {
            init();
            dp();
            System.out.println(answer);
        }


    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[2][N];
        answer = 0;
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void dp() {
        int[][] dpTable = new int[2][N];
        dpTable[0][0] = arr[0][0];
        dpTable[1][0] = arr[1][0];

        if (N > 1) {
            dpTable[0][1] = arr[1][0] + arr[0][1];
            dpTable[1][1] = arr[0][0] + arr[1][1];
        }

        for (int i = 2; i < N; i++) {
            dpTable[0][i] = Math.max(dpTable[1][i - 1], dpTable[1][i - 2]) + arr[0][i];
            dpTable[1][i] = Math.max(dpTable[0][i - 1], dpTable[0][i - 2]) + arr[1][i];
        }

        answer = Math.max(dpTable[0][N - 1], dpTable[1][N - 1]);
    }


}
