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
            int max_total = 0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    max_total = Math.max(max_total, Math.max(spray_cross(arr, j, k, m), spray_plus(arr, j, k, m)));
                }
            }
            System.out.println("#" + i + " " + max_total);

        }
        scanner.close();
    }

    private static int spray_cross(int[][] arr, int row, int col, int m) {
        int total = arr[row][col];  // 중심점 한 번만 계산
        for (int i = 1; i < m; i++) {  // 중심점을 제외하고 계산
            // 범위 체크 및 계산
            if (row - i >= 0 && col - i >= 0) {
                total += arr[row - i][col - i];
            }
            if (row - i >= 0 && col + i < arr[0].length) {
                total += arr[row - i][col + i];
            }
            if (row + i < arr.length && col - i >= 0) {
                total += arr[row + i][col - i];
            }
            if (row + i < arr.length && col + i < arr[0].length) {
                total += arr[row + i][col + i];
            }
        }
        return total;
    }

    private static int spray_plus(int[][] arr, int row, int col, int m) {
        int total = arr[row][col];  // 중심점 한 번만 계산
        for (int i = 1; i < m; i++) {  // 중심점을 제외하고 계산
            // 범위 체크 및 계산
            if (row - i >= 0) {
                total += arr[row - i][col];
            }
            if (row + i < arr.length) {
                total += arr[row + i][col];
            }
            if (col - i >= 0) {
                total += arr[row][col - i];
            }
            if (col + i < arr[0].length) {
                total += arr[row][col + i];
            }
        }
        return total;
    }

}
