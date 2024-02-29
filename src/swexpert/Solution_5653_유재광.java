package swexpert;

import java.io.*;
import java.util.*;

public class Solution_5653_유재광 {
    static class Cell implements Comparable<Cell> {
        int x;
        int y;
        int time;
        int curTime;

        boolean isAlive = true;

        public Cell(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Cell o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // x
            int m = Integer.parseInt(st.nextToken()); // y
            int k = Integer.parseInt(st.nextToken()); // 시간
            int[][] map = new int[n][m];
            Queue<Cell> cellList = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    int item = Integer.parseInt(st.nextToken());
                    map[i][j] = item;
                    if (item > 0) { // 세포 발견, 리스트에 넣기
                        cellList.offer(new Cell(i, j, item));
                    }
                }
            }

            bfs(n, m, cellList, map);

            int count = 0;

        }
    }

    private static void bfs(int n, int m, Queue<Cell> queue, int[][] map) {
        boolean[][] visited = new boolean[n][m];
        int[] deltaX = new int[]{0, -1, 0, 1};
        int[] deltaY = new int[]{1, 0, -1, 0};
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Cell curCell = queue.poll();
                int curX = curCell.x;
                int curY = curCell.y;
                int curTime = curCell.curTime;

                if (curTime > 0) {
                    queue.offer(curCell);
                    continue;
                }
                curCell.curTime -= 1;

                for (int i = 0; i < 4; i++) {
                    int nx = curX + deltaX[i];
                    int ny = curY + deltaY[i];

                    if (isIn(nx, ny, n, m) && map[nx][ny] < curTime && map[nx][ny] != -1) {
                        queue.add(new Cell(nx, ny, curCell.curTime));
                        map[nx][ny] = curTime;
                    }

                    if (curTime == -1) { // 세포 죽음
                        map[curX][curY] = curTime;
                    }
                }
            }
        }
    }

    private static boolean isIn(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}

