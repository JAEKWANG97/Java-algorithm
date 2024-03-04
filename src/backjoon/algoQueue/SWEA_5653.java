package backjoon.algoQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653 {

    static final int INACTIVATE = 0;
    static final int ACTIVATE = 1;
    static final int DIE = 2;

    static class Cell {
        int x;
        int y;

        int state;

        int lifeTime; // 세포가 가지고 있는 생명력 수치
        int timeToActive; // 비활성에서 활성이 되기까지 시간
        int timeToDie; // 활성 시간에서 죽을 때까지 시간

        int time;

        boolean visited;

        public Cell(int x, int y, int lifeTime, int time) {
            this.x = x;
            this.y = y;
            this.state = INACTIVATE;
            this.lifeTime = lifeTime;
            this.timeToActive = lifeTime;
            this.timeToDie = lifeTime;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            Map<String, Cell> cellMap = new HashMap<>();
            Queue<Cell> cellQueue = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int item = Integer.parseInt(st.nextToken());
                    if (item > 0) {
                        Cell cell = new Cell(i, j, item, 0);
                        cellMap.put(i + " " + j, cell);
                        cellQueue.add(cell);
                    }
                }
            }
        }
    }

    static int bfs(Map<String, Cell> cellMap, Queue<Cell> cellQueue, int n, int m, int k) {
        int count = 0;
        int[] deltaX = new int[]{0, 1, 0, -1};
        int[] deltaY = new int[]{-1, 0, 1, 0};
        int time = 0;
        while (!cellQueue.isEmpty()) {
            int size = cellQueue.size();
            while (size-- > 0) {
                Cell cur = cellQueue.poll();
                cur.time = time;

                if (cur.state != ACTIVATE) {
                    cur.timeToActive -= 1;
                    cellQueue.offer(cur);
                    continue;
                }

                if (cur.state == DIE) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + deltaX[i];
                    int ny = cur.y + deltaY[i];

                    // 1. 세포가 같은 시간대인지 체크, 같은 시간대이면 queue에 더 큰 값 추가
                    // 2. lifeTime 비교 후 더 큰 값 넣어주기
                    if (cellMap.getOrDefault(nx + " " + ny, new Cell(0, 0, 0, time)).time != cur.time) {
                        continue;
                    }
                    if (cellMap.getOrDefault(nx + " " + ny, new Cell(0, 0, 0, time)).lifeTime < cur.lifeTime) {
                        cellMap.put(nx + " " + ny, new Cell(nx, ny, cur.lifeTime, time));
                    }

                }

                cur.timeToDie -= 1;
                if (cur.timeToDie > 0) {
                    cellQueue.add(cur);
                }
            }
            time += 1;
            if (time == k) {
                return count;
            }
        }

        return count;
    }
}
