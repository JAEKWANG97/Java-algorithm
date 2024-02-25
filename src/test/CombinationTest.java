package test;

import java.util.Arrays;

public class CombinationTest {
    public static void main(String[] args) {
        char[] arr = new char[]{'A', 'B', 'C', 'D'};
        combiantion(0, new boolean[4], 4, 3, new char[3], 0, arr);

    }

    private static void combiantion(int start, boolean[] visited, int n, int r, char[] order, int cnt, char[] arr) {
        if (cnt == r) {
            System.out.println(Arrays.toString(order));

            return;
        }

        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[cnt] = arr[i];
                combiantion(i + 1, visited, n, r, order, cnt + 1, arr);
                visited[i] = false;
            }
        }
    }
}
