package backjoon.bfsOrDfs;

import java.util.*;
import java.io.*;

class BOJ_1926 {
    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int n, m;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };
    private static int max;
    private static int count;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(count);
        System.out.println(max);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        max = 0;
        count = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    max = Math.max(max, bfs(new Node(i, j)));
                    count++;
                }
            }
        }
    }

    private static int bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited[node.r][node.c] = true;
        int size = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            size++;
            for (int i = 0; i < 4; i++) {
                int nr = current.r + dx[i];
                int nc = current.c + dy[i];
                if (nr < 0 || nc < 0 || nr >= n || nc >= m || visited[nr][nc] || map[nr][nc] == 0)
                    continue;
                queue.offer(new Node(nr, nc));
                visited[nr][nc] = true;
            }
        }
        return size;
    }
}
// 1은 색칠된부분 0은 색칠 안된 부분
// 그림의 개수, 최대 크기