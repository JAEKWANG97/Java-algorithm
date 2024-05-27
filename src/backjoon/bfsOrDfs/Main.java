package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] initMap = new int[3][3];
    static int zeroI, zeroJ;

    public static void main(String[] args) throws IOException {
        init();
        int result = bfs();
        System.out.println(result);
    }

    private static void init() throws IOException {
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                initMap[i][j] = Integer.parseInt(st.nextToken());
                if (initMap[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                }
            }
        }
    }

    private static int bfs() {
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        String target = "123456780";

        State start = new State(copyMap(initMap), zeroI, zeroJ, 0);
        queue.add(start);
        visited.add(mapToString(start.map));

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (mapToString(current.map).equals(target)) {
                return current.count;
            }

            for (int[] d : dir) {
                int nextI = current.i + d[0];
                int nextJ = current.j + d[1];

                if (nextI >= 0 && nextI < 3 && nextJ >= 0 && nextJ < 3) {
                    int[][] newMap = copyMap(current.map);
                    newMap[current.i][current.j] = newMap[nextI][nextJ];
                    newMap[nextI][nextJ] = 0;

                    State newState = new State(newMap, nextI, nextJ, current.count + 1);
                    String newMapString = mapToString(newState.map);

                    if (!visited.contains(newMapString)) {
                        queue.add(newState);
                        visited.add(newMapString);
                    }
                }
            }
        }
        return -1;
    }

    private static String mapToString(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : map) {
            for (int num : row) {
                sb.append(num);
            }
        }
        return sb.toString();
    }

    private static int[][] copyMap(int[][] copyMap) {
        int[][] newMap = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(copyMap[i], 0, newMap[i], 0, 3);
        }
        return newMap;
    }

    static class State {
        int[][] map;
        int i, j, count;

        State(int[][] map, int i, int j, int count) {
            this.map = map;
            this.i = i;
            this.j = j;
            this.count = count;
        }
    }
}