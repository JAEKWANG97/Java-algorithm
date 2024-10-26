package backjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_12933 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder st;

    private static String input;

    private static char[] arr = { 'q', 'u', 'a', 'c', 'k' };

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        input = br.readLine();
    }

    private static void solve() {
        int[] ducks = new int[5]; // q, u, a, c, k 순서로 저장
        int maxDucks = 0;
        int currentDucks = 0;

        for (char c : input.toCharArray()) {
            int index = "quack".indexOf(c);
            if (index == -1) {
                System.out.println(-1);
                return;
            }

            if (index == 0) {
                ducks[index]++;
                currentDucks++;
                maxDucks = Math.max(maxDucks, currentDucks);
            } else {
                if (ducks[index - 1] == 0) {
                    System.out.println(-1);
                    return;
                }
                ducks[index - 1]--;
                ducks[index]++;
                if (index == 4) {
                    currentDucks--;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            if (ducks[i] > 0) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(maxDucks == 0 ? -1 : maxDucks);
    }
}
