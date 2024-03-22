package backjoon;

import java.io.*;
import java.util.*;

public class BOJ_3190 {

    static class Snake {
        int x;
        int y;
        int len;

        Queue<Location> tail = new ArrayDeque<>();

        Direction direction; // 방향 enum으로 변경

        public Snake(int x, int y, int len, Direction direction, Location tail) {
            this.x = x;
            this.y = y;
            this.len = len;
            this.direction = direction;
            this.tail.add(tail);
        }
    }

    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class SnakeMove {
        int time;
        String direction;

        public SnakeMove(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT // 방향 enum으로 정의
    }

    static BufferedReader br;
    static StringTokenizer st;

    static int N;
    static int K;
    static int L;

    static int[][] map;
    static Queue<SnakeMove> snakeMoveQueue;
    static Snake snake;

    public static void main(String[] args) throws IOException {
        init();
        simulate();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        snake = new Snake(1, 1, 1, Direction.RIGHT, new Location(1, 1)); // 방향 enum 사용
        snakeMoveQueue = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            snakeMoveQueue.add(new SnakeMove(time, direction));
        }
    }

    private static void simulate() {
        boolean[][] visited = new boolean[N + 1][N + 1];
        Queue<Location> queue = new ArrayDeque<>();
        queue.add(new Location(snake.x, snake.y));
        visited[snake.x][snake.y] = true;

        int time = 0;
        SnakeMove nextMove = null;
        if (!snakeMoveQueue.isEmpty()) {
            nextMove = snakeMoveQueue.poll();
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (nextMove != null && time == nextMove.time) {
                if (nextMove.direction.equals("D")) {
                    turnRight();
                } else if (nextMove.direction.equals("L")) {
                    turnLeft();
                }
                if (!snakeMoveQueue.isEmpty()) {
                    nextMove = snakeMoveQueue.poll();
                }
            }
            while (size-- > 0) {
                Location cur = queue.poll();

                Direction d = snake.direction;

                int nx = cur.x;
                int ny = cur.y;

                switch (d) {
                    case UP:
                        nx--;
                        break;
                    case DOWN:
                        nx++;
                        break;
                    case LEFT:
                        ny--;
                        break;
                    case RIGHT:
                        ny++;
                        break;
                }

                if (nx < 1 || nx > N || ny < 1 || ny > N || visited[nx][ny] || map[nx][ny] == -1) {
                    System.out.println(time + 1);
                    return;
                }

                visited[nx][ny] = true;
                snake.tail.add(new Location(nx, ny));
                if (map[nx][ny] == 0 && snake.tail.size() > 1) {
                    Location remove = snake.tail.poll();
                    visited[remove.x][remove.y] = false;
                }

                if (map[nx][ny] == 1) {
                    snake.len += 1;
                }
                map[nx][ny] = 0;
                snake.x = nx;
                snake.y = ny;
                queue.add(new Location(nx, ny));
                visited[nx][ny] = true;
            }
            time += 1;
        }

    }

    private static void turnLeft() {
        snake.direction = Direction.values()[(snake.direction.ordinal() + 3) % 4]; // 방향 전환을 enum을 사용하여 간소화
    }

    private static void turnRight() {
        snake.direction = Direction.values()[(snake.direction.ordinal() + 1) % 4]; // 방향 전환을 enum을 사용하여 간소화
    }
}
