package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14939 {
    private static final int N = 10;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        init();
        int result = solve();
        System.out.println(result);
    }

    private static void init() throws IOException {
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
            }
        }
    }

    private static void toggle(int i, int j, char[][] tempMap) {
        if (i < 0 || i >= N || j < 0 || j >= N) {
            return;
        }
        tempMap[i][j] = tempMap[i][j] == 'O' ? '#' : 'O';
    }

    private static int solve() {
        int minCount = Integer.MAX_VALUE;
        for (int firstRowState = 0; firstRowState < (1 << N); firstRowState++) {
            char[][] tempMap = copyMap();
            int count = 0;
            for (int j = 0; j < N; j++) {
                if ((firstRowState & (1 << j)) != 0) {
                    toggleSwitch(tempMap, 0, j);
                    count++;
                }
            }
            for (int i = 1; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tempMap[i - 1][j] == 'O') {
                        toggleSwitch(tempMap, i, j);
                        count++;
                    }
                }
            }
            if (confirm(tempMap)) {
                minCount = Math.min(minCount, count);
            }
        }
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    private static void toggleSwitch(char[][] tempMap, int i, int j) {
        toggle(i, j, tempMap);
        toggle(i + 1, j, tempMap);
        toggle(i - 1, j, tempMap);
        toggle(i, j + 1, tempMap);
        toggle(i, j - 1, tempMap);
    }

    private static boolean confirm(char[][] tempMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tempMap[i][j] == 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    private static char[][] copyMap() {
        char[][] newMap = new char[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, N);
        }
        return newMap;
    }
}
