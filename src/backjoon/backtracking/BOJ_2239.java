package backjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2239 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        init();
        dfs(0);
    }

    private static void init() throws IOException {
        for (int i = 0; i < 9; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    private static void dfs(int depth) {
        if (depth == 81) {
            printBoard();
            System.exit(0);
            return;
        }

        int row = depth / 9;
        int col = depth % 9;

        if (board[row][col] != 0) {
            dfs(depth + 1); // 이미 숫자가 있는 경우 다음 칸으로
            return;
        } else {
            for (int num = 1; num <= 9; num++) {
                if (isValid(row, col, num)) {
                    board[row][col] = num;
                    dfs(depth + 1);
                    board[row][col] = 0; // 백트래킹
                }
            }
        }
    }

    private static boolean isValid(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false; // 같은 행 또는 열에 동일한 숫자가 있는지 확인
            }
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false; // 3x3 박스 내에 동일한 숫자가 있는지 확인
                }
            }
        }
        return true;
    }

    private static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}