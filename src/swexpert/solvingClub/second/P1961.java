package swexpert.solvingClub.second;

import java.util.Scanner;

public class P1961 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = scanner.nextInt();
            int[][] arr = new int[N][N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    arr[j][k] = scanner.nextInt();
                }
            }
            int[][] arr90 = turnArr(arr, N);
            int[][] arr180 = turnArr(arr90, N);
            int[][] arr270 = turnArr(arr180, N);

            System.out.println("#" + i);
            for (int j = 0; j < N; j++) {
                printRow(arr90, j);
                printRow(arr180, j);
                printRow(arr270, j);
                System.out.println();
            }

        }
    }

    private static int[][] turnArr(int[][] arr, int N) {
        int[][] new_arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                new_arr[i][j] = arr[N - j - 1][i];
            }
        }
        return new_arr;
    }

    private static void printRow(int[][] arr, int row) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[row][i]);
        }
        System.out.print(" ");
    }
}
