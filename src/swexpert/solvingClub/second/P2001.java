package swexpert.solvingClub.second;

import java.util.Scanner;

public class P2001 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 1; i <= T; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] arr = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    arr[j][k] = scanner.nextInt();
                }
            }

        }
    }

    private static int killFly(int[][] arr, int n, int m) {
        int max_kill = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cur = arr[i][j];
                try {
                    for (int l = 1; l < n; l++) {
                        cur += arr[i + n][j];
                        cur += arr[i - n][j];
                        cur += arr[i][j - n];
                        cur += arr[i][j + n];
                    }
                } catch (Exception e) {
                    cur += 0;
                }
            }
        }
        return max_kill;
    }

}
