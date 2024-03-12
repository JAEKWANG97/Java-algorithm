package backjoon.algoQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653 {

    static final int INACTIVATE = 0;
    static final int ACTIVATE = 1;
    static final int DIE = 2;

    static final int BREED = 3;

    static class Cell {
        int x;
        int y;

        int state;

        int lifeTime; // 세포가 가지고 있는 생명력 수치
        int timeToActive; // 비활성에서 활성이 되기까지 시간
        int timeToDie; // 활성 시간에서 죽을 때까지 시간

        public Cell(int x, int y, int state, int lifeTime) {
            this.x = x;
            this.y = y;
            this.state = state;
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

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int item = Integer.parseInt(st.nextToken());
                    if (item > 0) {
                        Cell cell = new Cell(i, j, INACTIVATE, item);
                        cellMap.put(i + " " + j, cell);

                    }
                }
            }

            int[] deltaX = new int[]{0, 1, 0, -1};
            int[] deltaY = new int[]{-1, 0, 1, 0};

            Map<String, Cell> tempMap = new HashMap<>(); // 변경할 셀을 저장할 임시 맵

            for (int i = 0; i < K; i++) {
                for (Cell cell : new ArrayList<>(cellMap.values())) { // cellMap.values()의 스냅샷을 사용하여 순회
                    if (cell.state == INACTIVATE) {
                        if (--cell.timeToActive == 0) {
                            cell.state = ACTIVATE;
                        }
                    }
                    if (cell.state == ACTIVATE) {
                        for (int j = 0; j < 4; j++) {
                            int nx = cell.x + deltaX[j];
                            int ny = cell.y + deltaY[j];
                            String key = nx + " " + ny;

                            Cell existingCell = cellMap.getOrDefault(key, new Cell(nx, ny, BREED, cell.lifeTime));
                            if (existingCell.state == BREED) {
                                int lifeTime = Math.max(existingCell.lifeTime, cell.lifeTime);

                                // 임시 맵에 새 셀 상태 저장
                                tempMap.put(key, new Cell(nx, ny, BREED, lifeTime));
                            }
                        }
                        if (--cell.timeToDie == 0) {
                            cell.state = DIE;
                        }
                    }
                }

                // 임시 맵에서 cellMap으로 변경사항 반영
                for (Map.Entry<String, Cell> entry : tempMap.entrySet()) {
                    cellMap.put(entry.getKey(), entry.getValue());
                }
                tempMap.clear(); // 다음 순회를 위해 임시 맵 초기화
            }

            int count = 0;
            for (Cell c : cellMap.values()) {
                if (c.state == INACTIVATE || c.state == ACTIVATE) {
                    count += 1;
                }
            }

            System.out.println(count);

        }
    }
}