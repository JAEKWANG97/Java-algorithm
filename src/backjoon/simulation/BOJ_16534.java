package backjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_16534
 */
public class BOJ_16534 {
    static class Location {
        int x;
        int y;
        int value;
        int count;

        public Location(int x, int y, int value, int count) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.count = count;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }


    private static void bfs(int[][] arr, Location start, int N, int L, int R, boolean[][] visited) {
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.x][start.y] = true;
        int sum = start.value;
        int count = 1;

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Location cur = queue.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curValue = cur.value;

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
                    continue;
                }

                if (Math.abs(arr[nx][ny] - curValue) >= L && Math.abs(arr[nx][ny] - curValue) <= R) {
                    queue.offer(new Location(nx, ny, (arr[nx][ny] + sum) / count, count + 1));
                    visited[nx][ny] = true;
                    sum += arr[nx][ny];
                    count++;
                }

            }

        }
    }
}
