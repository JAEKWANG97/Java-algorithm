package backjoon.divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        compression(0, 0, N);
        System.out.println(sb.toString());
    }

    static void compression(int x, int y, int size) {
        if (isUniform(x, y, size)) {
            sb.append(arr[x][y]);
        } else {
            sb.append("(");
            int newSize = size / 2;

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    compression(x + i * newSize, y + j * newSize, newSize);
                }
            }
            sb.append(")");
        }
    }

    static boolean isUniform(int x, int y, int size) {
        char firstValue = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != firstValue) {
                    return false;
                }
            }
        }
        return true;
    }

}
