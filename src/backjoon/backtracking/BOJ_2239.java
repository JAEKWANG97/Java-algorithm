package backjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_2239 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board = new int[9][9];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        for (int i = 0; i < 9; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    private static void dfs(int depth, int[][] map, int x, int y, boolean[][] visited) {
        if (!confirm(map)) {
            return;
        }

        if (depth == 81) {
            return;
        }

        if (map[x][y] != 0) {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= 9 || ny < 0 || ny >= 9 || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                dfs(depth, map, nx, ny, visited);
                visited[nx][ny] = false;
            }
        } else {
            //
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= 9 || ny < 0 || ny >= 9 || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                dfs(depth, map, nx, ny, visited);
                visited[nx][ny] = false;
            }
        }

    }

    private static boolean confirm(int[][] map) {
        return confirmSquare(map) && confirmCols(map) && confirmRows(map);
    }

    private static boolean confirmSquare(int[][] map) {
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                HashSet<Integer> set = new HashSet<>(); // 유효성 체크
                // i와 j가 시작 지점임
                for (int k = i; k < i + 3; k++) {
                    for (int k2 = j; k2 < j + 3; k2++) {
                        if (set.contains(map[k][k2])) {
                            return false;
                        }
                        if (map[k][k2] != 0) {
                            set.add(map[k][k2]);
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean confirmRows(int[][] map) {
        for (int i = 0; i < 9; i++) {
            HashSet<Integer> set = new HashSet<>(); // 유효성 체크
            for (int j = 0; j < 9; j++) {
                if (set.contains(map[i][j])) {
                    return false;
                }
                if (map[i][j] != 0) {
                    set.add(map[i][j]);
                }
            }
        }
        return true;
    }

    private static boolean confirmCols(int[][] map) {
        for (int i = 0; i < 9; i++) {
            HashSet<Integer> set = new HashSet<>(); // 유효성 체크
            for (int j = 0; j < 9; j++) {
                if (set.contains(map[j][i])) {
                    return false;
                }
                if (map[j][i] != 0) {
                    set.add(map[j][i]);
                }
            }
        }
        return true;
    }
}
