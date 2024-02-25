package test;

import java.util.Arrays;

public class PowerSetTest {
    public static void main(String[] args) {
        char[] arr = new char[]{'A', 'B', 'C', 'D', 'E'};
        powerSet(0, 5, new boolean[5], arr);

    }

    private static void powerSet(int cnt, int n, boolean[] visited, char[] arr) {
        if (cnt == n) {
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();
            return;
        }

        visited[cnt] = true;
        powerSet(cnt + 1, n, visited, arr);

        visited[cnt] = false;
        powerSet(cnt + 1, n, visited, arr);

    }
}
