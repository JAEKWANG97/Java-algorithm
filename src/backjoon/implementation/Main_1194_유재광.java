package backjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1194_유재광 {

    static class Location {
        int x, y, dist, keys;

        public Location(int x, int y, int dist, int keys) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.keys = keys;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static char[][] map;
    static Location minsik;

    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };

    static int keyType = (1 << 'a' - 'a') | (1 << 'b' - 'a') | (1 << 'c' - 'a') | (1 << 'd' - 'a') | (1 << 'e' - 'a')
            | (1 << 'f' - 'a');
    static int doorType = (1 << 'A' - 'A') | (1 << 'B' - 'A') | (1 << 'C' - 'A') | (1 << 'D' - 'A') | (1 << 'E' - 'A')
            | (1 << 'F' - 'A');

    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char item = input.charAt(j);
                map[i][j] = item;
                if (item == '0') {
                    minsik = new Location(i, j, 0, 0);
                }
            }
        }
    }

    private static void bfs() {
        PriorityQueue<Location> que = new PriorityQueue<>(Comparator.comparingInt(l -> l.dist));
        que.add(minsik);

        boolean[][][] visited = new boolean[N][M][1 << 6]; // 6개의 키를 모두 표현할 수 있는 크기

        visited[minsik.x][minsik.y][0] = true;

        while (!que.isEmpty()) {
            Location cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == '#' || visited[nx][ny][cur.keys])
                    continue;

                if (map[nx][ny] == '1') {
                    System.out.println(cur.dist + 1);
                    return;
                }

                int newKeys = cur.keys;
                boolean canMove = false;

                if (map[nx][ny] == '.' || map[nx][ny] == '0') {
                    canMove = true;
                } else if (isDoor(map[nx][ny]) && hasKeyForDoor(cur.keys, map[nx][ny])) {
                    canMove = true;
                } else if (isKey(map[nx][ny])) {
                    newKeys = cur.keys | (1 << (map[nx][ny] - 'a'));
                    canMove = true;
                }

                if (canMove && !visited[nx][ny][newKeys]) {
                    visited[nx][ny][newKeys] = true;
                    que.add(new Location(nx, ny, cur.dist + 1, newKeys));
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isDoor(char c) {
        return c >= 'A' && c <= 'F';
    }

    private static boolean hasKeyForDoor(int keys, char door) {
        return (keys & (1 << (door - 'A'))) != 0;
    }

    private static boolean isKey(char c) {
        return c >= 'a' && c <= 'f';
    }
}
