package swexpert.week6;

import static java.lang.Math.abs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_4012_유재광 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T;
    static int sumA;
    static int sumB;
    static int[][] arr;
    static int minFlavor;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sumA = 0;
            sumB = 0;
            minFlavor = Integer.MAX_VALUE;
            int n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            initArr(n, arr);
            int[] tmpArr = new int[n];
            for (int i = 0; i < n; i++) {
                tmpArr[i] = i;
            }
            combi1(tmpArr, new boolean[n], 0, n, n / 2);

            System.out.println("#" + tc + " " + minFlavor);

        }


    }

    private static void initArr(int n, int[][] arr) throws IOException {
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void combi1(int[] tmpArr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            // 배열 합
            for (int k = 0; k < n - 1; k++) {
                for (int j = k; j < n; j++) {
                    if (visited[k] && visited[j]) {
                        // 음식 A
                        sumA += arr[k][j];
                        sumA += arr[j][k];
                    }
                    if (!visited[k] && !visited[j]) {
                        //음식 B
                        sumB += arr[k][j];
                        sumB += arr[j][k];
                    }
                }
            }
            minFlavor = Math.min(abs(sumA - sumB), minFlavor);
            sumA = 0;
            sumB = 0;
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combi1(tmpArr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    static void calculateFlavor() {

    }
}




