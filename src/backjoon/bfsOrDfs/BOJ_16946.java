package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16946 {
    private static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int[][] map;

    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static List<Location> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
                if (map[i][j] == 0) {
                    list.add(new Location(i, j));
                }
            }
        }
    }

    private static void solve() {
        for (Location location : list) {
            bfs(location);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j] % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void bfs(Location start) {
        boolean[][] visited = new boolean[N][M];
        Queue<Location> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start.r][start.c] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            Location cur = queue.poll();

            count += 1;
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dx[i];
                int nc = cur.c + dy[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || map[nr][nc] != 0) {
                    continue;
                }
                visited[nr][nc] = true;
                queue.add(new Location(nr, nc));
            }
        }
        map[start.r][start.c] = count;
    }

}
