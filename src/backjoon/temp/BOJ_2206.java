package backjoon.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String input = br.readLine().trim();
            for (int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        System.out.println(bfs(map, n, m));

    }

    private static int bfs(char[][] map, int n, int m) {
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][n][m];

        que.add(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        int[] deltaX = new int[]{0, 1, 0, -1};
        int[] deltaY = new int[]{-1, 0, 1, 0};

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curD = cur[2];
            int curK = cur[3];

            if (curX == n - 1 && curY == m - 1) {
                return curD + 1;
            }
            // 벽 안 부실 경우
            for (int i = 0; i < 4; i++) {
                int nx = curX + deltaX[i];
                int ny = curY + deltaY[i];
                if (isIn(nx, ny, n, m) && !visited[curK][nx][ny] && map[nx][ny] == '0') {
                    que.add(new int[]{nx, ny, curD + 1, curK});
                    visited[curK][nx][ny] = true;
                }
                if (isIn(nx, ny, n, m) && !visited[curK][nx][ny] && map[nx][ny] == '1' && curK == 0) {
                    que.add(new int[]{nx, ny, curD + 1, curK + 1});
                    visited[curK + 1][nx][ny] = true;
                }
            }
            // 벽 부실 경우

        }
        return -1;
    }

    private static boolean isIn(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
