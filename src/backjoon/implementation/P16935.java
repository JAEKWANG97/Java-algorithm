package backjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P16935 {
    static int M;
    static int N;
    static int R;
    static int OPERATOR;
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
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < R; i++) {
            OPERATOR     = Integer.parseInt(st.nextToken());
            if (OPERATOR == 1) {
                reverseArrTopBottom();
            }
            if (OPERATOR == 2) {
                reverseArrLeftRight();
            }
            if (OPERATOR == 3) {
                rotateArrayRight90();
            }
            if (OPERATOR == 4) {
                rotateArrayLeft90();
            }
            if (OPERATOR == 5) {
                shiftGroupsToRight();
            }
            if (OPERATOR == 6) {
                shiftGroupsToLeft();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }

    //상하 반전 시키는 함수
    static void reverseArrTopBottom() {
        int top = 0;
        int bottom = N - 1;
        while (top < bottom) {
            int[] tmpArr;
            tmpArr = Arrays.copyOf(arr[top], M);
            arr[top] = arr[bottom];
            arr[bottom] = tmpArr;
            top++;
            bottom--;
        }
    }

    //좌우 반전 시키는 함수
    static void reverseArrLeftRight() {
        int left = 0;
        int right = M - 1;

        while (left < right) {
            for (int i = 0; i < N; i++) {
                int temp = arr[i][left];
                arr[i][left] = arr[i][right];
                arr[i][right] = temp;
            }
            left++;
            right--;
        }
    }

    // 90도 오른쪽으로 회전 시키는 함수
    static void rotateArrayRight90() {
        int[][] newArr = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newArr[j][N - 1 - i] = arr[i][j];
            }
        }
        int tmp = N;
        N = M;
        M = tmp;

        arr = newArr;
    }

    //90도 왼쪽으로 회전 시키는 함수
    static void rotateArrayLeft90() {
        int[][] newArr = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newArr[M - j - 1][i] = arr[i][j];
            }
        }
        int tmp = N;
        N = M;
        M = tmp;

        arr = newArr;
    }

    static void shiftGroupsToRight() {
        int[][] newArr = new int[N][M];
        // 1번을 2번으로 이동
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                newArr[i][j + M / 2] = arr[i][j];
            }
        }

        // 2번을 3번으로 이동
        for (int i = 0; i < N / 2; i++) {
            for (int j = M / 2; j < M; j++) {
                newArr[N / 2 + i][j] = arr[i][j];
            }
        }

        //3번을 4번으로 이동
        for (int i = N / 2; i < N; i++) {
            for (int j = M / 2; j < M; j++) {
                newArr[i][j - M / 2] = arr[i][j];
            }
        }

        //4번을 1번으로 이동
        for (int i = N / 2; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                newArr[i - N / 2][j] = arr[i][j];
            }
        }

        arr = newArr;
    }

    static void shiftGroupsToLeft() {
        int[][] newArr = new int[N][M];

        // 1번을 4번으로
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                newArr[i + N / 2][j] = arr[i][j];
            }
        }
        // 4번을 3번으로
        for (int i = N / 2; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                newArr[i][j + M / 2] = arr[i][j];
            }
        }
        // 3번을 2번으로
        for (int i = N / 2; i < N; i++) {
            for (int j = M / 2; j < M; j++) {
                newArr[i - N / 2][j] = arr[i][j];
            }
        }
        // 2번을 1번으로
        for (int i = 0; i < N / 2; i++) {
            for (int j = M / 2; j < M; j++) {
                newArr[i][j - M / 2] = arr[i][j];
            }
        }

        arr = newArr;
    }
}
