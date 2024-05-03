package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main_16933 {
    static class Location implements Comparable<Location> {
        int r;
        int c;
        int time;
        int k;
        boolean day;

        public Location(int r, int c, int time, int k, boolean day) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.k = k;
            this.day = day;
        }

    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
    }

    private static void bfs() {
        Queue<Location> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[N][M][K + 1][2];
        queue.offer(new Location(0, 0, 1, 0, true));
        visited[0][0][0][1] = true;
        int[] dr = { -1, 0, 1, 0 };
        int[] dc = { 0, 1, 0, -1 };

        while (!queue.isEmpty()) {
            Location cur = queue.poll();

            if (cur.r == N - 1 && cur.c == M - 1) {
                System.out.println(cur.time);
                return;
            }

            int dayNightIndex = cur.day ? 1 : 0;
            int nextDayNightIndex = 1 - dayNightIndex;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc][cur.k][nextDayNightIndex])
                    continue;

                if (cur.day && map[nr][nc] == 1 && cur.k < K) {
                    visited[nr][nc][cur.k + 1][nextDayNightIndex] = true;
                    queue.offer(new Location(nr, nc, cur.time + 1, cur.k + 1, !cur.day));
                } else if (map[nr][nc] == 0) {
                    visited[nr][nc][cur.k][nextDayNightIndex] = true;
                    queue.offer(new Location(nr, nc, cur.time + 1, cur.k, !cur.day));
                }
            }

            if (!visited[cur.r][cur.c][cur.k][nextDayNightIndex]) {
                visited[cur.r][cur.c][cur.k][nextDayNightIndex] = true;
                queue.offer(new Location(cur.r, cur.c, cur.time + 1, cur.k, !cur.day));
            }
        }

        System.out.println(-1);
    }

}
