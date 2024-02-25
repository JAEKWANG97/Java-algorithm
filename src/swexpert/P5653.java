package swexpert;

import java.io.*;
import java.util.*;

public class P5653 {
    static class Cell implements Comparable<Cell> {
        int x, y, lifeTime, activeTime, state; // state: 0-비활성, 1-활성, 2-죽음

        public Cell(int x, int y, int lifeTime, int activeTime, int state) {
            this.x = x;
            this.y = y;
            this.lifeTime = lifeTime;
            this.activeTime = activeTime;
            this.state = state;
        }

        @Override
        public int compareTo(Cell other) {
            return other.lifeTime - this.lifeTime; // 생명력 높은 순으로 정렬
        }
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int N, M, K;
    static Map<String, Cell> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map.clear();

            PriorityQueue<Cell> queue = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int lifeTime = Integer.parseInt(st.nextToken());
                    if (lifeTime > 0) {
                        Cell cell = new Cell(i, j, lifeTime, lifeTime, 0);
                        map.put(i + "," + j, cell);
                        queue.add(cell);
                    }
                }
            }

            simulate(queue);
            System.out.println("#" + tc + " " + map.size());
        }
    }

    private static void simulate(PriorityQueue<Cell> queue) {
        int time = 0;
        while (time < K && !queue.isEmpty()) {
            int size = queue.size();
            PriorityQueue<Cell> newCells = new PriorityQueue<>();
            for (int i = 0; i < size; i++) {
                Cell cell = queue.poll();
                if (cell.state == 2) {
                    continue; // 죽은 세포는 무시
                }

                if (cell.state == 1 && cell.activeTime == cell.lifeTime) { // 활성 상태에서 처음 시간
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = cell.x + dx[dir];
                        int ny = cell.y + dy[dir];
                        if (!map.containsKey(nx + "," + ny)) {
                            Cell newCell = new Cell(nx, ny, cell.lifeTime, cell.lifeTime, 0);
                            map.put(nx + "," + ny, newCell);
                            newCells.add(newCell);
                        } else if (map.get(nx + "," + ny).lifeTime < cell.lifeTime) {
                            Cell newCell = new Cell(nx, ny, cell.lifeTime, cell.lifeTime, 0);
                            map.put(nx + "," + ny, newCell);
                            newCells.add(newCell);
                        }
                    }
                }
                if (--cell.activeTime == 0) {
                    if (cell.state == 0) { // 비활성 -> 활성
                        cell.state = 1;
                        cell.activeTime = cell.lifeTime; // 활성 상태 시간 재설정
                    } else { // 활성 -> 죽음
                        cell.state = 2;
                    }
                }
                if (cell.state != 2) {
                    newCells.add(cell); // 살아있는 세포 다시 큐에 추가
                }
            }

            queue.addAll(newCells);
            time++;
        }
    }

}
