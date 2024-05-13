package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086 {
    static class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int[][] map;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        input();
        process();
        System.out.println(max);
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void process() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }
    }

    private static void bfs(int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(x, y, 0));
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        int[] dx = { -1, 1, 0, 0, 1, 1, -1, -1 };
        int[] dy = { 0, 0, -1, 1, 1, -1, -1, 1 };
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (map[cur.x][cur.y] == 1 && (cur.x != x || cur.y != y)) {
                max = Math.max(max, cur.cnt);
                return;
            }

            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
                    continue;
                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny, cur.cnt + 1));
            }
        }
    }
}
