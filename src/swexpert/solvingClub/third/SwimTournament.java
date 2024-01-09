package swexpert.solvingClub.third;

import java.util.Scanner;

public class SwimTournament {
    static int[][] grid;
    static boolean[][] visited;
    static int N;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // 테스트 케이스의 수

        for (int t = 0; t < T; t++) {
            N = scanner.nextInt();
            grid = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    grid[i][j] = scanner.nextInt();
                }
            }

            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            int endX = scanner.nextInt();
            int endY = scanner.nextInt();

            boolean result = dfs(startX, startY, endX, endY);
            System.out.println("#" + (t + 1) + " " + (result ? "Yes" : "No"));
        }

        scanner.close();
    }

    static boolean dfs(int x, int y, int endX, int endY) {
        // 목적지에 도달한 경우
        if (x == endX && y == endY) {
            return true;
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 격자 범위 내이고, 방문하지 않았으며, 장애물이 아닌 경우
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && grid[nx][ny] != 1 && grid[nx][ny] != 2) {
                if (dfs(nx, ny, endX, endY)) {
                    return true;
                }
            }
        }

        // 현재 경로에서 더 이상 진행할 수 없는 경우
        return false;
    }
}
