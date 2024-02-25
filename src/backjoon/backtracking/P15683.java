package backjoon.backtracking;

import java.io.*;
import java.util.*;

public class P15683 {
    static class Cctv {
        int x, y, kind;

        public Cctv(int x, int y, int kind) {
            this.x = x;
            this.y = y;
            this.kind = kind;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[][] map;
    static List<Cctv> cctvList = new ArrayList<>();
    static int minBlindSpotsCount = Integer.MAX_VALUE;
    static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북, 동, 남, 서

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, map);
        System.out.println(minBlindSpotsCount);
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] <= 5) {
                    cctvList.add(new Cctv(i, j, map[i][j]));
                }
            }
        }
    }

    // DFS를 사용한 모든 CCTV 방향 조합 탐색
    private static void dfs(int index, int[][] prevMap) {
        if (index == cctvList.size()) {
            minBlindSpotsCount = Math.min(minBlindSpotsCount, countBlindSpots(prevMap));
            return;
        }

        Cctv cctv = cctvList.get(index);
        int[][] newMap = new int[N][M];

        for (int d = 0; d < 4; d++) { // 각 CCTV 방향에 대해 시뮬레이션
            copyMap(prevMap, newMap); // 맵 상태 복사
            switch (cctv.kind) {
                case 1:
                    watch(cctv, newMap, d);
                    break;
                case 2:
                    watch(cctv, newMap, d);
                    watch(cctv, newMap, (d + 2) % 4);
                    break;
                case 3:
                    watch(cctv, newMap, d);
                    watch(cctv, newMap, (d + 1) % 4);
                    break;
                case 4:
                    watch(cctv, newMap, d);
                    watch(cctv, newMap, (d + 1) % 4);
                    watch(cctv, newMap, (d + 2) % 4);
                    break;
                case 5:
                    for (int i = 0; i < 4; i++) {
                        watch(cctv, newMap, i);
                    }
                    break;
            }
            dfs(index + 1, newMap); // 다음 CCTV에 대해 재귀 호출
        }
    }

    private static void watch(Cctv cctv, int[][] map, int d) {
        int x = cctv.x;
        int y = cctv.y;
        while (x >= 0 && x < N && y >= 0 && y < M && map[x][y] != 6) {
            map[x][y] = 7; // 감시 영역 표시
            x += directions[d][0];
            y += directions[d][1];
        }
    }

    private static int countBlindSpots(int[][] map) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void copyMap(int[][] source, int[][] destination) {
        for (int i = 0; i < N; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, M);
        }
    }
}
