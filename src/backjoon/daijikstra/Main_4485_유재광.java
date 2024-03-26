package backjoon.daijikstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4485_유재광 {

    static class Location implements Comparable<Location> {
        int x;
        int y;
        int sum;

        public Location(int x, int y, int sum) {
            super();
            this.x = x;
            this.y = y;
            this.sum = sum;
        }

        @Override
        public int compareTo(Location o) {
            return this.sum - o.sum;
        }

    }

    static int minSum;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] map;

    static int[] deltaX = new int[]{0, 1, 0, -1};
    static int[] deltaY = new int[]{-1, 0, 1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            minSum = Integer.MAX_VALUE;
            if (N == 0) {
                break;
            }
            init();
            bfs();
            sb.append("Problem ").append(t++).append(": ").append(minSum).append("\n");
        }
        System.out.println(sb);
    }

    private static void init() throws NumberFormatException, IOException {

        minSum = Integer.MAX_VALUE;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void bfs() {
        PriorityQueue<Location> que = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        que.add(new Location(0, 0, map[0][0]));

        while (!que.isEmpty()) {

            Location cur = que.poll();
            visited[cur.x][cur.y] = true;

            if (cur.x == N - 1 && cur.y == N - 1) {
                minSum = cur.sum;
                return;
            }

            for (int i = 0; i < 4; i++) {

                int nx = cur.x + deltaX[i];
                int ny = cur.y + deltaY[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
                    continue;
                }

                que.add(new Location(nx, ny, cur.sum + map[nx][ny]));
            }

        }

    }

}