package swexpert.solvingClub.second;

import java.util.Scanner;

public class ValidSudok {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int k = 0; k < n; k++) {
            int rows = 9;  // 행의 수
            int columns = 9;  // 열의 수
            int[][] array = new int[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    array[i][j] = scanner.nextInt();
                }
            }

            boolean answer = isValidColumn(array) && isValidRow(array) && isValidSquare(array);
            if (answer) {
                System.out.println("#"+(k+1)+" 1");
            } else {
                System.out.println("#"+(k+1)+" 0");
            }
        }
    }

    private static boolean isValidRow(int[][] array) {
        int rows = array.length;
        int cols = array[0].length;

        for (int i = 0; i < rows; i++) {
            boolean[] visit = new boolean[cols];
            for (int j = 0; j < cols; j++) {
                int value = array[i][j];

                if (value < 1 || value > cols) {
                    return false; // 값이 유효 범위를 벗어난 경우
                }

                if (visit[value - 1]) {
                    return false; // 중복된 값이 있는 경우
                }
                visit[value - 1] = true;
            }
        }
        return true; // 모든 행이 유효한 경우
    }

    private static boolean isValidColumn(int[][] array) {
        int rows = array.length;
        int cols = array[0].length;

        for (int i = 0; i < cols; i++) {
            boolean[] visit = new boolean[cols];
            for (int j = 0; j < rows; j++) {
                int value = array[j][i];

                if (value < 1 || value > cols) {
                    return false; // 값이 유효 범위를 벗어난 경우
                }

                if (visit[value - 1]) {
                    return false; // 중복된 값이 있는 경우
                }
                visit[value - 1] = true;
            }
        }
        return true; // 모든 행이 유효한 경우
    }

    private static boolean isValidSquare(int[][] array) {
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (!isUniqueInSquare(array, row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isUniqueInSquare(int[][] array, int startRow, int startCol) {
        boolean[] visit = new boolean[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = array[startRow + i][startCol + j];
                if (value < 1 || value > 9 || visit[value - 1]) {
                    return false;
                }
                visit[value - 1] = true;
            }
        }
        return true;
    }

}
