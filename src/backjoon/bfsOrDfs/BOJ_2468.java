package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2468 {
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] map;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int maxHeight = 0;
    static int maxCount = 0;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = maxHeight - 1; i >= 0; i--) {
            boolean[][] visited = new boolean[N][N];
            int count = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k] > i && !visited[j][k]) {
                        bfs(new Node(j, k), i, visited);
                        count++;
                    }
                }
            }
            maxCount = Math.max(maxCount, count);
        }
        System.out.println(maxCount);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

    }

    private static void bfs(Node node, int height, boolean[][] visited) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        visited[node.x][node.y] = true;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] <= height)
                    continue;
                queue.offer(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }
}
