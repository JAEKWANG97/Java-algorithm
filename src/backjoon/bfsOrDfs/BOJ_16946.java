package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_16946 {
    private static class Location {
        int row, col;

        Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            return prime * (prime + row) + col;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Location)) {
                return false;
            }
            Location other = (Location) obj;
            return row == other.row && col == other.col;
        }

        @Override
        public String toString() {
            return String.format("Location [row=%d, col=%d]", row, col);
        }
    }

    private static int[][] map;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static List<Location> zeroList = new ArrayList<>();
    private static List<Location> oneList = new ArrayList<>();
    private static Map<Location, Integer> setMap = new HashMap<>();
    private static int idx = 0;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
                if (map[i][j] == 0) {
                    zeroList.add(new Location(i, j));
                } else if (map[i][j] == 1) {
                    oneList.add(new Location(i, j));
                }
            }
        }
    }

    private static void solve() {
        int[][] copyMap = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        zeroList.forEach(location -> {
            if (!visited[location.row][location.col]) {
                bfs(location, visited, copyMap);
            }
        });

        updateMapWithOnes(copyMap);

        printMap(copyMap);
    }

    private static void updateMapWithOnes(int[][] copyMap) {
        oneList.forEach(location -> {
            Set<Integer> uniqueGroups = new HashSet<>();
            int wallValue = 1;
            for (int i = 0; i < 4; i++) {
                int nr = location.row + dx[i];
                int nc = location.col + dy[i];
                if (isValid(nr, nc)) {
                    Integer groupIdx = setMap.get(new Location(nr, nc));
                    if (groupIdx != null && uniqueGroups.add(groupIdx)) {
                        wallValue += copyMap[nr][nc];
                    }
                }
            }
            copyMap[location.row][location.col] = wallValue % 10;
        });
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static void printMap(int[][] map) {
        for (int[] row : map) {
            for (int cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    private static void bfs(Location start, boolean[][] visited, int[][] copyMap) {
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.row][start.col] = true;
        List<Location> group = new ArrayList<>();
        int count = 1;
        while (!queue.isEmpty()) {
            Location current = queue.poll();
            group.add(current);
            for (int i = 0; i < 4; i++) {
                int nr = current.row + dx[i];
                int nc = current.col + dy[i];
                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                    queue.offer(new Location(nr, nc));
                    visited[nr][nc] = true;
                    count++;
                }
            }
        }

        int finalCount = count;
        group.forEach(location -> {
            setMap.put(location, idx);
            copyMap[location.row][location.col] = finalCount;
        });
        idx++;
    }
}
